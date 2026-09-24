package nyp.m3;

import java.util.Locale;

/**
 * M3 - {@code record} ile değişmez bir veri sınıfı (JEP 395).
 *
 * <p>Tutar kuruş cinsinden {@code long} olarak tutulur; böylece {@code double}'ın yuvarlama
 * sorunları yaşanmaz (M1'deki uyarı). Kompakt kurucu doğrulama ve normalleştirme yapar.
 * {@code equals}, {@code hashCode}, {@code toString} ve erişimci metotları derleyici üretir.
 */
public record Para(long kurus, String paraBirimi) {

    /** Kompakt kurucu: parametreler alanlara atanmadan önce çalışır. */
    public Para {
        if (kurus < 0) {
            throw new IllegalArgumentException("Tutar negatif olamaz: " + kurus);
        }
        if (paraBirimi == null || paraBirimi.length() != 3) {
            throw new IllegalArgumentException("Para birimi üç harfli bir kod olmalı: " + paraBirimi);
        }
        paraBirimi = paraBirimi.toUpperCase(Locale.ROOT);
    }

    /** Türk lirası için static fabrika metodu: {@code Para.tl(12, 50)} = 12,50 TRY. */
    public static Para tl(long lira, int kurus) {
        if (kurus < 0 || kurus > 99) {
            throw new IllegalArgumentException("Kuruş 0-99 aralığında olmalı: " + kurus);
        }
        return new Para(lira * 100 + kurus, "TRY");
    }

    /** İki tutarın toplamını yeni bir Para olarak döndürür. Para birimleri farklıysa IAE. */
    public Para topla(Para diger) {
        ayniBirimOlmali(diger);
        return new Para(kurus + diger.kurus, paraBirimi);
    }

    /** Farkı yeni bir Para olarak döndürür. Sonuç negatif olursa kompakt kurucu IAE fırlatır. */
    public Para cikar(Para diger) {
        ayniBirimOlmali(diger);
        return new Para(kurus - diger.kurus, paraBirimi);
    }

    /** Ör. "12,50 TRY". */
    public String bicimli() {
        return String.format("%d,%02d %s", kurus / 100, kurus % 100, paraBirimi);
    }

    private void ayniBirimOlmali(Para diger) {
        if (!paraBirimi.equals(diger.paraBirimi)) {
            throw new IllegalArgumentException(
                    "Farklı para birimleri: " + paraBirimi + " ve " + diger.paraBirimi);
        }
    }
}
