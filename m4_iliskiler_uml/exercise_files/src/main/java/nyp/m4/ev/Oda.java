package nyp.m4.ev;

/**
 * M4 - Bir evin odası (bileşimin parçası).
 *
 * <p>Kurucu paket içidir: bu paketin dışındaki kod {@code new Oda(...)} yazamaz. Odaları yalnızca
 * {@link Ev} yaratır; böylece bir oda hiçbir zaman evsiz ya da iki evin birden parçası olamaz.
 */
public class Oda {

    private final String ad;
    private final double en;
    private final double boy;

    Oda(String ad, double en, double boy) {
        if (ad == null || ad.isBlank()) {
            throw new IllegalArgumentException("Oda adı boş olamaz");
        }
        if (en <= 0 || boy <= 0) {
            throw new IllegalArgumentException("Oda boyutları pozitif olmalı");
        }
        this.ad = ad;
        this.en = en;
        this.boy = boy;
    }

    public double alan() {
        return en * boy;
    }

    public String getAd() {
        return ad;
    }
}
