package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DersTest {

    private final Ogrenci can = new Ogrenci("2024003", "Can", 1.8);
    private final Ogrenci ece = new Ogrenci("2024001", "Ece", 3.5);
    private final Ogrenci ali = new Ogrenci("2024002", "Ali", 2.1);
    private Ders ders;

    @BeforeEach
    void hazirla() {
        ders = new Ders("NYP101", 3);
    }

    @Test
    @DisplayName("Aynı öğrenci ikinci kez kaydolamaz")
    void ikinciKayitReddedilir() {
        assertTrue(ders.kaydet(ece));
        assertFalse(ders.kaydet(new Ogrenci("2024001", "Ece", 3.5)));
        assertEquals(1, ders.ogrenciSayisi());
    }

    @Test
    @DisplayName("Kontenjan dolunca IllegalStateException fırlatılır")
    void kontenjanDolu() {
        ders.kaydet(can);
        ders.kaydet(ece);
        ders.kaydet(ali);

        IllegalStateException hata = assertThrows(IllegalStateException.class,
                () -> ders.kaydet(new Ogrenci("2024004", "Deniz", 3.0)));
        assertEquals("Kontenjan dolu: NYP101", hata.getMessage());
    }

    @Test
    @DisplayName("Kontenjan doluyken kayıtlı öğrenciyi tekrar denemek false döner")
    void doluykenTekrarKayit() {
        ders.kaydet(can);
        ders.kaydet(ece);
        ders.kaydet(ali);

        assertFalse(ders.kaydet(ece));
    }

    @Test
    @DisplayName("Öğrenci listesi kayıt sırasını korur ve değiştirilemez")
    void listeKayitSirasinda() {
        ders.kaydet(can);
        ders.kaydet(ece);
        ders.kaydet(ali);

        List<Ogrenci> liste = ders.ogrenciListesi();

        assertEquals(List.of(can, ece, ali), liste);
        assertThrows(UnsupportedOperationException.class, () -> liste.add(ece));
    }

    @Test
    @DisplayName("Iterator.remove ile ortalaması yetmeyenler güvenle çıkarılır")
    void ortalamasiYetmeyenler() {
        ders.kaydet(can);
        ders.kaydet(ece);
        ders.kaydet(ali);

        int silinen = ders.ortalamasiYetmeyenleriCikar(2.0);

        assertEquals(1, silinen);
        assertFalse(ders.kayitliMi(can));
        assertEquals(List.of(ece, ali), ders.ogrenciListesi());
    }

    @Test
    @DisplayName("Pozitif olmayan kontenjanla ders açılamaz")
    void gecersizKontenjan() {
        assertThrows(IllegalArgumentException.class, () -> new Ders("NYP101", 0));
    }
}
