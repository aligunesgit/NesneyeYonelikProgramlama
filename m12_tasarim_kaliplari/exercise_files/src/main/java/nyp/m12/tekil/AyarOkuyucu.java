package nyp.m12.tekil;

/**
 * M12 - Singleton: ayar okuma soyutlaması.
 *
 * <p>Sınıflar {@link Yapilandirma#ORNEK} tekiline doğrudan değil bu arayüze bağımlı olursa (M11,
 * bağımlılığın tersine çevrilmesi) testlerde tekil yerine basit bir lambda verilebilir.
 */
@FunctionalInterface
public interface AyarOkuyucu {

    /**
     * Anahtarın değerini döndürür.
     *
     * @throws IllegalArgumentException anahtar tanımlı değilse
     */
    String oku(String anahtar);
}
