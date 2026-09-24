package nyp.m11.yazici;

/**
 * M11 - Yalnızca yazdırabilen ucuz bir yazıcı.
 *
 * <p>Şişkin bir {@code Yazici} arayüzü olsaydı {@code tara} ve {@code faksGonder} metotlarını boş
 * bırakmak ya da {@code UnsupportedOperationException} fırlatmak zorunda kalırdı.
 */
public class BasitYazici implements Yazdirici {

    private int yazdirilanSayisi;

    @Override
    public String yazdir(String belge) {
        yazdirilanSayisi++;
        return "Yazdırıldı: " + belge;
    }

    public int getYazdirilanSayisi() {
        return yazdirilanSayisi;
    }
}
