package nyp.m8;

/**
 * M8 - Banka alanındaki kontrollü (checked) istisnaların ortak üst sınıfı.
 *
 * <p>Çağıran kod isterse tek bir {@code catch (BankaException e)} ile tüm banka hatalarını, isterse
 * alt sınıfları ayrı ayrı yakalayabilir.
 */
public class BankaException extends Exception {

    /** Yalnızca açıklayıcı mesajla istisna oluşturur. */
    public BankaException(String mesaj) {
        super(mesaj);
    }

    /** Mesaj ve bu istisnaya yol açan asıl nedenle (cause) istisna oluşturur. */
    public BankaException(String mesaj, Throwable neden) {
        super(mesaj, neden);
    }
}
