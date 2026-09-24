package nyp.m6.sekil;

/** M6 - Eni ve boyu verilen dikdörtgen. */
public class Dikdortgen extends Sekil {

    private final double en;
    private final double boy;

    /** Negatif kenar kabul edilmez. */
    public Dikdortgen(double en, double boy) {
        if (en < 0 || boy < 0) {
            throw new IllegalArgumentException("Kenar negatif olamaz");
        }
        this.en = en;
        this.boy = boy;
    }

    public double getEn() {
        return en;
    }

    public double getBoy() {
        return boy;
    }

    /** Eni boyuna eşitse true. Yalnızca dikdörtgenlere özgü bir metot (aşağı dönüşüm örneği). */
    public boolean kareMi() {
        return en == boy;
    }

    @Override
    public double alan() {
        return en * boy;
    }

    @Override
    public double cevre() {
        return 2 * (en + boy);
    }

    @Override
    public String ad() {
        return "Dikdörtgen";
    }
}
