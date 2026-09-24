package nyp.m10;

/**
 * M10 - Katalogdaki bir ürün.
 *
 * <p>{@code UnaryOperator<Urun>} ve kurucu referansı ({@code Urun::new}) örneklerinde kullanılır.
 */
public record Urun(String ad, double fiyat) {

    public Urun {
        if (fiyat < 0) {
            throw new IllegalArgumentException("Fiyat negatif olamaz: " + fiyat);
        }
    }

    /** Fiyatı verilen oranda artırılmış YENİ bir ürün döndürür (ör. 0.10 = yüzde 10 zam). */
    public Urun zamli(double oran) {
        return new Urun(ad, fiyat * (1 + oran));
    }
}
