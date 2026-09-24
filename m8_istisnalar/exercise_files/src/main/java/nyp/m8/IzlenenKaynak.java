package nyp.m8;

import java.util.List;

/**
 * M8 - Açılışını, kullanımını ve kapanışını bir günlüğe yazan kaynak.
 *
 * <p>{@link AutoCloseable} gerçeklediği için try-with-resources içinde kullanılabilir. Gerçek
 * hayatta bu bir dosya, ağ bağlantısı ya da veritabanı bağlantısı olurdu; burada kapatma sırasını
 * gözlemlemek için yalnızca günlüğe yazıyoruz.
 */
public class IzlenenKaynak implements AutoCloseable {

    private final String ad;
    private final List<String> gunluk;
    private final boolean kapatirkenHataVer;

    public IzlenenKaynak(String ad, List<String> gunluk) {
        this(ad, gunluk, false);
    }

    /** {@code kapatirkenHataVer} doğruysa {@link #close()} bir istisna fırlatır. */
    public IzlenenKaynak(String ad, List<String> gunluk, boolean kapatirkenHataVer) {
        this.ad = ad;
        this.gunluk = gunluk;
        this.kapatirkenHataVer = kapatirkenHataVer;
        gunluk.add(ad + " açıldı");
    }

    public void kullan() {
        gunluk.add(ad + " kullanıldı");
    }

    /** Ezen metot {@code throws Exception} bildirimini daraltabilir; burada hiç bildirmiyoruz. */
    @Override
    public void close() {
        gunluk.add(ad + " kapatıldı");
        if (kapatirkenHataVer) {
            throw new IllegalStateException(ad + " kapatılamadı");
        }
    }
}
