package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OgrenciTest {

    @Test
    @DisplayName("Aynı numaralı iki öğrenci eşittir ve hash kodları aynıdır")
    void ayniNumaraEsit() {
        Ogrenci a = new Ogrenci("2024001", "Ece", 3.5);
        Ogrenci b = new Ogrenci("2024001", "Ece Yılmaz", 3.6);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("HashSet aynı numaralı öğrenciyi ikinci kez eklemez")
    void hashSetTekrarEklemez() {
        Set<Ogrenci> kume = new HashSet<>();

        assertTrue(kume.add(new Ogrenci("2024001", "Ece", 3.5)));
        assertFalse(kume.add(new Ogrenci("2024001", "Ece", 3.5)));
        assertEquals(1, kume.size());
        assertTrue(kume.contains(new Ogrenci("2024001", "Ece", 3.5)));
    }

    @Test
    @DisplayName("equals/hashCode ezilmezse HashSet aynı öğrenciyi iki kez tutar")
    void equalsizOgrenciIkiKezEklenir() {
        Set<EqualsizOgrenci> kume = new HashSet<>();

        kume.add(new EqualsizOgrenci("2024001", "Ece"));
        kume.add(new EqualsizOgrenci("2024001", "Ece"));

        assertEquals(2, kume.size());
        assertFalse(kume.contains(new EqualsizOgrenci("2024001", "Ece")));
    }

    @Test
    @DisplayName("Öğrenci HashMap'te anahtar olarak yeni ama eşit bir nesneyle bulunur")
    void hashMapAnahtar() {
        Map<Ogrenci, String> danisman = new HashMap<>();
        danisman.put(new Ogrenci("2024001", "Ece", 3.5), "Dr. Kaya");

        assertEquals("Dr. Kaya", danisman.get(new Ogrenci("2024001", "Ece", 3.5)));
    }

    @Test
    @DisplayName("equals ezilmemiş anahtar HashMap'te bulunamaz")
    void equalsizAnahtarBulunamaz() {
        Map<EqualsizOgrenci, String> danisman = new HashMap<>();
        EqualsizOgrenci ece = new EqualsizOgrenci("2024001", "Ece");
        danisman.put(ece, "Dr. Kaya");

        assertNull(danisman.get(new EqualsizOgrenci("2024001", "Ece")));
        assertSame("Dr. Kaya", danisman.get(ece));
    }

    @Test
    @DisplayName("Geçersiz numara ya da ortalama reddedilir")
    void gecersizDegerler() {
        assertThrows(IllegalArgumentException.class, () -> new Ogrenci("", "Ece", 3.0));
        assertThrows(IllegalArgumentException.class, () -> new Ogrenci("2024001", "Ece", 4.1));
    }
}
