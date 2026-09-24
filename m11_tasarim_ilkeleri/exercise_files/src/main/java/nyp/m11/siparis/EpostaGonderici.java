package nyp.m11.siparis;

import java.io.PrintStream;

/**
 * M11 - Düşük seviyeli ayrıntı: e-posta ile bildirim.
 *
 * <p>Gerçek bir e-posta sunucusu yerine mesajı bir çıktı akışına yazar; ders örneği için yeterlidir.
 */
public class EpostaGonderici implements BildirimGonderici {

    private final PrintStream cikis;

    public EpostaGonderici() {
        this(System.out);
    }

    public EpostaGonderici(PrintStream cikis) {
        this.cikis = cikis;
    }

    @Override
    public void gonder(String alici, String mesaj) {
        if (!alici.contains("@")) {
            throw new IllegalArgumentException("Geçersiz e-posta adresi: " + alici);
        }
        cikis.println("[E-posta] " + alici + ": " + mesaj);
    }
}
