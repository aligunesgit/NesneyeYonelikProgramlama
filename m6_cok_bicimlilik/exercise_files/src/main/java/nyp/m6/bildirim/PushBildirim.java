package nyp.m6.bildirim;

/** M6 - Mobil uygulamaya anlık (push) bildirim. Başlık 50 karakterde kesilir. */
public class PushBildirim extends Bildirim {

    public static final int AZAMI_UZUNLUK = 50;

    public PushBildirim(String cihazKimligi) {
        super(cihazKimligi);
    }

    @Override
    public String gonder(String mesaj) {
        String kisa = mesaj.length() > AZAMI_UZUNLUK ? mesaj.substring(0, AZAMI_UZUNLUK) + "..." : mesaj;
        return "[Push] " + getAlici() + ": " + kisa;
    }
}
