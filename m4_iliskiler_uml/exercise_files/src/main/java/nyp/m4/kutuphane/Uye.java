package nyp.m4.kutuphane;

import java.util.ArrayList;

/**
 * M4 - Kütüphane üyesi.
 *
 * <p>Çift yönlü ilişkinin diğer ucu: üye, elindeki kitapları (0..3) bilir. {@link #oduncAl} ve
 * {@link #iadeEt} ilişkinin iki tarafını da birlikte günceller; böylece "üye kitabı biliyor ama
 * kitap üyeyi bilmiyor" gibi tutarsız bir durum oluşamaz.
 */
public class Uye {

    /** Bir üyenin aynı anda elinde tutabileceği en fazla kitap sayısı. */
    public static final int MAKS_KITAP = 3;

    private final String uyeNo;
    private final String ad;
    private final ArrayList<Kitap> kitaplar = new ArrayList<>();

    public Uye(String uyeNo, String ad) {
        if (uyeNo == null || uyeNo.isBlank()) {
            throw new IllegalArgumentException("Üye numarası boş olamaz");
        }
        if (ad == null || ad.isBlank()) {
            throw new IllegalArgumentException("Ad boş olamaz");
        }
        this.uyeNo = uyeNo;
        this.ad = ad;
    }

    /**
     * Kitabı ödünç alır ve ilişkinin iki tarafını da günceller.
     *
     * @return kitap raftaysa ve üyenin limiti dolmadıysa {@code true}; aksi hâlde {@code false}
     * @throws IllegalArgumentException kitap {@code null} ise
     */
    public boolean oduncAl(Kitap kitap) {
        if (kitap == null) {
            throw new IllegalArgumentException("Kitap null olamaz");
        }
        if (kitap.oduncteMi() || kitaplar.size() >= MAKS_KITAP) {
            return false;
        }
        kitaplar.add(kitap);        // bu taraf: üye kitabı biliyor
        kitap.oduncAlaniAta(this);  // karşı taraf: kitap üyeyi biliyor
        return true;
    }

    /** Kitap bu üyedeyse iade eder ve iki tarafı da günceller; değilse {@code false} döndürür. */
    public boolean iadeEt(Kitap kitap) {
        if (kitap == null || kitap.getOduncAlan() != this) {
            return false;
        }
        kitaplar.remove(kitap);
        kitap.oduncAlaniAta(null);
        return true;
    }

    /** Verilen kitap bu üyenin elindeyse {@code true}. */
    public boolean elindeMi(Kitap kitap) {
        for (Kitap k : kitaplar) {
            if (k == kitap) {
                return true;
            }
        }
        return false;
    }

    public int oduncKitapSayisi() {
        return kitaplar.size();
    }

    public String getUyeNo() {
        return uyeNo;
    }

    public String getAd() {
        return ad;
    }
}
