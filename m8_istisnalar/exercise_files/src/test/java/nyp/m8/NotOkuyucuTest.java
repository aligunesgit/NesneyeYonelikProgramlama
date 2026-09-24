package nyp.m8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class NotOkuyucuTest {

    @TempDir
    Path geciciKlasor;

    private static BufferedReader okuyucu(String icerik) {
        return new BufferedReader(new StringReader(icerik));
    }

    @Test
    @DisplayName("Geçerli satırlardan notlar okunur, boş satırlar atlanır")
    void gecerliSatirlar() throws Exception {
        List<Integer> notlar = NotOkuyucu.notlariOku(okuyucu("Ayşe;85\n\nMehmet; 70\n"));

        assertEquals(List.of(85, 70), notlar);
    }

    @Test
    @DisplayName("Sayı olmayan not: neden zinciri NumberFormatException'a gider")
    void sayiOlmayanNot() {
        NotFormatException hata = assertThrows(NotFormatException.class,
                () -> NotOkuyucu.notlariOku(okuyucu("Ayşe;85\nZeynep;yetmiş\n")));

        assertEquals(2, hata.getSatirNo());
        assertEquals("Satır 2: Not bir tam sayı değil: yetmiş", hata.getMessage());
        assertInstanceOf(NumberFormatException.class, hata.getCause());
    }

    @Test
    @DisplayName("Aralık dışı notun nedeni yoktur")
    void araliktisiNot() {
        NotFormatException hata = assertThrows(NotFormatException.class,
                () -> NotOkuyucu.notlariOku(okuyucu("Ayşe;101")));

        assertEquals("Satır 1: Not 0-100 aralığında olmalı: 101", hata.getMessage());
        assertNull(hata.getCause());
    }

    @Test
    @DisplayName("Noktalı virgülü olmayan satır reddedilir")
    void hataliBicim() {
        NotFormatException hata = assertThrows(NotFormatException.class,
                () -> NotOkuyucu.notlariOku(okuyucu("Ayşe 85")));

        assertEquals(1, hata.getSatirNo());
    }

    @Test
    @DisplayName("Geçici dosyadan ortalama hesaplanır")
    void dosyadanOrtalama() throws Exception {
        Path dosya = geciciKlasor.resolve("notlar.txt");
        Files.writeString(dosya, "Ayşe;85\nMehmet;70\nZeynep;90\n");

        assertEquals(81.666666666, NotOkuyucu.ortalama(dosya), 1e-6);
    }

    @Test
    @DisplayName("Boş dosyada ortalama hesaplanamaz")
    void bosDosya() throws IOException {
        Path dosya = geciciKlasor.resolve("bos.txt");
        Files.writeString(dosya, "");

        NotFormatException hata =
                assertThrows(NotFormatException.class, () -> NotOkuyucu.ortalama(dosya));
        assertEquals("Satır 0: Dosyada hiç not yok", hata.getMessage());
    }

    @Test
    @DisplayName("Olmayan dosya IOException fırlatır")
    void olmayanDosya() {
        Path dosya = geciciKlasor.resolve("yok.txt");

        assertThrows(IOException.class, () -> NotOkuyucu.ortalama(dosya));
    }
}
