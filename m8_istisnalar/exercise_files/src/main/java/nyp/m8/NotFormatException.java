package nyp.m8;

/**
 * M8 - Not dosyasındaki bir satır beklenen biçimde değilse fırlatılan kontrollü istisna.
 *
 * <p>Hatalı satırın numarasını taşır. Hata bir alt düzey istisnadan (ör.
 * {@link NumberFormatException}) doğduysa, o istisna neden (cause) olarak saklanır.
 */
public class NotFormatException extends Exception {

    private final int satirNo;

    public NotFormatException(int satirNo, String aciklama) {
        super("Satır " + satirNo + ": " + aciklama);
        this.satirNo = satirNo;
    }

    public NotFormatException(int satirNo, String aciklama, Throwable neden) {
        super("Satır " + satirNo + ": " + aciklama, neden);
        this.satirNo = satirNo;
    }

    public int getSatirNo() {
        return satirNo;
    }
}
