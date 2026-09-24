package nyp.m2;

/**
 * M2 - Düzlemde bir nokta: varsayılan kurucu, aşırı yüklenmiş kurucular ve {@code this(...)} ile
 * kurucu zincirleme.
 *
 * <p>Bu sınıf {@code equals} metodunu kendisi tanımlamaz; bu yüzden aynı koordinatlı iki farklı
 * {@code Nokta} nesnesi için {@code equals} false döndürür (ayrıntısı M5).
 */
public class Nokta {

    private double x;
    private double y;

    /** Başlangıç noktası (0, 0). */
    public Nokta() {
        this(0, 0);
    }

    /** Koordinatları verilen noktayı oluşturur. */
    public Nokta(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** Kopya kurucu: verilen noktayla aynı koordinatlarda, ama ondan bağımsız yeni bir nokta. */
    public Nokta(Nokta diger) {
        this(diger.x, diger.y);
    }

    /** Noktayı (dx, dy) kadar kaydırır. Bu nesneyi gösteren tüm referanslar değişikliği görür. */
    public void tasi(double dx, double dy) {
        x = x + dx;
        y = y + dy;
    }

    /** Bu noktanın {@code diger} noktaya Öklid uzaklığı. */
    public double uzaklik(Nokta diger) {
        return Math.hypot(x - diger.x, y - diger.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
