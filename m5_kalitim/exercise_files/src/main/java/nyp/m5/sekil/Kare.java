package nyp.m5.sekil;

/**
 * M5 - "Kare bir dikdörtgendir" sezgisiyle yazılmış, sorunlu bir alt sınıf.
 *
 * <p>Karenin kenarları eşit kalmalı; bu yüzden {@code setEn} ve {@code setBoy} ikisini birden
 * değiştirir. Sonuç: {@link Dikdortgen} bekleyen kodun varsayımı ("eni değiştirmek boyu
 * değiştirmez") bozulur. Ayrıntı ve çözüm M11'de (Liskov yerine geçme ilkesi).
 */
public class Kare extends Dikdortgen {

    public Kare(double kenar) {
        super(kenar, kenar);
    }

    @Override
    public void setEn(double en) {
        super.setEn(en);
        super.setBoy(en);
    }

    @Override
    public void setBoy(double boy) {
        super.setEn(boy);
        super.setBoy(boy);
    }
}
