package nyp.m11.yazici;

/** M11 - Arayüz ayrımı (ISP): yalnızca tarama yeteneği. */
public interface Tarayici {

    /** Kâğıttaki belgeyi tarar ve dijital kopyasının adını döndürür. */
    String tara(String belge);
}
