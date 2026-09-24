package nyp.m10;

import java.util.ArrayList;
import java.util.List;

/**
 * M10 - Davranışı parametre olarak alan bir metot.
 *
 * <p>{@link #filtrele} "hangi siparişler seçilsin?" sorusunun cevabını bilmez; cevabı çağıran kod
 * bir {@link SiparisFiltresi} olarak verir (ayrı sınıf, anonim sınıf ya da lambda).
 */
public final class SiparisSecici {

    private SiparisSecici() {
    }

    /** Filtreye uyan siparişleri, sıralarını koruyarak yeni bir listede döndürür. */
    public static List<Siparis> filtrele(List<Siparis> siparisler, SiparisFiltresi filtre) {
        List<Siparis> sonuc = new ArrayList<>();
        for (Siparis siparis : siparisler) {
            if (filtre.uygunMu(siparis)) {
                sonuc.add(siparis);
            }
        }
        return sonuc;
    }
}
