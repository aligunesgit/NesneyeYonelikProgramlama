package nyp.m7.odeme;

/** M7 - Dijital cüzdan ile ödeme. %1,5 komisyon, en az 1 TL. İadede 1 TL işlem ücreti kesilir. */
public class DijitalCuzdan implements Odeme, Iade {

    public static final double ORAN = 0.015;
    public static final double ASGARI_KOMISYON = 1.0;
    public static final double IADE_UCRETI = 1.0;

    @Override
    public String ad() {
        return "Dijital Cüzdan";
    }

    @Override
    public double komisyon(double tutar) {
        return Math.max(tutar * ORAN, ASGARI_KOMISYON);
    }

    /** İade ücreti düşülür; sonuç negatif olamaz. */
    @Override
    public double iadeTutari(double odenenToplam) {
        return Math.max(0, odenenToplam - IADE_UCRETI);
    }
}
