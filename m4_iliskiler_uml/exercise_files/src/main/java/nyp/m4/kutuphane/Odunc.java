package nyp.m4.kutuphane;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * M4 - Bir ödünç alma işleminin kaydı (fişi).
 *
 * <p>Değişmez bir {@code record}: hangi üyenin, hangi kitabı, hangi tarihte aldığını tutar.
 * {@link Kutuphane} bu kaydı üretir ve iade sırasında parametre olarak alır, ama bir alanda
 * saklamaz; bu yüzden {@code Kutuphane} ile {@code Odunc} arasındaki ilişki bir bağımlılıktır.
 */
public record Odunc(Uye uye, Kitap kitap, LocalDate alisTarihi) {

    /** Ödünç süresi (gün). */
    public static final int SURE_GUN = 14;

    public Odunc {
        if (uye == null || kitap == null || alisTarihi == null) {
            throw new IllegalArgumentException("Üye, kitap ve tarih null olamaz");
        }
    }

    /** Kitabın en geç iade edilmesi gereken gün. */
    public LocalDate sonTeslimTarihi() {
        return alisTarihi.plusDays(SURE_GUN);
    }

    /** Verilen iade tarihinde kaç gün gecikme olduğunu döndürür; zamanında iadede 0. */
    public long gecikmeGunu(LocalDate iadeTarihi) {
        long gun = ChronoUnit.DAYS.between(sonTeslimTarihi(), iadeTarihi);
        return Math.max(0, gun);
    }
}
