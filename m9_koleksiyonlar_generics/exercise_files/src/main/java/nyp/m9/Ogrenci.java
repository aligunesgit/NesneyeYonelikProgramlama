package nyp.m9;

import java.util.Comparator;
import java.util.Objects;

/**
 * M9 - Öğrenci numarasıyla tanınan öğrenci.
 *
 * <p>İki öğrenci numaraları aynıysa eşittir; {@code equals} ve {@code hashCode} buna göre ezilmiştir
 * (M5). Bu sayede {@code HashSet} ve {@code HashMap} içinde doğru davranır. Doğal sıralaması
 * (natural ordering) numaraya göredir.
 */
public class Ogrenci implements Comparable<Ogrenci> {

    /**
     * Ortalamaya göre büyükten küçüğe sıralayan karşılaştırıcı. Anonim sınıf olarak yazılmıştır;
     * aynı şeyin lambda ile kısa yazımı M10'da.
     */
    public static final Comparator<Ogrenci> ORTALAMAYA_GORE_AZALAN = new Comparator<>() {
        @Override
        public int compare(Ogrenci a, Ogrenci b) {
            return Double.compare(b.ortalama, a.ortalama);
        }
    };

    private final String numara;
    private final String ad;
    private final double ortalama;

    public Ogrenci(String numara, String ad, double ortalama) {
        if (numara == null || numara.isBlank()) {
            throw new IllegalArgumentException("Numara boş olamaz");
        }
        if (ortalama < 0 || ortalama > 4) {
            throw new IllegalArgumentException("Ortalama 0-4 aralığında olmalı: " + ortalama);
        }
        this.numara = numara;
        this.ad = ad;
        this.ortalama = ortalama;
    }

    public String getNumara() {
        return numara;
    }

    public String getAd() {
        return ad;
    }

    public double getOrtalama() {
        return ortalama;
    }

    /** Doğal sıralama: numaraya göre küçükten büyüğe. */
    @Override
    public int compareTo(Ogrenci diger) {
        return numara.compareTo(diger.numara);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ogrenci diger)) {
            return false;
        }
        return numara.equals(diger.numara);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numara);
    }

    @Override
    public String toString() {
        return ad + " (" + numara + ")";
    }
}
