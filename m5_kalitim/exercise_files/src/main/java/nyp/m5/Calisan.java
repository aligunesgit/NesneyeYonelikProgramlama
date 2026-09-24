package nyp.m5;

import java.util.Objects;

/**
 * M5 - Tüm çalışanların ortak üst sınıfı.
 *
 * <p>Her çalışanın bir sicil numarası ve adı vardır. İki çalışan, sicil numaraları aynıysa eşittir
 * ({@link #equals}); {@link #hashCode} bu sözleşmeye uyar. İki metot da {@code final}: alt sınıflar
 * eşitlik kuralını değiştiremez. Ücret hesabı her çalışan türünde farklıdır; alt sınıflar
 * {@link #aylikUcret()} metodunu ezer (override).
 */
public class Calisan {

    private final String sicilNo;
    private final String ad;

    public Calisan(String sicilNo, String ad) {
        if (sicilNo == null || sicilNo.isBlank()) {
            throw new IllegalArgumentException("Sicil numarası boş olamaz");
        }
        if (ad == null || ad.isBlank()) {
            throw new IllegalArgumentException("Ad boş olamaz");
        }
        this.sicilNo = sicilNo;
        this.ad = ad;
    }

    /**
     * Aylık brüt ücret (TL). Genel bir çalışanın ücret kuralı yoktur; alt sınıflar bu metodu ezer.
     * (M7'de bu metodu soyut, abstract, yapacağız.)
     */
    public double aylikUcret() {
        return 0.0;
    }

    /**
     * Alt sınıfların kurucularında kullanması için doğrulama yardımcısı.
     *
     * @return değer pozitifse kendisi
     * @throws IllegalArgumentException değer sıfır ya da negatifse
     */
    protected static double pozitifOlmali(double deger, String alanAdi) {
        if (deger <= 0) {
            throw new IllegalArgumentException(alanAdi + " pozitif olmalı: " + deger);
        }
        return deger;
    }

    public String getSicilNo() {
        return sicilNo;
    }

    public String getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return sicilNo + " " + ad;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Calisan diger)) {
            return false;
        }
        return sicilNo.equals(diger.sicilNo);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(sicilNo);
    }
}
