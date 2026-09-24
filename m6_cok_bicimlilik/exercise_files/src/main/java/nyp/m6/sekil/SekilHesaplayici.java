package nyp.m6.sekil;

import java.util.List;

/**
 * M6 - Heterojen şekil listeleri üzerinde hesaplar.
 *
 * <p>{@link #toplamAlanInstanceofIle(List)} kötü örnektir: her yeni şekil türü için bu metodun
 * değişmesi gerekir. {@link #toplamAlan(List)} ise çok biçimli çağrı kullanır ve yeni türlerden
 * etkilenmez.
 */
public final class SekilHesaplayici {

    private SekilHesaplayici() {
    }

    /** KÖTÜ SÜRÜM: tür sorup her türün alanını burada hesaplar. */
    public static double toplamAlanInstanceofIle(List<Sekil> sekiller) {
        double toplam = 0;
        for (Sekil s : sekiller) {
            if (s instanceof Daire) {
                Daire d = (Daire) s;
                toplam += Math.PI * d.getYaricap() * d.getYaricap();
            } else if (s instanceof Dikdortgen) {
                Dikdortgen r = (Dikdortgen) s;
                toplam += r.getEn() * r.getBoy();
            }
            // Ucgen unutuldu! Derleyici uyarmaz, sonuç sessizce yanlış olur.
        }
        return toplam;
    }

    /** İYİ SÜRÜM: her nesneye alanını sorar; hangi alan() metodunun çalışacağına JVM karar verir. */
    public static double toplamAlan(List<Sekil> sekiller) {
        double toplam = 0;
        for (Sekil s : sekiller) {
            toplam += s.alan();
        }
        return toplam;
    }

    /** Alanı en büyük şekli döndürür; liste boşsa null. */
    public static Sekil enBuyuk(List<Sekil> sekiller) {
        Sekil enBuyuk = null;
        for (Sekil s : sekiller) {
            if (enBuyuk == null || s.alan() > enBuyuk.alan()) {
                enBuyuk = s;
            }
        }
        return enBuyuk;
    }

    /** Listedeki karelerin sayısı: instanceof desen eşleme ile güvenli aşağı dönüşüm. */
    public static int kareSayisi(List<Sekil> sekiller) {
        int sayac = 0;
        for (Sekil s : sekiller) {
            if (s instanceof Dikdortgen r && r.kareMi()) {
                sayac++;
            }
        }
        return sayac;
    }

    /** switch ile desen eşleme (JEP 441). Sekil sealed olmadığı için default şarttır. */
    public static String tarif(Sekil s) {
        return switch (s) {
            case null -> "şekil yok";
            case Daire d -> "yarıçapı " + d.getYaricap() + " olan daire";
            case Dikdortgen r when r.kareMi() -> "kenarı " + r.getEn() + " olan kare";
            case Dikdortgen r -> r.getEn() + " x " + r.getBoy() + " dikdörtgen";
            default -> s.ad();
        };
    }
}
