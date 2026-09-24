package nyp.m12.gozlemci;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * M12 - Observer kalıbında özne (subject): durumu izlenen sipariş.
 *
 * <p>Sipariş, kendisini kimin dinlediğini yalnızca {@link SiparisGozlemcisi} arayüzü üzerinden bilir.
 * E-posta, SMS ya da log gibi somut gözlemcileri tanımaz; yeni bir gözlemci eklemek bu sınıfı
 * değiştirmeyi gerektirmez.
 */
public class Siparis {

    private final String siparisNo;
    private SiparisDurumu durum = SiparisDurumu.ALINDI;
    private final List<SiparisGozlemcisi> gozlemciler = new ArrayList<>();

    public Siparis(String siparisNo) {
        if (siparisNo == null || siparisNo.isBlank()) {
            throw new IllegalArgumentException("Sipariş numarası boş olamaz");
        }
        this.siparisNo = siparisNo;
    }

    public void aboneEkle(SiparisGozlemcisi gozlemci) {
        gozlemciler.add(Objects.requireNonNull(gozlemci));
    }

    /** Gözlemciyi listeden çıkarır; listede varsa true döndürür. */
    public boolean aboneCikar(SiparisGozlemcisi gozlemci) {
        return gozlemciler.remove(gozlemci);
    }

    /**
     * Durumu değiştirir ve tüm gözlemcileri bilgilendirir. Aynı duruma geçişte kimse bilgilendirilmez.
     *
     * @throws IllegalStateException sipariş son durumdaysa (teslim edildi / iptal edildi)
     */
    public void durumGuncelle(SiparisDurumu yeni) {
        Objects.requireNonNull(yeni);
        if (yeni == durum) {
            return;
        }
        if (durum.sonDurumMu()) {
            throw new IllegalStateException(siparisNo + " zaten " + durum + " durumunda");
        }
        SiparisDurumu eski = durum;
        durum = yeni;
        bildir(eski, yeni);
    }

    private void bildir(SiparisDurumu eski, SiparisDurumu yeni) {
        // Kopya üzerinde dolaşıyoruz: bir gözlemci bildirim sırasında abonelikten çıkarsa liste bozulmaz.
        for (SiparisGozlemcisi gozlemci : List.copyOf(gozlemciler)) {
            gozlemci.durumDegisti(this, eski, yeni);
        }
    }

    public String getSiparisNo() {
        return siparisNo;
    }

    public SiparisDurumu getDurum() {
        return durum;
    }
}
