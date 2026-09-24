package nyp.m7.personel;

/**
 * M7 - Saat ücretiyle çalışan personel. 160 saatin üstündeki her saat 1,5 kat ödenir.
 * Brüt maaşı 10.000 TL'nin altındaysa kesinti yapılmaz (kanca metodun ezilmesi).
 */
public class SaatlikPersonel extends Personel {

    public static final int NORMAL_SAAT = 160;
    public static final double FAZLA_MESAI_KATSAYISI = 1.5;
    public static final double KESINTI_ESIGI = 10_000;

    private final double saatUcreti;
    private final int calisilanSaat;

    public SaatlikPersonel(String ad, double saatUcreti, int calisilanSaat) {
        super(ad);
        this.saatUcreti = saatUcreti;
        this.calisilanSaat = calisilanSaat;
    }

    @Override
    public double brutMaas() {
        int normal = Math.min(calisilanSaat, NORMAL_SAAT);
        int fazla = Math.max(calisilanSaat - NORMAL_SAAT, 0);
        return normal * saatUcreti + fazla * saatUcreti * FAZLA_MESAI_KATSAYISI;
    }

    @Override
    protected double kesinti() {
        return brutMaas() < KESINTI_ESIGI ? 0 : super.kesinti();
    }
}
