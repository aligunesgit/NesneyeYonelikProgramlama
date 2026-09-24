package nyp.m10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiparisRaporuTest {

    // Beklenen değerler elle hesaplandı (iptal: Mehmet/Kitap/90):
    // Kitap 120 + 45 = 165, Elektronik 2500 + 800 = 3300, Giyim 350 + 600 = 950, ciro 4415.
    private final List<Siparis> siparisler = List.of(
            new Siparis("Ayşe", "Kitap", 120, SiparisDurumu.TESLIM_EDILDI),
            new Siparis("Mehmet", "Elektronik", 2500, SiparisDurumu.KARGODA),
            new Siparis("Ayşe", "Elektronik", 800, SiparisDurumu.TESLIM_EDILDI),
            new Siparis("Zeynep", "Giyim", 350, SiparisDurumu.BEKLIYOR),
            new Siparis("Mehmet", "Kitap", 90, SiparisDurumu.IPTAL),
            new Siparis("Can", "Giyim", 600, SiparisDurumu.TESLIM_EDILDI),
            new Siparis("Zeynep", "Kitap", 45, SiparisDurumu.TESLIM_EDILDI));

    private final SiparisRaporu rapor = new SiparisRaporu(siparisler);
    private final SiparisRaporu bosRapor = new SiparisRaporu(List.of());

    @Test
    @DisplayName("Toplam ciro iptal edilen siparişi saymaz")
    void toplamCiroIptalHaric() {
        assertEquals(4415.0, rapor.toplamCiro(), 1e-9);
    }

    @Test
    @DisplayName("Kategoriye göre toplamlar doğru hesaplanır")
    void kategoriyeGoreToplam() {
        // Çalıştır
        Map<String, Double> toplamlar = rapor.kategoriyeGoreToplam();
        // Doğrula
        assertEquals(3, toplamlar.size());
        assertEquals(165.0, toplamlar.get("Kitap"), 1e-9);
        assertEquals(3300.0, toplamlar.get("Elektronik"), 1e-9);
        assertEquals(950.0, toplamlar.get("Giyim"), 1e-9);
    }

    @Test
    @DisplayName("Döngü ve stream sürümleri aynı raporu üretir")
    void donguVeStreamAyni() {
        assertEquals(rapor.kategoriyeGoreToplamDongu(), rapor.kategoriyeGoreToplam());
        assertEquals(bosRapor.kategoriyeGoreToplamDongu(), bosRapor.kategoriyeGoreToplam());
    }

    @Test
    @DisplayName("Müşteri sayısı tekrarları saymaz; liste alfabetik birleştirilir")
    void musteriSayisiVeListesi() {
        assertEquals(4, rapor.musteriSayisi());
        assertEquals("Ayşe, Can, Mehmet, Zeynep", rapor.musteriListesi());
    }

    @Test
    @DisplayName("Durumlara göre sayım")
    void durumaGoreSayilar() {
        Map<SiparisDurumu, Long> sayilar = rapor.durumaGoreSayilar();

        assertEquals(4L, sayilar.get(SiparisDurumu.TESLIM_EDILDI));
        assertEquals(1L, sayilar.get(SiparisDurumu.KARGODA));
        assertEquals(1L, sayilar.get(SiparisDurumu.BEKLIYOR));
        assertEquals(1L, sayilar.get(SiparisDurumu.IPTAL));
    }

    @Test
    @DisplayName("En pahalı sipariş bulunur")
    void enPahaliSiparis() {
        Optional<Siparis> enPahali = rapor.enPahaliSiparis();

        assertTrue(enPahali.isPresent());
        assertEquals("Mehmet", enPahali.get().musteri());
        assertEquals(2500.0, rapor.enYuksekTutar(), 1e-9);
    }

    @Test
    @DisplayName("En pahalı n sipariş azalan sırada gelir; n listeden büyükse hepsi gelir")
    void enPahaliSiparisler() {
        List<Double> ilkUc = rapor.enPahaliSiparisler(3).stream().map(Siparis::tutar).toList();

        assertEquals(List.of(2500.0, 800.0, 600.0), ilkUc);
        assertEquals(7, rapor.enPahaliSiparisler(100).size());
        assertTrue(rapor.enPahaliSiparisler(0).isEmpty());
    }

    @Test
    @DisplayName("Önce kategori, sonra azalan tutar sıralaması")
    void kategoriVeTutarSiralamasi() {
        List<Double> tutarlar = rapor.kategoriVeTutaraGoreSirala().stream()
                .map(Siparis::tutar)
                .toList();

        assertEquals(List.of(2500.0, 800.0, 600.0, 350.0, 120.0, 90.0, 45.0), tutarlar);
    }

    @Test
    @DisplayName("Müşterinin ilk siparişi bulunur; olmayan müşteri için Optional boştur")
    void musterininIlkSiparisi() {
        assertEquals(120.0, rapor.musterininIlkSiparisi("Ayşe").orElseThrow().tutar(), 1e-9);
        assertTrue(rapor.musterininIlkSiparisi("Ali").isEmpty());
    }

    @Test
    @DisplayName("siparisBul olmayan müşteri için NoSuchElementException fırlatır")
    void siparisBulOlmayanMusteri() {
        assertEquals("Elektronik", rapor.siparisBul("Mehmet").kategori());
        assertThrows(NoSuchElementException.class, () -> rapor.siparisBul("Ali"));
    }

    @Test
    @DisplayName("Boş listede raporlar güvenli varsayılan değerler döndürür")
    void bosListe() {
        assertEquals(0.0, bosRapor.toplamCiro(), 1e-9);
        assertEquals(0, bosRapor.musteriSayisi());
        assertEquals("", bosRapor.musteriListesi());
        assertFalse(bosRapor.enPahaliSiparis().isPresent());
        assertEquals(0.0, bosRapor.enYuksekTutar(), 1e-9);
        assertTrue(bosRapor.kategoriyeGoreToplam().isEmpty());
    }

    @Test
    @DisplayName("Negatif tutarlı sipariş oluşturulamaz")
    void negatifTutarReddedilir() {
        assertThrows(IllegalArgumentException.class,
                () -> new Siparis("Ayşe", "Kitap", -1, SiparisDurumu.BEKLIYOR));
    }
}
