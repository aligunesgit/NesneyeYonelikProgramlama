package nyp.m6.sekil;

/** M6 - Yarıçapı verilen daire. */
public class Daire extends Sekil {

    private final double yaricap;

    /** Negatif yarıçap kabul edilmez. */
    public Daire(double yaricap) {
        if (yaricap < 0) {
            throw new IllegalArgumentException("Yarıçap negatif olamaz: " + yaricap);
        }
        this.yaricap = yaricap;
    }

    public double getYaricap() {
        return yaricap;
    }

    @Override
    public double alan() {
        return Math.PI * yaricap * yaricap;
    }

    @Override
    public double cevre() {
        return 2 * Math.PI * yaricap;
    }

    @Override
    public String ad() {
        return "Daire";
    }
}
