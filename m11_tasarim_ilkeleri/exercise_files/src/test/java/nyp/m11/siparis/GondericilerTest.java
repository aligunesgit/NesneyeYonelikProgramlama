package nyp.m11.siparis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GondericilerTest {

    private final ByteArrayOutputStream tampon = new ByteArrayOutputStream();
    private final PrintStream cikis = new PrintStream(tampon, true, StandardCharsets.UTF_8);

    private String yazilan() {
        return tampon.toString(StandardCharsets.UTF_8).strip();
    }

    @Test
    @DisplayName("E-posta gönderici mesajı çıktıya yazar")
    void epostaGonderir() {
        new EpostaGonderici(cikis).gonder("ayse@ornek.com", "Merhaba");

        assertEquals("[E-posta] ayse@ornek.com: Merhaba", yazilan());
    }

    @Test
    @DisplayName("E-posta gönderici geçersiz adresi reddeder")
    void gecersizEposta() {
        EpostaGonderici gonderici = new EpostaGonderici(cikis);

        assertThrows(IllegalArgumentException.class, () -> gonderici.gonder("5551234567", "x"));
    }

    @Test
    @DisplayName("SMS gönderici mesajı çıktıya yazar ve geçersiz numarayı reddeder")
    void smsGonderir() {
        SmsGonderici gonderici = new SmsGonderici(cikis);

        gonderici.gonder("5551234567", "Merhaba");

        assertEquals("[SMS] 5551234567: Merhaba", yazilan());
        assertThrows(IllegalArgumentException.class, () -> gonderici.gonder("ayse@ornek.com", "x"));
        assertThrows(IllegalArgumentException.class, () -> gonderici.gonder("", "x"));
    }

    @Test
    @DisplayName("Servis, kodu değişmeden SMS göndericiyle de çalışır")
    void servisSmsIleCalisir() {
        SiparisServisi servis = new SiparisServisi(
                new IndirimHesaplayici(List.of()), new SmsGonderici(cikis));

        servis.siparisVer(new Siparis("Can", "5551234567", "Kitap", 50));

        assertEquals("[SMS] 5551234567: Sayın Can, siparişiniz alındı. Ödenecek tutar: 50.00 TL",
                yazilan());
    }
}
