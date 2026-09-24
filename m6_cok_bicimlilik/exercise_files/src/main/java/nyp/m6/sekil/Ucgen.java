package nyp.m6.sekil;

/** M6 - Üç kenarı verilen üçgen. Alan Heron formülüyle hesaplanır. */
public class Ucgen extends Sekil {

    private final double a;
    private final double b;
    private final double c;

    /** Kenarlar pozitif olmalı ve üçgen eşitsizliğini sağlamalıdır. */
    public Ucgen(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0 || a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("Bu kenarlarla üçgen oluşmaz");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double alan() {
        double s = cevre() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double cevre() {
        return a + b + c;
    }

    @Override
    public String ad() {
        return "Üçgen";
    }
}
