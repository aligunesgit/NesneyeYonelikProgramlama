package nyp.m7.odeme;

/**
 * M7 - Kredi kartı ile ödeme. Tek çekimde %2,5 komisyon; her ek taksit için %1 daha eklenir.
 *
 * <p>İki arayüzü birden uygular: {@link Odeme} ve {@link Iade}.
 */
public class KrediKarti implements Odeme, Iade {

    public static final double TEMEL_ORAN = 0.025;
    public static final double TAKSIT_ORANI = 0.01;
    public static final int AZAMI_TAKSIT = 12;

    private final int taksitSayisi;

    /** Taksit sayısı 1 ile 12 arasında olmalıdır. */
    public KrediKarti(int taksitSayisi) {
        if (taksitSayisi < 1 || taksitSayisi > AZAMI_TAKSIT) {
            throw new IllegalArgumentException("Taksit 1-12 arasında olmalı: " + taksitSayisi);
        }
        this.taksitSayisi = taksitSayisi;
    }

    public int getTaksitSayisi() {
        return taksitSayisi;
    }

    @Override
    public String ad() {
        return "Kredi Kartı (" + taksitSayisi + " taksit)";
    }

    @Override
    public double komisyon(double tutar) {
        return tutar * (TEMEL_ORAN + (taksitSayisi - 1) * TAKSIT_ORANI);
    }

    /** Aylık taksit tutarı. */
    public double aylikTaksit(double tutar) {
        return toplamTutar(tutar) / taksitSayisi;
    }

    /** Kart iadelerinde komisyon dahil tamamı geri ödenir. */
    @Override
    public double iadeTutari(double odenenToplam) {
        return odenenToplam;
    }
}
