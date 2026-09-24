package nyp.m11.siparis;

import java.io.PrintStream;

/** M11 - Düşük seviyeli ayrıntı: SMS ile bildirim (mesajı bir çıktı akışına yazar). */
public class SmsGonderici implements BildirimGonderici {

    private final PrintStream cikis;

    public SmsGonderici() {
        this(System.out);
    }

    public SmsGonderici(PrintStream cikis) {
        this.cikis = cikis;
    }

    @Override
    public void gonder(String alici, String mesaj) {
        if (alici.isEmpty() || !alici.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Geçersiz telefon numarası: " + alici);
        }
        cikis.println("[SMS] " + alici + ": " + mesaj);
    }
}
