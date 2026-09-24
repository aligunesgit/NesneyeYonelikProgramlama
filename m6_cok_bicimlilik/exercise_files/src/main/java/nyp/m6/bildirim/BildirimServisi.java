package nyp.m6.bildirim;

import java.util.ArrayList;
import java.util.List;

/**
 * M6 - Aynı mesajı farklı kanallardan gönderir.
 *
 * <p>Servis yalnızca {@link Bildirim} türünü tanır; yeni bir kanal eklemek bu sınıfı değiştirmeyi
 * gerektirmez.
 */
public final class BildirimServisi {

    private BildirimServisi() {
    }

    /** Her bildirim nesnesinin kendi gonder() metodunu çağırır, kayıtları sırayla döndürür. */
    public static List<String> hepsineGonder(List<Bildirim> kanallar, String mesaj) {
        List<String> kayitlar = new ArrayList<>();
        for (Bildirim b : kanallar) {
            kayitlar.add(b.gonder(mesaj));
        }
        return kayitlar;
    }

    /** Tüm kanallardan göndermenin toplam maliyeti. */
    public static double toplamMaliyet(List<Bildirim> kanallar, String mesaj) {
        double toplam = 0;
        for (Bildirim b : kanallar) {
            toplam += b.maliyet(mesaj);
        }
        return toplam;
    }
}
