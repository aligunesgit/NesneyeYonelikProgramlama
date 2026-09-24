package nyp.m11.sekil;

/** M11 - Değiştirilemez kare. {@link Dikdortgen}'in alt türü DEĞİLDİR. */
public record Kare(double kenar) implements Sekil {

    public Kare {
        if (kenar <= 0) {
            throw new IllegalArgumentException("Kenar pozitif olmalı");
        }
    }

    @Override
    public double alan() {
        return kenar * kenar;
    }

    /** Aynı ölçülerde bir dikdörtgen olarak görmek gerekirse dönüştürme açıkça yapılır. */
    public Dikdortgen dikdortgeneDonustur() {
        return new Dikdortgen(kenar, kenar);
    }
}
