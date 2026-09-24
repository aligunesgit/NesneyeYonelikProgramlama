package nyp.m5;

/**
 * M5 - Maaşına ek olarak aylık prim alan yönetici.
 *
 * <p>Üç katmanlı kalıtım zinciri: {@code Yonetici -> TamZamanliCalisan -> Calisan -> Object}.
 * {@link #aylikUcret()} üst sınıfın hesabını {@code super.aylikUcret()} ile çağırıp genişletir.
 * Sınıf {@code final}: yöneticiden alt sınıf türetilemez.
 */
public final class Yonetici extends TamZamanliCalisan {

    private final double prim;

    public Yonetici(String sicilNo, String ad, double aylikMaas, double prim) {
        super(sicilNo, ad, aylikMaas);
        this.prim = pozitifOlmali(prim, "Prim");
    }

    @Override
    public double aylikUcret() {
        return super.aylikUcret() + prim;   // üstün hesabı + bu sınıfın eki
    }

    @Override
    public String toString() {
        return super.toString() + " [yönetici]";
    }
}
