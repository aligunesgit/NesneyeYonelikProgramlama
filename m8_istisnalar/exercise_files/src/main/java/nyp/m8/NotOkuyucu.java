package nyp.m8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * M8 - "ad;not" biçimindeki satırlardan sınav notlarını okur.
 *
 * <p>Örnek dosya içeriği:
 *
 * <pre>
 * Ayşe;85
 * Mehmet;70
 * </pre>
 */
public final class NotOkuyucu {

    private NotOkuyucu() {
    }

    /**
     * Okuyucudaki tüm satırlardan notları okur; boş satırları atlar. Okuyucuyu kapatmaz: onu açan
     * kod kapatmalıdır.
     *
     * @throws IOException okuma sırasında giriş/çıkış hatası olursa
     * @throws NotFormatException bir satır "ad;not" biçiminde değilse ya da not 0-100 dışındaysa
     */
    public static List<Integer> notlariOku(BufferedReader okuyucu)
            throws IOException, NotFormatException {
        List<Integer> notlar = new ArrayList<>();
        int satirNo = 0;
        String satir;
        while ((satir = okuyucu.readLine()) != null) {
            satirNo++;
            if (!satir.isBlank()) {
                notlar.add(notuAyikla(satir, satirNo));
            }
        }
        return notlar;
    }

    /**
     * Dosyayı açar, notların ortalamasını hesaplar ve dosyayı her durumda kapatır.
     *
     * @throws IOException dosya yoksa ya da okunamıyorsa
     * @throws NotFormatException dosyada hatalı satır varsa ya da hiç not yoksa
     */
    public static double ortalama(Path dosya) throws IOException, NotFormatException {
        try (BufferedReader okuyucu = Files.newBufferedReader(dosya)) {
            List<Integer> notlar = notlariOku(okuyucu);
            if (notlar.isEmpty()) {
                throw new NotFormatException(0, "Dosyada hiç not yok");
            }
            int toplam = 0;
            for (int not : notlar) {
                toplam += not;
            }
            return (double) toplam / notlar.size();
        }
    }

    private static int notuAyikla(String satir, int satirNo) throws NotFormatException {
        String[] parcalar = satir.split(";");
        if (parcalar.length != 2) {
            throw new NotFormatException(satirNo, "Beklenen biçim 'ad;not', bulunan: " + satir);
        }
        String metin = parcalar[1].trim();
        int not;
        try {
            not = Integer.parseInt(metin);
        } catch (NumberFormatException e) {
            throw new NotFormatException(satirNo, "Not bir tam sayı değil: " + metin, e);
        }
        if (not < 0 || not > 100) {
            throw new NotFormatException(satirNo, "Not 0-100 aralığında olmalı: " + not);
        }
        return not;
    }
}
