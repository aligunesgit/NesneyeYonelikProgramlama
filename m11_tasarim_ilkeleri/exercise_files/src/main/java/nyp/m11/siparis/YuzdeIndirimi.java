package nyp.m11.siparis;

/** M11 - Tüm siparişlere tutarın belirli bir yüzdesi kadar indirim (ör. 0.10 = yüzde 10). */
public class YuzdeIndirimi implements IndirimKurali {

    private final double oran;

    public YuzdeIndirimi(double oran) {
        if (oran < 0 || oran > 1) {
            throw new IllegalArgumentException("Oran 0 ile 1 arasında olmalı: " + oran);
        }
        this.oran = oran;
    }

    @Override
    public double indirim(Siparis siparis) {
        return siparis.tutar() * oran;
    }
}
