package nyp.m11.siparis;

import java.util.Locale;
import java.util.Objects;

/**
 * M11 - Yüksek seviyeli iş akışı: indirimi hesapla, müşteriye bildir.
 *
 * <p>Bağımlılıklarını kendisi oluşturmaz ({@code new EpostaGonderici()} yazmaz); kurucu ile alır
 * (kurucu ile bağımlılık enjeksiyonu, constructor injection). Böylece testte sahte bir gönderici
 * verilebilir.
 */
public class SiparisServisi {

    private final IndirimHesaplayici hesaplayici;
    private final BildirimGonderici gonderici;

    public SiparisServisi(IndirimHesaplayici hesaplayici, BildirimGonderici gonderici) {
        this.hesaplayici = Objects.requireNonNull(hesaplayici, "hesaplayici");
        this.gonderici = Objects.requireNonNull(gonderici, "gonderici");
    }

    /** Siparişin net tutarını hesaplar, müşteriye bildirim gönderir ve net tutarı döndürür. */
    public double siparisVer(Siparis siparis) {
        double net = hesaplayici.netTutar(siparis);
        gonderici.gonder(siparis.iletisim(), mesajOlustur(siparis, net));
        return net;
    }

    static String mesajOlustur(Siparis siparis, double net) {
        return String.format(Locale.ROOT, "Sayın %s, siparişiniz alındı. Ödenecek tutar: %.2f TL",
                siparis.musteri(), net);
    }
}
