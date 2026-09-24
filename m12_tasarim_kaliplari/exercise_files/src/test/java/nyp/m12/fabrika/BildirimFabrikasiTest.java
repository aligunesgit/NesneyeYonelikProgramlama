package nyp.m12.fabrika;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BildirimFabrikasiTest {

    @Test
    @DisplayName("Fabrika her kanal için doğru somut türü üretir")
    void kanalaGoreDogruTur() {
        assertInstanceOf(Bildirim.Eposta.class, BildirimFabrikasi.olustur(Kanal.EPOSTA, "ayse@ornek.com"));
        assertInstanceOf(Bildirim.Sms.class, BildirimFabrikasi.olustur(Kanal.SMS, "05551234567"));
        assertInstanceOf(Bildirim.Push.class, BildirimFabrikasi.olustur(Kanal.PUSH, "cihaz-42"));
    }

    @Test
    @DisplayName("Kanal adı metinden, büyük/küçük harf fark etmeden okunur")
    void metindenKanal() {
        Bildirim bildirim = BildirimFabrikasi.olustur(" sms ", "05551234567");

        assertEquals("SMS -> 05551234567: Merhaba", bildirim.gonder("Merhaba"));
    }

    @Test
    @DisplayName("Bilinmeyen kanal adı ve null kanal reddedilir")
    void bilinmeyenKanal() {
        assertThrows(IllegalArgumentException.class, () -> BildirimFabrikasi.olustur("faks", "123"));
        assertThrows(NullPointerException.class, () -> BildirimFabrikasi.olustur((Kanal) null, "123"));
    }

    @Test
    @DisplayName("Geçersiz alıcı, ürün oluşturulurken yakalanır")
    void gecersizAlici() {
        assertThrows(IllegalArgumentException.class, () -> BildirimFabrikasi.olustur(Kanal.EPOSTA, "ayse"));
    }

    @Test
    @DisplayName("SMS 160 karakteri aşamaz; tam 160 karakter kabul edilir")
    void smsUzunlukSiniri() {
        Bildirim sms = BildirimFabrikasi.olustur(Kanal.SMS, "05551234567");

        sms.gonder("a".repeat(160));
        assertThrows(IllegalArgumentException.class, () -> sms.gonder("a".repeat(161)));
    }
}
