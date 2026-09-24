package nyp.m11.siparis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiparisServisiTest {

    private SahteBildirimGonderici sahte;
    private SiparisServisi servis;

    @BeforeEach
    void hazirla() {
        sahte = new SahteBildirimGonderici();
        IndirimHesaplayici hesaplayici = new IndirimHesaplayici(
                List.of(new YuzdeIndirimi(0.10), new EsikIndirimi(1000, 100)));
        servis = new SiparisServisi(hesaplayici, sahte); // bağımlılıklar kurucu ile verildi
    }

    @Test
    @DisplayName("Sipariş verilince net tutar döner ve müşteriye tek bildirim gider")
    void siparisVerBildirimGonderir() {
        // Hazırla
        Siparis siparis = new Siparis("Ayşe", "ayse@ornek.com", "Elektronik", 1200);
        // Çalıştır
        double net = servis.siparisVer(siparis);
        // Doğrula
        assertEquals(980.0, net, 1e-9);
        assertEquals(1, sahte.getGonderimler().size());
        SahteBildirimGonderici.Gonderim gonderim = sahte.getGonderimler().get(0);
        assertEquals("ayse@ornek.com", gonderim.alici());
        assertEquals("Sayın Ayşe, siparişiniz alındı. Ödenecek tutar: 980.00 TL", gonderim.mesaj());
    }

    @Test
    @DisplayName("Her sipariş için ayrı bildirim gönderilir")
    void herSiparisIcinBildirim() {
        servis.siparisVer(new Siparis("Ayşe", "ayse@ornek.com", "Kitap", 200));
        servis.siparisVer(new Siparis("Can", "5551234567", "Giyim", 500));

        assertEquals(2, sahte.getGonderimler().size());
        assertEquals("5551234567", sahte.getGonderimler().get(1).alici());
    }

    @Test
    @DisplayName("Gönderici hata verirse istisna çağırana ulaşır")
    void gondericiHatasiYayilir() {
        SiparisServisi hataliServis = new SiparisServisi(new IndirimHesaplayici(List.of()),
                (alici, mesaj) -> {
                    throw new IllegalStateException("sunucu kapalı");
                });

        assertThrows(IllegalStateException.class,
                () -> hataliServis.siparisVer(new Siparis("Can", "can@ornek.com", "Kitap", 50)));
    }

    @Test
    @DisplayName("Bağımlılık verilmezse servis oluşturulamaz")
    void nullBagimlilikReddedilir() {
        assertThrows(NullPointerException.class, () -> new SiparisServisi(null, sahte));
        assertThrows(NullPointerException.class,
                () -> new SiparisServisi(new IndirimHesaplayici(List.of()), null));
    }

    @Test
    @DisplayName("Mesaj ondalık ayırıcı olarak nokta kullanır")
    void mesajBicimi() {
        String mesaj = SiparisServisi.mesajOlustur(
                new Siparis("Can", "can@ornek.com", "Kitap", 50), 45.5);

        assertTrue(mesaj.endsWith("45.50 TL"));
    }
}
