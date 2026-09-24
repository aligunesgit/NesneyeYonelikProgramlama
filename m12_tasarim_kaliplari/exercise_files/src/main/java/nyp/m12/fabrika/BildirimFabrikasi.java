package nyp.m12.fabrika;

import java.util.Locale;
import java.util.Objects;

/**
 * M12 - Basit fabrika (simple factory): hangi somut {@link Bildirim} sınıfının oluşturulacağına
 * tek bir yerde karar verir. İstemci kod yalnızca {@code Bildirim} arayüzünü ve {@link Kanal}'ı bilir.
 */
public final class BildirimFabrikasi {

    private BildirimFabrikasi() {
        // Yalnızca statik metotlar; nesnesi oluşturulmaz.
    }

    public static Bildirim olustur(Kanal kanal, String alici) {
        Objects.requireNonNull(kanal, "Kanal null olamaz");
        return switch (kanal) {
            case EPOSTA -> new Bildirim.Eposta(alici);
            case SMS -> new Bildirim.Sms(alici);
            case PUSH -> new Bildirim.Push(alici);
        };
    }

    /**
     * Kanalı metinden okur (ör. yapılandırma dosyasındaki {@code "sms"}). Büyük/küçük harf önemsizdir.
     *
     * @throws IllegalArgumentException kanal adı tanınmıyorsa
     */
    public static Bildirim olustur(String kanalAdi, String alici) {
        Kanal kanal = Kanal.valueOf(kanalAdi.trim().toUpperCase(Locale.ROOT));
        return olustur(kanal, alici);
    }
}
