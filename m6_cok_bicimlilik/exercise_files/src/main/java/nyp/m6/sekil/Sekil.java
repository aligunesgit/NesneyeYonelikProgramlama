package nyp.m6.sekil;

/**
 * M6 - Tüm şekillerin üst sınıfı.
 *
 * <p>Alt sınıflar {@link #alan()}, {@link #cevre()} ve {@link #ad()} metotlarını ezer (override).
 * {@code Sekil} türünde bir referans üzerinden yapılan çağrı, çalışma zamanında nesnenin gerçek
 * türüne ait metodu çalıştırır (dinamik bağlama). "Genel şekil"in alanı anlamsız olduğu için burada
 * 0 döndürüyoruz; M7'de bu sınıfı {@code abstract} yaparak daha temiz bir çözüm göreceğiz.
 */
public class Sekil {

    /** Şeklin alanı. Alt sınıflar ezer. */
    public double alan() {
        return 0;
    }

    /** Şeklin çevresi. Alt sınıflar ezer. */
    public double cevre() {
        return 0;
    }

    /** Şeklin okunaklı adı. Alt sınıflar ezer. */
    public String ad() {
        return "Şekil";
    }

    @Override
    public String toString() {
        return ad() + " (alan = " + alan() + ")";
    }
}
