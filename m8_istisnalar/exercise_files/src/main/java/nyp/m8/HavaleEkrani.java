package nyp.m8;

/**
 * M8 - Kullanıcıyla konuşan katman: istisnaları yakalayıp okunabilir mesaja çevirir.
 *
 * <p>{@link Banka} hataları fırlatır, bu sınıf yakalar. İstisnayı, onunla anlamlı bir şey
 * yapabilecek katmanda yakalamak iyi bir alışkanlıktır.
 */
public class HavaleEkrani {

    private final Banka banka;
    private int denemeSayisi;

    public HavaleEkrani(Banka banka) {
        this.banka = banka;
    }

    /** Her hata türünü ayrı bir {@code catch} bloğuyla ele alır. */
    public String havaleYap(String kaynakNo, String hedefNo, double tutar) {
        try {
            banka.havale(kaynakNo, hedefNo, tutar);
            return "Havale tamamlandı";
        } catch (YetersizBakiyeException e) {
            return "Bakiye yetersiz, eksik tutar: " + e.getEksik();
        } catch (HesapBulunamadiException e) {
            return "Hesap bulunamadı: " + e.getHesapNo();
        } catch (IllegalArgumentException e) {
            return "Geçersiz tutar";
        } finally {
            denemeSayisi++;
        }
    }

    /** Aynı işi çoklu yakalama (multi-catch) ile, iki banka hatasını tek blokta ele alarak yapar. */
    public String havaleYapOzet(String kaynakNo, String hedefNo, double tutar) {
        try {
            banka.havale(kaynakNo, hedefNo, tutar);
            return "Havale tamamlandı";
        } catch (YetersizBakiyeException | HesapBulunamadiException e) {
            return "İşlem başarısız: " + e.getMessage();
        } finally {
            denemeSayisi++;
        }
    }

    /** Başarılı ya da başarısız, kaç kez havale denendiği. */
    public int getDenemeSayisi() {
        return denemeSayisi;
    }
}
