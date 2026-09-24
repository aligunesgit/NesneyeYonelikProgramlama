package nyp.m12.fabrika;

/**
 * M12 - Factory: fabrikanın ürettiği ürün (product) arayüzü ve somut ürünler.
 *
 * <p>Arayüz {@code sealed} olduğu için bildirim türleri yalnızca burada sayılanlardır (M7).
 * Somut türler iç içe {@code record} olarak tanımlandı: {@code Bildirim.Eposta}, {@code Bildirim.Sms},
 * {@code Bildirim.Push}.
 */
public sealed interface Bildirim permits Bildirim.Eposta, Bildirim.Sms, Bildirim.Push {

    /** Mesajı gönderir ve gönderimi anlatan bir satır döndürür (gerçek ağ çağrısı yapılmaz). */
    String gonder(String mesaj);

    /** E-posta bildirimi. */
    record Eposta(String adres) implements Bildirim {
        public Eposta {
            if (adres == null || !adres.contains("@")) {
                throw new IllegalArgumentException("Geçersiz e-posta adresi: " + adres);
            }
        }

        @Override
        public String gonder(String mesaj) {
            return "E-posta -> " + adres + ": " + mesaj;
        }
    }

    /** SMS bildirimi; bir SMS en fazla 160 karakterdir. */
    record Sms(String telefon) implements Bildirim {
        public static final int AZAMI_UZUNLUK = 160;

        public Sms {
            if (telefon == null || telefon.isBlank()) {
                throw new IllegalArgumentException("Telefon numarası boş olamaz");
            }
        }

        @Override
        public String gonder(String mesaj) {
            if (mesaj.length() > AZAMI_UZUNLUK) {
                throw new IllegalArgumentException("SMS en fazla " + AZAMI_UZUNLUK + " karakter olabilir");
            }
            return "SMS -> " + telefon + ": " + mesaj;
        }
    }

    /** Mobil uygulama (push) bildirimi. */
    record Push(String cihazKimligi) implements Bildirim {
        public Push {
            if (cihazKimligi == null || cihazKimligi.isBlank()) {
                throw new IllegalArgumentException("Cihaz kimliği boş olamaz");
            }
        }

        @Override
        public String gonder(String mesaj) {
            return "Push -> " + cihazKimligi + ": " + mesaj;
        }
    }
}
