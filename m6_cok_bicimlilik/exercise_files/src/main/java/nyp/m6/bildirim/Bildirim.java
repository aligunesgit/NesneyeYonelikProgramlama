package nyp.m6.bildirim;

/**
 * M6 - Bir kullanıcıya gönderilen bildirimlerin üst sınıfı.
 *
 * <p>Her kanal (e-posta, SMS, anlık bildirim) {@link #gonder(String)} ve {@link #maliyet(String)}
 * metotlarını kendine göre ezer.
 */
public class Bildirim {

    private final String alici;

    public Bildirim(String alici) {
        this.alici = alici;
    }

    public String getAlici() {
        return alici;
    }

    /** Mesajı gönderir ve gönderim kaydını döndürür. */
    public String gonder(String mesaj) {
        return "[Bildirim] " + alici + ": " + mesaj;
    }

    /** Bu mesajı göndermenin maliyeti (TL). */
    public double maliyet(String mesaj) {
        return 0;
    }
}
