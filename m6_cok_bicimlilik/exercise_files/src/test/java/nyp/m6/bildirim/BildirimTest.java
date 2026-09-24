package nyp.m6.bildirim;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BildirimTest {

    @Test
    @DisplayName("Her kanal kendi gonder() metodunu çalıştırır")
    void hepsineGonder() {
        List<Bildirim> kanallar = List.of(
                new EpostaBildirim("ayse@ornek.com"), new SmsBildirim("555"), new PushBildirim("c1"));

        List<String> kayitlar = BildirimServisi.hepsineGonder(kanallar, "Merhaba");

        assertEquals(List.of("[E-posta] ayse@ornek.com: Merhaba", "[SMS] 555: Merhaba", "[Push] c1: Merhaba"),
                kayitlar);
    }

    @Test
    @DisplayName("SMS parça sayısı sınırlarda doğru hesaplanır")
    void smsParcaSayisi() {
        SmsBildirim sms = new SmsBildirim("555");

        assertEquals(1, sms.parcaSayisi(""));
        assertEquals(1, sms.parcaSayisi("a".repeat(160)));
        assertEquals(2, sms.parcaSayisi("a".repeat(161)));
    }

    @Test
    @DisplayName("Toplam maliyet: e-posta ve push ücretsiz, SMS parça başına 0.25 TL")
    void toplamMaliyet() {
        List<Bildirim> kanallar = List.of(new EpostaBildirim("a@b.c"), new SmsBildirim("555"), new PushBildirim("c1"));

        double maliyet = BildirimServisi.toplamMaliyet(kanallar, "a".repeat(200));

        assertEquals(0.50, maliyet, 1e-9);
    }

    @Test
    @DisplayName("Push bildirimi 50 karakterden uzun mesajı keser")
    void pushKesme() {
        Bildirim push = new PushBildirim("c1");

        assertEquals("[Push] c1: " + "x".repeat(50) + "...", push.gonder("x".repeat(60)));
        assertEquals("[Push] c1: " + "x".repeat(50), push.gonder("x".repeat(50)));
    }

    @Test
    @DisplayName("Üst sınıf Bildirim de tek başına kullanılabilir")
    void ustSinif() {
        Bildirim b = new Bildirim("ali");

        assertEquals("[Bildirim] ali: Selam", b.gonder("Selam"));
        assertEquals(0.0, b.maliyet("Selam"), 1e-9);
    }
}
