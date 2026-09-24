package nyp.m7.personel;

/** M7 - Sabit aylık maaşlı personel. */
public class TamZamanliPersonel extends Personel {

    private final double aylikMaas;

    public TamZamanliPersonel(String ad, double aylikMaas) {
        super(ad);
        this.aylikMaas = aylikMaas;
    }

    @Override
    public double brutMaas() {
        return aylikMaas;
    }
}
