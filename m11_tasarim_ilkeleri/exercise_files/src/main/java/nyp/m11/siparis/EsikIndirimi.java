package nyp.m11.siparis;

/** M11 - Tutar eşiğe eşit ya da eşikten büyükse sabit bir indirim (ör. 1000 TL üzerine 100 TL). */
public class EsikIndirimi implements IndirimKurali {

    private final double esik;
    private final double indirimTutari;

    public EsikIndirimi(double esik, double indirimTutari) {
        this.esik = esik;
        this.indirimTutari = indirimTutari;
    }

    @Override
    public double indirim(Siparis siparis) {
        return siparis.tutar() >= esik ? indirimTutari : 0.0;
    }
}
