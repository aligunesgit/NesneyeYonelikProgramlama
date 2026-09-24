package nyp.m12.gozlemci;

/** M12 - Observer: bir siparişin geçebileceği durumlar. */
public enum SiparisDurumu {
    ALINDI,
    HAZIRLANIYOR,
    KARGODA,
    TESLIM_EDILDI,
    IPTAL_EDILDI;

    /** Teslim edilmiş ya da iptal edilmiş sipariş artık değişmez. */
    public boolean sonDurumMu() {
        return this == TESLIM_EDILDI || this == IPTAL_EDILDI;
    }
}
