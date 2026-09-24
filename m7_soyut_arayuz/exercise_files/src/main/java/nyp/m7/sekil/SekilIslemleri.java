package nyp.m7.sekil;

/** M7 - Sealed hiyerarşi üzerinde eksiksiz switch örnekleri. */
public final class SekilIslemleri {

    private SekilIslemleri() {
    }

    /** default yok: Sekil sealed olduğu için derleyici üç durumun yeterli olduğunu bilir. */
    public static double alan(Sekil s) {
        return switch (s) {
            case Daire d -> Math.PI * d.yaricap() * d.yaricap();
            case Dikdortgen r -> r.en() * r.boy();
            case Ucgen u -> u.taban() * u.yukseklik() / 2;
        };
    }

    /** Kayıt deseni (record pattern) ve koruma koşulu ile tarif. */
    public static String tarif(Sekil s) {
        return switch (s) {
            case Daire(double r) when r == 0 -> "nokta";
            case Daire(double r) -> "daire, r = " + r;
            case Dikdortgen(double en, double boy) when en == boy -> "kare, kenar = " + en;
            case Dikdortgen(double en, double boy) -> "dikdörtgen, " + en + " x " + boy;
            case Ucgen u -> "üçgen";
        };
    }
}
