package nyp.m9;

import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * M9 - Metinlerdeki kelimelerin kaç kez geçtiğini sayar.
 *
 * <p>Sayımlar bir {@link TreeMap}'te tutulur: anahtar kelime, değer sayıdır. {@code TreeMap}
 * anahtarları sıralı tuttuğu için sonuçlar alfabetik (Unicode sırasıyla) listelenir.
 */
public class KelimeSayaci {

    private static final Locale TURKCE = Locale.forLanguageTag("tr-TR");

    private final Map<String, Integer> sayimlar = new TreeMap<>();

    /** Metni harf olmayan karakterlerden böler, küçük harfe çevirir ve her kelimeyi sayar. */
    public void ekle(String metin) {
        for (String parca : metin.split("[^\\p{L}]+")) {
            if (parca.isEmpty()) {
                continue;
            }
            String kelime = parca.toLowerCase(TURKCE);
            int eski = sayimlar.getOrDefault(kelime, 0);
            sayimlar.put(kelime, eski + 1);
        }
    }

    /** Kelimenin kaç kez geçtiği; hiç geçmediyse 0. */
    public int sayi(String kelime) {
        return sayimlar.getOrDefault(kelime.toLowerCase(TURKCE), 0);
    }

    public int farkliKelimeSayisi() {
        return sayimlar.size();
    }

    /**
     * En sık geçen kelime. Eşitlikte alfabetik olarak önce gelen döner.
     *
     * @throws IllegalStateException henüz hiç kelime eklenmediyse
     */
    public String enSikKelime() {
        if (sayimlar.isEmpty()) {
            throw new IllegalStateException("Henüz kelime yok");
        }
        String enSik = null;
        int enBuyukSayi = 0;
        for (Map.Entry<String, Integer> giris : sayimlar.entrySet()) {
            if (giris.getValue() > enBuyukSayi) {
                enSik = giris.getKey();
                enBuyukSayi = giris.getValue();
            }
        }
        return enSik;
    }

    /** Sayımların salt okunur görünümü (unmodifiable view). */
    public Map<String, Integer> sayimlar() {
        return Collections.unmodifiableMap(sayimlar);
    }
}
