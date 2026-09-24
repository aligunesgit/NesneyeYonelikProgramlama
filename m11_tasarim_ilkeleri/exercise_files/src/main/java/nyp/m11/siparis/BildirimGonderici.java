package nyp.m11.siparis;

/**
 * M11 - Bağımlılığın tersine çevrilmesi (DIP): yüksek seviyeli {@link SiparisServisi} bu soyutlamaya
 * bağımlıdır; e-posta ya da SMS ayrıntısını bilmez.
 */
@FunctionalInterface
public interface BildirimGonderici {

    /** Verilen alıcıya (e-posta adresi, telefon numarası...) mesajı gönderir. */
    void gonder(String alici, String mesaj);
}
