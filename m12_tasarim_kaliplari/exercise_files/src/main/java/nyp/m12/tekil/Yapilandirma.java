package nyp.m12.tekil;

import java.util.HashMap;
import java.util.Map;

/**
 * M12 - Singleton: uygulama yapılandırmasının tek örneği.
 *
 * <p>Tek elemanlı {@code enum}, Java'da tekil yazmanın en güvenli yoludur (Bloch, Effective Java):
 * JVM bu sabitten yalnızca bir nesne oluşturur; yansıma (reflection) ya da serileştirme ile ikinci bir
 * örnek üretilemez.
 */
public enum Yapilandirma implements AyarOkuyucu {
    ORNEK;

    private final Map<String, String> ayarlar = new HashMap<>();

    Yapilandirma() {
        varsayilanlariYukle();
    }

    private void varsayilanlariYukle() {
        ayarlar.clear();
        ayarlar.put("para.birimi", "TRY");
        ayarlar.put("kampanya.esik", "500");
    }

    @Override
    public String oku(String anahtar) {
        String deger = ayarlar.get(anahtar);
        if (deger == null) {
            throw new IllegalArgumentException("Tanımsız ayar: " + anahtar);
        }
        return deger;
    }

    public void ayarla(String anahtar, String deger) {
        ayarlar.put(anahtar, deger);
    }

    /**
     * Varsayılan ayarlara döner. Bu metodun var olması bile bir uyarıdır: tekil, testler arasında
     * paylaşılan küresel durum (global state) taşıdığı için her testten sonra temizlenmesi gerekir.
     */
    public void sifirla() {
        varsayilanlariYukle();
    }
}
