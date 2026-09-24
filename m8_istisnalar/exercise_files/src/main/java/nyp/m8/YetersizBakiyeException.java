package nyp.m8;

/**
 * M8 - Bakiyeden fazla para çekilmek istendiğinde fırlatılan kontrollü istisna.
 *
 * <p>Yalnızca mesaj değil, hatayı anlamak için gereken alan verisini de (hesap numarası, bakiye,
 * istenen tutar) taşır. Böylece yakalayan kod mesajı ayrıştırmak zorunda kalmaz.
 */
public class YetersizBakiyeException extends BankaException {

    private final String hesapNo;
    private final double bakiye;
    private final double istenen;

    public YetersizBakiyeException(String hesapNo, double bakiye, double istenen) {
        super("Yetersiz bakiye: hesap " + hesapNo + ", bakiye " + bakiye + ", istenen " + istenen);
        this.hesapNo = hesapNo;
        this.bakiye = bakiye;
        this.istenen = istenen;
    }

    public String getHesapNo() {
        return hesapNo;
    }

    public double getBakiye() {
        return bakiye;
    }

    public double getIstenen() {
        return istenen;
    }

    /** İşlemin yapılabilmesi için eksik olan tutar. */
    public double getEksik() {
        return istenen - bakiye;
    }
}
