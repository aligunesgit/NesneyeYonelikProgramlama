package nyp.m10;

/**
 * M10 - Kendi fonksiyonel arayüzümüz: bir siparişin bir koşula uyup uymadığını söyler.
 *
 * <p>Tek soyut metodu olduğu için lambda ifadesiyle ya da metot referansıyla gerçeklenebilir.
 * {@code @FunctionalInterface} ikinci bir soyut metot eklenirse derleme hatası verdirir.
 */
@FunctionalInterface
public interface SiparisFiltresi {

    boolean uygunMu(Siparis siparis);

    /** Bu filtre VE verilen filtre sağlanıyorsa true döndüren yeni bir filtre üretir. */
    default SiparisFiltresi ve(SiparisFiltresi diger) {
        return siparis -> this.uygunMu(siparis) && diger.uygunMu(siparis);
    }
}
