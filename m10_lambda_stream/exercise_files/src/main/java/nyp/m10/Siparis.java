package nyp.m10;

import java.util.Objects;

/**
 * M10 - Bir e-ticaret siparişi.
 *
 * <p>{@code record} olduğu için değiştirilemez (immutable); stream işlemlerinde güvenle paylaşılır.
 * Erişim metotları ({@code musteri()}, {@code tutar()}...) metot referansı olarak kullanılır:
 * {@code Siparis::tutar}.
 */
public record Siparis(String musteri, String kategori, double tutar, SiparisDurumu durum) {

    public Siparis {
        Objects.requireNonNull(musteri, "musteri");
        Objects.requireNonNull(kategori, "kategori");
        Objects.requireNonNull(durum, "durum");
        if (tutar < 0) {
            throw new IllegalArgumentException("Tutar negatif olamaz: " + tutar);
        }
    }

    /** Sipariş iptal edildiyse true döndürür. Ciro hesaplarında iptaller sayılmaz. */
    public boolean iptalMi() {
        return durum == SiparisDurumu.IPTAL;
    }
}
