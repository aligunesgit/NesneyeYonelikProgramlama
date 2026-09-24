package nyp.m4.okul;

/**
 * M4 - Bir öğrenci.
 *
 * <p>Öğrenci, derslerden bağımsız yaşar: önce yaratılır, sonra bir ya da daha fazla derse
 * kaydedilir. Ders kapansa bile öğrenci nesnesi var olmaya devam eder (toplama).
 */
public class Ogrenci {

    private final String numara;
    private final String ad;

    public Ogrenci(String numara, String ad) {
        if (numara == null || numara.isBlank()) {
            throw new IllegalArgumentException("Numara boş olamaz");
        }
        if (ad == null || ad.isBlank()) {
            throw new IllegalArgumentException("Ad boş olamaz");
        }
        this.numara = numara;
        this.ad = ad;
    }

    public String getNumara() {
        return numara;
    }

    public String getAd() {
        return ad;
    }
}
