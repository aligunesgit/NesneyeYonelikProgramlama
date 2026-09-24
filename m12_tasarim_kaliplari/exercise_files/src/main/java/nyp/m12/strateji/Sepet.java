package nyp.m12.strateji;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * M12 - Strategy kalıbında bağlam (context): alışveriş sepeti.
 *
 * <p>Sepet kargo ücretinin nasıl hesaplandığını bilmez; bu işi kendisine verilen
 * {@link KargoStratejisi} nesnesine devreder. Strateji çalışma anında değiştirilebilir.
 */
public class Sepet {

    /** Sepetteki bir ürün satırı. */
    public record Urun(String ad, double fiyat, double agirlikKg) {
        public Urun {
            if (fiyat < 0 || agirlikKg < 0) {
                throw new IllegalArgumentException("Fiyat ve ağırlık negatif olamaz");
            }
        }
    }

    private final List<Urun> urunler = new ArrayList<>();
    private KargoStratejisi kargoStratejisi;

    public Sepet(KargoStratejisi kargoStratejisi) {
        this.kargoStratejisi = Objects.requireNonNull(kargoStratejisi, "Strateji null olamaz");
    }

    public void urunEkle(String ad, double fiyat, double agirlikKg) {
        urunler.add(new Urun(ad, fiyat, agirlikKg));
    }

    /** Stratejiyi çalışma anında değiştirir (ör. müşteri "hızlı kargo" seçti). */
    public void setKargoStratejisi(KargoStratejisi kargoStratejisi) {
        this.kargoStratejisi = Objects.requireNonNull(kargoStratejisi, "Strateji null olamaz");
    }

    public double toplamTutar() {
        return urunler.stream().mapToDouble(Urun::fiyat).sum();
    }

    public double toplamAgirlik() {
        return urunler.stream().mapToDouble(Urun::agirlikKg).sum();
    }

    public double kargoUcreti() {
        return kargoStratejisi.ucretHesapla(toplamAgirlik(), toplamTutar());
    }

    public double odenecekTutar() {
        return toplamTutar() + kargoUcreti();
    }
}
