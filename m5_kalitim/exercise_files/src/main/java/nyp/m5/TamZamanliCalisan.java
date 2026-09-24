package nyp.m5;

/** M5 - Sabit aylık maaşla çalışan: {@link Calisan}'ın bir alt sınıfı. */
public class TamZamanliCalisan extends Calisan {

    private final double aylikMaas;

    public TamZamanliCalisan(String sicilNo, String ad, double aylikMaas) {
        super(sicilNo, ad);                                     // önce üst sınıf kurulur
        this.aylikMaas = pozitifOlmali(aylikMaas, "Aylık maaş");
    }

    @Override
    public double aylikUcret() {
        return aylikMaas;
    }

    @Override
    public String toString() {
        return super.toString() + " (tam zamanlı)";
    }
}
