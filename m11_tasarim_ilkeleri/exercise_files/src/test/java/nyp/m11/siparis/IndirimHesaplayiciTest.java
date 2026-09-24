package nyp.m11.siparis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IndirimHesaplayiciTest {

    private final Siparis telefon = new Siparis("Ayşe", "ayse@ornek.com", "Elektronik", 1200);
    private final Siparis kitap = new Siparis("Can", "5551234567", "Kitap", 200);

    private final List<IndirimKurali> standartKurallar =
            List.of(new YuzdeIndirimi(0.10), new EsikIndirimi(1000, 100));

    @Test
    @DisplayName("Kuralların indirimleri toplanır")
    void kurallarToplanir() {
        IndirimHesaplayici hesaplayici = new IndirimHesaplayici(standartKurallar);

        // 1200 * 0.10 = 120, eşik aşıldı: +100
        assertEquals(220.0, hesaplayici.toplamIndirim(telefon), 1e-9);
        assertEquals(980.0, hesaplayici.netTutar(telefon), 1e-9);
    }

    @Test
    @DisplayName("Eşiğin altındaki siparişe eşik indirimi uygulanmaz")
    void esikAltindaIndirimYok() {
        IndirimHesaplayici hesaplayici = new IndirimHesaplayici(standartKurallar);

        assertEquals(20.0, hesaplayici.toplamIndirim(kitap), 1e-9); // yalnızca yüzde 10
    }

    @Test
    @DisplayName("Tutar tam eşiğe eşitse eşik indirimi uygulanır")
    void esigeEsitTutar() {
        Siparis tamEsik = new Siparis("Zeynep", "zeynep@ornek.com", "Giyim", 1000);

        assertEquals(100.0, new EsikIndirimi(1000, 100).indirim(tamEsik), 1e-9);
    }

    @Test
    @DisplayName("Kural yoksa indirim 0, net tutar sipariş tutarıdır")
    void kuralYok() {
        IndirimHesaplayici hesaplayici = new IndirimHesaplayici(List.of());

        assertEquals(1200.0, hesaplayici.netTutar(telefon), 1e-9);
    }

    @Test
    @DisplayName("Toplam indirim sipariş tutarını aşamaz")
    void indirimTutariAsamaz() {
        IndirimHesaplayici hesaplayici = new IndirimHesaplayici(
                List.of(new EsikIndirimi(0, 150), new EsikIndirimi(0, 150)));

        assertEquals(200.0, hesaplayici.toplamIndirim(kitap), 1e-9);
        assertEquals(0.0, hesaplayici.netTutar(kitap), 1e-9);
    }

    @Test
    @DisplayName("OCP: yeni kural mevcut sınıflara dokunmadan eklenir")
    void yeniKuralEklemek() {
        // Hazırla: yeni kampanya "kitaplarda ek yüzde 5". Hiçbir mevcut sınıf değişmedi.
        IndirimKurali kitapKampanyasi =
                s -> s.kategori().equals("Kitap") ? s.tutar() * 0.05 : 0.0;
        IndirimHesaplayici hesaplayici = new IndirimHesaplayici(
                List.of(new YuzdeIndirimi(0.10), new EsikIndirimi(1000, 100), kitapKampanyasi));
        // Çalıştır ve doğrula: 200 * 0.10 + 200 * 0.05 = 30
        assertEquals(170.0, hesaplayici.netTutar(kitap), 1e-9);
        assertEquals(980.0, hesaplayici.netTutar(telefon), 1e-9); // elektronik etkilenmedi
    }

    @Test
    @DisplayName("Geçersiz yüzde oranı reddedilir")
    void gecersizOran() {
        assertThrows(IllegalArgumentException.class, () -> new YuzdeIndirimi(1.5));
        assertThrows(IllegalArgumentException.class, () -> new YuzdeIndirimi(-0.1));
    }
}
