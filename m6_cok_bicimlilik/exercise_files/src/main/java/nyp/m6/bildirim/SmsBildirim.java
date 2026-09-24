package nyp.m6.bildirim;

/** M6 - SMS bildirimi. Her 160 karakterlik parça ayrı ücretlendirilir. */
public class SmsBildirim extends Bildirim {

    public static final int PARCA_UZUNLUGU = 160;
    public static final double PARCA_UCRETI = 0.25;

    public SmsBildirim(String telefon) {
        super(telefon);
    }

    @Override
    public String gonder(String mesaj) {
        return "[SMS] " + getAlici() + ": " + mesaj;
    }

    /** Parça sayısı = tavan(uzunluk / 160); boş mesaj da bir parça sayılır. */
    public int parcaSayisi(String mesaj) {
        if (mesaj.isEmpty()) {
            return 1;
        }
        return (mesaj.length() + PARCA_UZUNLUGU - 1) / PARCA_UZUNLUGU;
    }

    @Override
    public double maliyet(String mesaj) {
        return parcaSayisi(mesaj) * PARCA_UCRETI;
    }
}
