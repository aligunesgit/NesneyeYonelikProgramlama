package nyp.m12.gozlemci;

import java.util.ArrayList;
import java.util.List;

/** M12 - Observer: her durum değişikliğini bir kayıt satırı olarak saklayan somut gözlemci. */
public class LogGozlemcisi implements SiparisGozlemcisi {

    private final List<String> kayitlar = new ArrayList<>();

    @Override
    public void durumDegisti(Siparis siparis, SiparisDurumu eski, SiparisDurumu yeni) {
        kayitlar.add(siparis.getSiparisNo() + ": " + eski + " -> " + yeni);
    }

    public List<String> getKayitlar() {
        return List.copyOf(kayitlar);
    }
}
