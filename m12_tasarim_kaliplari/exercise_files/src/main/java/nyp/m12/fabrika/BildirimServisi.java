package nyp.m12.fabrika;

/**
 * M12 - Factory Method (GoF): ürün oluşturma kararı alt sınıflara bırakılır.
 *
 * <p>{@link #bilgilendir} algoritması her kanal için aynıdır; değişen tek adım hangi
 * {@link Bildirim} nesnesinin oluşturulacağıdır. Bu adım soyut {@link #bildirimOlustur} metodudur.
 */
public abstract class BildirimServisi {

    /** Fabrika metodu: alt sınıf hangi somut bildirimi oluşturacağına karar verir. */
    protected abstract Bildirim bildirimOlustur(String alici);

    public String bilgilendir(String alici, String mesaj) {
        if (mesaj == null || mesaj.isBlank()) {
            throw new IllegalArgumentException("Mesaj boş olamaz");
        }
        Bildirim bildirim = bildirimOlustur(alici);
        return bildirim.gonder(mesaj.strip());
    }
}
