package nyp.m11.sekil;

/** M11 - Değiştirilemez dikdörtgen. Boyut değişikliği yeni bir nesne döndürür. */
public record Dikdortgen(double genislik, double yukseklik) implements Sekil {

    public Dikdortgen {
        if (genislik <= 0 || yukseklik <= 0) {
            throw new IllegalArgumentException("Kenarlar pozitif olmalı");
        }
    }

    @Override
    public double alan() {
        return genislik * yukseklik;
    }

    /** Genişliği değiştirilmiş yeni bir dikdörtgen; yükseklik aynı kalır. */
    public Dikdortgen genislikIle(double yeniGenislik) {
        return new Dikdortgen(yeniGenislik, yukseklik);
    }
}
