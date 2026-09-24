package nyp.m11.siparis;

import java.util.ArrayList;
import java.util.List;

/**
 * M11 - Testler için elle yazılmış sahte (fake) gönderici.
 *
 * <p>Gerçekten e-posta ya da SMS göndermez; kendisine verilen alıcı ve mesajları bir listede
 * saklar. Test, servisin "doğru kişiye doğru mesajı gönderip göndermediğini" bu listeden okur.
 */
class SahteBildirimGonderici implements BildirimGonderici {

    record Gonderim(String alici, String mesaj) {
    }

    private final List<Gonderim> gonderimler = new ArrayList<>();

    @Override
    public void gonder(String alici, String mesaj) {
        gonderimler.add(new Gonderim(alici, mesaj));
    }

    List<Gonderim> getGonderimler() {
        return gonderimler;
    }
}
