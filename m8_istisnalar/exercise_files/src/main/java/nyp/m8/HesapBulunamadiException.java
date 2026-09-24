package nyp.m8;

/** M8 - Verilen numarada bir hesap bulunamadığında fırlatılan kontrollü istisna. */
public class HesapBulunamadiException extends BankaException {

    private final String hesapNo;

    public HesapBulunamadiException(String hesapNo) {
        super("Hesap bulunamadı: " + hesapNo);
        this.hesapNo = hesapNo;
    }

    public String getHesapNo() {
        return hesapNo;
    }
}
