package nyp.m10;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * M10 - Sipariş listesi üzerinden raporlar üreten sınıf; Stream API örnekleri.
 *
 * <p>Ciro ve kategori toplamlarında iptal edilmiş siparişler sayılmaz. Diğer raporlar (müşteri
 * sayısı, en pahalı sipariş, durum sayıları) tüm siparişleri kapsar.
 */
public class SiparisRaporu {

    private final List<Siparis> siparisler;

    public SiparisRaporu(List<Siparis> siparisler) {
        this.siparisler = List.copyOf(siparisler);
    }

    /** İptal edilmemiş siparişlerin toplam tutarı. Liste boşsa 0. */
    public double toplamCiro() {
        return siparisler.stream()
                .filter(s -> !s.iptalMi())
                .mapToDouble(Siparis::tutar)
                .sum();
    }

    /** Kategori adından o kategorideki iptal edilmemiş siparişlerin toplamına eşleme. */
    public Map<String, Double> kategoriyeGoreToplam() {
        return siparisler.stream()
                .filter(s -> !s.iptalMi())
                .collect(Collectors.groupingBy(Siparis::kategori,
                        Collectors.summingDouble(Siparis::tutar)));
    }

    /** {@link #kategoriyeGoreToplam()} raporunun döngüyle yazılmış karşılığı (karşılaştırma için). */
    public Map<String, Double> kategoriyeGoreToplamDongu() {
        Map<String, Double> toplamlar = new HashMap<>();
        for (Siparis s : siparisler) {
            if (s.iptalMi()) {
                continue;
            }
            double onceki = toplamlar.getOrDefault(s.kategori(), 0.0);
            toplamlar.put(s.kategori(), onceki + s.tutar());
        }
        return toplamlar;
    }

    /** Birbirinden farklı müşteri sayısı. */
    public long musteriSayisi() {
        return siparisler.stream()
                .map(Siparis::musteri)
                .distinct()
                .count();
    }

    /** Müşteri adları, tekrarsız ve alfabetik sırada, virgülle birleştirilmiş. */
    public String musteriListesi() {
        return siparisler.stream()
                .map(Siparis::musteri)
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));
    }

    /** Her durumda kaç sipariş olduğu. Hiç siparişi olmayan durum eşlemede yer almaz. */
    public Map<SiparisDurumu, Long> durumaGoreSayilar() {
        return siparisler.stream()
                .collect(Collectors.groupingBy(Siparis::durum, Collectors.counting()));
    }

    /** Tutarı en yüksek sipariş; liste boşsa boş Optional. */
    public Optional<Siparis> enPahaliSiparis() {
        return siparisler.stream()
                .max(Comparator.comparingDouble(Siparis::tutar));
    }

    /** En yüksek sipariş tutarı; liste boşsa 0. */
    public double enYuksekTutar() {
        return enPahaliSiparis()
                .map(Siparis::tutar)
                .orElse(0.0);
    }

    /** Tutara göre azalan sırada ilk {@code n} sipariş. */
    public List<Siparis> enPahaliSiparisler(int n) {
        return siparisler.stream()
                .sorted(Comparator.comparingDouble(Siparis::tutar).reversed())
                .limit(n)
                .toList();
    }

    /** Önce kategoriye göre (A-Z), aynı kategoride tutara göre azalan sıralama. */
    public List<Siparis> kategoriVeTutaraGoreSirala() {
        return siparisler.stream()
                .sorted(Comparator.comparing(Siparis::kategori)
                        .thenComparing(Comparator.comparingDouble(Siparis::tutar).reversed()))
                .toList();
    }

    /** Verilen müşterinin listedeki ilk siparişi; yoksa boş Optional. */
    public Optional<Siparis> musterininIlkSiparisi(String musteri) {
        return siparisler.stream()
                .filter(s -> s.musteri().equals(musteri))
                .findFirst();
    }

    /**
     * Verilen müşterinin ilk siparişi.
     *
     * @throws NoSuchElementException müşterinin hiç siparişi yoksa
     */
    public Siparis siparisBul(String musteri) {
        return musterininIlkSiparisi(musteri)
                .orElseThrow(() -> new NoSuchElementException("Sipariş bulunamadı: " + musteri));
    }
}
