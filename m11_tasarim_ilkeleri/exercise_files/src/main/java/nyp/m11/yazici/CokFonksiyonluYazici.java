package nyp.m11.yazici;

/** M11 - Hem yazdırabilen hem tarayabilen yazıcı: iki küçük arayüzü birlikte gerçekler. */
public class CokFonksiyonluYazici implements Yazdirici, Tarayici {

    @Override
    public String yazdir(String belge) {
        return "Yazdırıldı: " + belge;
    }

    @Override
    public String tara(String belge) {
        return belge + ".pdf";
    }
}
