package nyp.m11.yazici;

/** M11 - Arayüz ayrımı (ISP): yalnızca yazdırma yeteneği. */
public interface Yazdirici {

    /** Belgeyi yazdırır ve yazdırma kaydını döndürür. */
    String yazdir(String belge);
}
