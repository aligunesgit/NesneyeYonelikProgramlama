package nyp.m3;

/**
 * M3 - Savunmacı kopya (defensive copy).
 *
 * <p>{@code notlar} alanı {@code final} olsa da dizinin içeriği değiştirilebilir. Dışarıdan gelen
 * diziyi olduğu gibi saklamak ya da dizinin kendisini dışarı vermek, sınıfın değişmezini (her not
 * 0-100 aralığında) dışarıdaki koda teslim etmek olurdu. Bu yüzden kurucu ve getter kopya kullanır.
 */
public final class SinavNotlari {

    private final String dersKodu;
    private final int[] notlar;

    /** Ders kodu ve notlarla oluşturur. Not listesi boşsa ya da 0-100 dışında not varsa IAE. */
    public SinavNotlari(String dersKodu, int[] notlar) {
        int[] kopya = notlar.clone();          // önce kopyala, sonra kopyayı doğrula
        if (kopya.length == 0) {
            throw new IllegalArgumentException("En az bir not olmalı");
        }
        for (int not : kopya) {
            if (not < 0 || not > 100) {
                throw new IllegalArgumentException("Not 0-100 aralığında olmalı: " + not);
            }
        }
        this.dersKodu = dersKodu;
        this.notlar = kopya;
    }

    /** Notların kopyasını döndürür; dönen diziyi değiştirmek bu nesneyi etkilemez. */
    public int[] getNotlar() {
        return notlar.clone();
    }

    public double ortalama() {
        return Istatistik.ortalama(notlar);
    }

    public int enYuksek() {
        return Istatistik.enBuyuk(notlar);
    }

    public int notSayisi() {
        return notlar.length;
    }

    public String getDersKodu() {
        return dersKodu;
    }
}
