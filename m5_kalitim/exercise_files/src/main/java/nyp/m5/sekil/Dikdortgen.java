package nyp.m5.sekil;

/** M5 - Eni ve boyu ayrı ayrı değiştirilebilen bir dikdörtgen. */
public class Dikdortgen {

    private double en;
    private double boy;

    public Dikdortgen(double en, double boy) {
        this.en = pozitif(en);    // kurucuda ezilebilir setEn'i çağırmıyoruz (bkz. README §6)
        this.boy = pozitif(boy);
    }

    public void setEn(double en) {
        this.en = pozitif(en);
    }

    public void setBoy(double boy) {
        this.boy = pozitif(boy);
    }

    public double getEn() {
        return en;
    }

    public double getBoy() {
        return boy;
    }

    public double alan() {
        return en * boy;
    }

    private static double pozitif(double deger) {
        if (deger <= 0) {
            throw new IllegalArgumentException("Kenar uzunluğu pozitif olmalı: " + deger);
        }
        return deger;
    }
}
