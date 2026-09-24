package nyp.m11.siparis;

import java.util.Objects;

/**
 * M11 - Bir sipariş: müşteri adı, iletişim adresi (e-posta ya da telefon), kategori ve tutar.
 *
 * <p>Değiştirilemez bir {@code record}; kuralları (indirim, bildirim) başka sınıflar uygular.
 */
public record Siparis(String musteri, String iletisim, String kategori, double tutar) {

    public Siparis {
        Objects.requireNonNull(musteri, "musteri");
        Objects.requireNonNull(iletisim, "iletisim");
        Objects.requireNonNull(kategori, "kategori");
        if (tutar <= 0) {
            throw new IllegalArgumentException("Tutar pozitif olmalı: " + tutar);
        }
    }
}
