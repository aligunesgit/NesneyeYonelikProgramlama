package nyp.m4.okul;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DersTest {

    @Test
    @DisplayName("Toplama: aynı öğrenci iki farklı derse kayıtlı olabilir")
    void ogrenciIkiDerseKayitliOlabilir() {
        Ogrenci zeynep = new Ogrenci("2026001", "Zeynep");
        Ders nyp = new Ders("BIL203", 40);
        Ders veriYapilari = new Ders("BIL205", 40);

        assertTrue(nyp.kaydet(zeynep));
        assertTrue(veriYapilari.kaydet(zeynep));
        assertTrue(nyp.kayitliMi(zeynep));
        assertTrue(veriYapilari.kayitliMi(zeynep));
    }

    @Test
    @DisplayName("Aynı öğrenci bir derse iki kez kaydolamaz")
    void ikiKezKayitOlmaz() {
        Ogrenci zeynep = new Ogrenci("2026001", "Zeynep");
        Ders nyp = new Ders("BIL203", 40);
        nyp.kaydet(zeynep);

        assertFalse(nyp.kaydet(zeynep));
        assertEquals(1, nyp.ogrenciSayisi());
    }

    @Test
    @DisplayName("Kontenjan dolunca yeni kayıt alınmaz (sınır durumu)")
    void kontenjanDolu() {
        Ders seminer = new Ders("BIL499", 2);

        assertTrue(seminer.kaydet(new Ogrenci("1", "Ali")));
        assertTrue(seminer.kaydet(new Ogrenci("2", "Ece")));
        assertFalse(seminer.kaydet(new Ogrenci("3", "Can")));
        assertEquals(2, seminer.ogrenciSayisi());
    }

    @Test
    @DisplayName("Kontenjan en az 1 olmalıdır")
    void gecersizKontenjan() {
        assertThrows(IllegalArgumentException.class, () -> new Ders("BIL203", 0));
    }
}
