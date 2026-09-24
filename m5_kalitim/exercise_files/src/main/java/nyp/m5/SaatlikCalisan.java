package nyp.m5;

/**
 * M5 - Çalıştığı saat kadar ücret alan çalışan.
 *
 * <p>Ayda {@value #NORMAL_SAAT} saate kadar normal ücret, üzerindeki her saat için
 * {@value #FAZLA_MESAI_CARPANI} katı ücret ödenir.
 */
public class SaatlikCalisan extends Calisan {

    public static final double NORMAL_SAAT = 160;
    public static final double FAZLA_MESAI_CARPANI = 1.5;

    private final double saatUcreti;
    private double calisilanSaat;

    public SaatlikCalisan(String sicilNo, String ad, double saatUcreti) {
        super(sicilNo, ad);
        this.saatUcreti = pozitifOlmali(saatUcreti, "Saat ücreti");
        this.calisilanSaat = 0;
    }

    /** Bu ay çalışılan saatlere ekleme yapar. */
    public void saatEkle(double saat) {
        calisilanSaat += pozitifOlmali(saat, "Saat");
    }

    public double getCalisilanSaat() {
        return calisilanSaat;
    }

    @Override
    public double aylikUcret() {
        if (calisilanSaat <= NORMAL_SAAT) {
            return calisilanSaat * saatUcreti;
        }
        double fazlaMesai = calisilanSaat - NORMAL_SAAT;
        return NORMAL_SAAT * saatUcreti + fazlaMesai * saatUcreti * FAZLA_MESAI_CARPANI;
    }

    @Override
    public String toString() {
        return super.toString() + " (saatlik, " + calisilanSaat + " saat)";
    }
}
