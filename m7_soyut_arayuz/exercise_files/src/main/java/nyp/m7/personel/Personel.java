package nyp.m7.personel;

/**
 * M7 - Tüm personel türlerinin soyut üst sınıfı.
 *
 * <p>{@code abstract} olduğu için {@code new Personel(...)} yazılamaz. Ortak durum (ad) ve ortak
 * davranış (net maaş hesabı) burada; türe göre değişen kısım ({@link #brutMaas()}) alt sınıflara
 * bırakılmıştır. {@link #netMaas()} bir <b>şablon metottur</b> (Template Method, ayrıntısı M12):
 * algoritmanın iskeletini sabitler, adımları alt sınıflara yaptırır.
 */
public abstract class Personel {

    public static final double VERGI_ORANI = 0.15;

    private final String ad;

    protected Personel(String ad) {
        this.ad = ad;
    }

    public String getAd() {
        return ad;
    }

    /** Türe göre değişen adım: her alt sınıf kendi brüt maaşını hesaplar. */
    public abstract double brutMaas();

    /** Varsayılan kesinti: brütün %15'i. Alt sınıflar isterse ezebilir (kanca metot). */
    protected double kesinti() {
        return brutMaas() * VERGI_ORANI;
    }

    /** Şablon metot: iskelet sabit, final olduğu için ezilemez. */
    public final double netMaas() {
        return brutMaas() - kesinti();
    }
}
