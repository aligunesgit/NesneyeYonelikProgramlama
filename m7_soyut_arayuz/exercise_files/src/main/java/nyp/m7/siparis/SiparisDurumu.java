package nyp.m7.siparis;

/**
 * M7 - Alanlı, kuruculu ve metotlu enum. Bir arayüzü ({@link Aciklanabilir}) de uygular.
 *
 * <p>Akış: HAZIRLANIYOR → KARGODA → TESLIM_EDILDI. Teslimden önce IPTAL_EDILDI'ye geçilebilir.
 */
public enum SiparisDurumu implements Aciklanabilir {
    HAZIRLANIYOR("Hazırlanıyor", 1),
    KARGODA("Kargoda", 2),
    TESLIM_EDILDI("Teslim edildi", 3),
    IPTAL_EDILDI("İptal edildi", 0);

    private final String aciklama;
    private final int adim;

    SiparisDurumu(String aciklama, int adim) {   // enum kurucusu her zaman private'tır
        this.aciklama = aciklama;
        this.adim = adim;
    }

    @Override
    public String aciklama() {
        return aciklama;
    }

    /** İlerleme çubuğu için adım numarası (iptal için 0). */
    public int getAdim() {
        return adim;
    }

    /** Sipariş son durumuna ulaştıysa true. */
    public boolean bittiMi() {
        return this == TESLIM_EDILDI || this == IPTAL_EDILDI;
    }

    /** Yalnızca kargoya verilmeden önce iptal edilebilir. */
    public boolean iptalEdilebilirMi() {
        return this == HAZIRLANIYOR;
    }

    /** Akıştaki bir sonraki durum; bitmiş durumlar kendisini döndürür. */
    public SiparisDurumu sonraki() {
        return switch (this) {
            case HAZIRLANIYOR -> KARGODA;
            case KARGODA -> TESLIM_EDILDI;
            case TESLIM_EDILDI, IPTAL_EDILDI -> this;
        };
    }
}
