package nyp.m12.fabrika;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BildirimServisiTest {

    @Test
    @DisplayName("Factory Method: alt sınıf ürünü seçer, algoritma üst sınıfta kalır")
    void altSinifUrunuSecer() {
        BildirimServisi servis = new SmsServisi();

        assertEquals("SMS -> 05551234567: Kargonuz yolda", servis.bilgilendir("05551234567", "  Kargonuz yolda "));
    }

    @Test
    @DisplayName("Yeni bir kanal için yalnızca yeni bir alt sınıf yeterlidir")
    void yeniAltSinif() {
        BildirimServisi epostaServisi = new BildirimServisi() {
            @Override
            protected Bildirim bildirimOlustur(String alici) {
                return new Bildirim.Eposta(alici);
            }
        };

        assertEquals("E-posta -> ali@ornek.com: Hoş geldiniz",
                epostaServisi.bilgilendir("ali@ornek.com", "Hoş geldiniz"));
    }

    @Test
    @DisplayName("Boş mesaj gönderilmez")
    void bosMesaj() {
        assertThrows(IllegalArgumentException.class, () -> new SmsServisi().bilgilendir("0555", " "));
    }
}
