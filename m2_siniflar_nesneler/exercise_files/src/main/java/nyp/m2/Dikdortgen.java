package nyp.m2;

/**
 * M2 - Başka bir nesneye (Nokta) referans tutan sınıf.
 *
 * <p>Kurucu, kendisine verilen {@code Nokta} referansını olduğu gibi saklar; kopyalamaz. Bu yüzden
 * aynı noktayla oluşturulan iki dikdörtgen aynı {@code Nokta} nesnesini paylaşır (aliasing). Bu
 * sorunun çözümü olan savunmacı kopyayı M3'te göreceğiz.
 */
public class Dikdortgen {

    private Nokta solAlt;
    private double genislik;
    private double yukseklik;

    /** Sol alt köşesi, genişliği ve yüksekliği verilen dikdörtgen. */
    public Dikdortgen(Nokta solAlt, double genislik, double yukseklik) {
        this.solAlt = solAlt;          // referans kopyalanır, nesne kopyalanmaz
        this.genislik = genislik;
        this.yukseklik = yukseklik;
    }

    /** Sol alt köşesi başlangıç noktasında (0, 0) olan dikdörtgen. */
    public Dikdortgen(double genislik, double yukseklik) {
        this(new Nokta(), genislik, yukseklik);
    }

    public double alan() {
        return genislik * yukseklik;
    }

    public double cevre() {
        return 2 * (genislik + yukseklik);
    }

    /** Dikdörtgeni (dx, dy) kadar kaydırır; bunu sol alt köşe noktasını taşıyarak yapar. */
    public void tasi(double dx, double dy) {
        solAlt.tasi(dx, dy);
    }

    /** Nokta dikdörtgenin içinde ya da kenarı üzerindeyse true döndürür. */
    public boolean icerir(Nokta p) {
        return p.getX() >= solAlt.getX() && p.getX() <= solAlt.getX() + genislik
                && p.getY() >= solAlt.getY() && p.getY() <= solAlt.getY() + yukseklik;
    }

    /** Sol alt köşeyi döndürür. Dikkat: nesnenin kopyası değil, kendisine referans döner. */
    public Nokta getSolAlt() {
        return solAlt;
    }

    public double getGenislik() {
        return genislik;
    }

    public double getYukseklik() {
        return yukseklik;
    }

    @Override
    public String toString() {
        return "Dikdortgen[solAlt=" + solAlt + ", " + genislik + "x" + yukseklik + "]";
    }
}
