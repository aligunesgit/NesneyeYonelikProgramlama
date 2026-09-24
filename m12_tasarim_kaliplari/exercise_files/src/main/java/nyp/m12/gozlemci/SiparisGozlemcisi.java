package nyp.m12.gozlemci;

/**
 * M12 - Observer kalıbında gözlemci (observer) arayüzü.
 *
 * <p>Sipariş durumu her değiştiğinde abone olan tüm gözlemcilerin bu metodu çağrılır.
 */
@FunctionalInterface
public interface SiparisGozlemcisi {

    void durumDegisti(Siparis siparis, SiparisDurumu eski, SiparisDurumu yeni);
}
