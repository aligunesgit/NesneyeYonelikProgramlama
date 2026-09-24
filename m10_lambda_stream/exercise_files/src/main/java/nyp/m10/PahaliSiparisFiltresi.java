package nyp.m10;

/**
 * M10 - Davranışı ayrı bir sınıfla tanımlamak (lambda öncesi en uzun yol).
 *
 * <p>Tutarı eşiğe eşit ya da eşikten büyük siparişleri seçer.
 */
public class PahaliSiparisFiltresi implements SiparisFiltresi {

    private final double esik;

    public PahaliSiparisFiltresi(double esik) {
        this.esik = esik;
    }

    @Override
    public boolean uygunMu(Siparis siparis) {
        return siparis.tutar() >= esik;
    }
}
