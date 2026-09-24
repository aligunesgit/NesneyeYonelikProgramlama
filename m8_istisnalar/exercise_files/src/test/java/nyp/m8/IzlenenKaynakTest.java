package nyp.m8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IzlenenKaynakTest {

    @Test
    @DisplayName("Kaynaklar açılış sırasının tersiyle kapatılır")
    void tersSiradaKapatma() {
        List<String> gunluk = new ArrayList<>();

        try (IzlenenKaynak a = new IzlenenKaynak("A", gunluk);
                IzlenenKaynak b = new IzlenenKaynak("B", gunluk)) {
            a.kullan();
            b.kullan();
        }

        assertEquals(List.of("A açıldı", "B açıldı", "A kullanıldı", "B kullanıldı",
                "B kapatıldı", "A kapatıldı"), gunluk);
    }

    @Test
    @DisplayName("Gövdede istisna olsa da kaynak catch'ten önce kapatılır")
    void istisnadaDaKapatilir() {
        List<String> gunluk = new ArrayList<>();

        try (IzlenenKaynak k = new IzlenenKaynak("K", gunluk)) {
            k.kullan();
            throw new IllegalStateException("gövde hatası");
        } catch (IllegalStateException e) {
            gunluk.add("yakalandı: " + e.getMessage());
        }

        assertEquals(List.of("K açıldı", "K kullanıldı", "K kapatıldı", "yakalandı: gövde hatası"),
                gunluk);
    }

    @Test
    @DisplayName("close() da hata verirse o hata bastırılmış (suppressed) olarak eklenir")
    void bastirilmisIstisna() {
        List<String> gunluk = new ArrayList<>();

        IllegalArgumentException hata = assertThrows(IllegalArgumentException.class, () -> {
            try (IzlenenKaynak k = new IzlenenKaynak("K", gunluk, true)) {
                k.kullan();
                throw new IllegalArgumentException("gövde hatası");
            }
        });

        assertEquals("gövde hatası", hata.getMessage());
        assertEquals(1, hata.getSuppressed().length);
        assertInstanceOf(IllegalStateException.class, hata.getSuppressed()[0]);
        assertEquals("K kapatılamadı", hata.getSuppressed()[0].getMessage());
    }
}
