package nyp.m7.hayvan;

/**
 * M7 - "Elmas" çakışması: iki arayüzden aynı imzalı iki {@code default} metot gelir.
 *
 * <p>Derleyici hangisini seçeceğini bilemez; sınıf {@code hareket()} metodunu ezmek zorundadır.
 * {@code X.super.m()} yazımıyla istenen arayüzün sürümü çağrılabilir.
 */
public class Ordek implements Ucabilen, Yuzebilen {

    @Override
    public String hareket() {
        return Ucabilen.super.hareket() + " ve " + Yuzebilen.super.hareket();
    }
}
