package nyp.m2;

/**
 * M2 - Hiç kurucu yazılmamış bir sınıf.
 *
 * <p>Derleyici bu sınıfa parametresiz bir varsayılan kurucu (default constructor) ekler; böylece
 * {@code new Sayac()} yazılabilir. {@code deger} alanına hiçbir değer atanmadığı için varsayılan
 * değeri olan 0 ile başlar.
 */
public class Sayac {

    private int deger;

    /** Sayacı bir artırır. */
    public void artir() {
        deger = deger + 1;
    }

    /** Sayacı sıfırlar. */
    public void sifirla() {
        deger = 0;
    }

    public int getDeger() {
        return deger;
    }

    @Override
    public String toString() {
        return "Sayac[" + deger + "]";
    }
}
