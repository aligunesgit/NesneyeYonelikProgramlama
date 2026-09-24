package nyp.m6.bildirim;

/** M6 - E-posta bildirimi. Ücretsizdir. */
public class EpostaBildirim extends Bildirim {

    public EpostaBildirim(String epostaAdresi) {
        super(epostaAdresi);
    }

    @Override
    public String gonder(String mesaj) {
        return "[E-posta] " + getAlici() + ": " + mesaj;
    }
}
