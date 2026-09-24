package nyp.m11.siparis;

/**
 * M11 - Açık/kapalı ilkesi (OCP): her indirim türü bu arayüzü gerçekleyen ayrı bir sınıftır.
 *
 * <p>Yeni bir indirim türü eklemek için mevcut kod değiştirilmez; yeni bir gerçekleme (ya da
 * fonksiyonel arayüz olduğu için bir lambda, M10) yazılır.
 */
@FunctionalInterface
public interface IndirimKurali {

    /** Bu kurala göre siparişe uygulanacak indirim tutarı (TL). Uygulanmıyorsa 0. */
    double indirim(Siparis siparis);
}
