package nyp.m4.kutuphane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UyeTest {

    @Test
    @DisplayName("oduncAl ilişkinin iki tarafını da günceller")
    void oduncAlIkiTarafiGunceller() {
        // Hazırla
        Uye ayse = new Uye("U-001", "Ayşe");
        Kitap kitap = new Kitap("111", "Simyacı");
        // Çalıştır
        boolean sonuc = ayse.oduncAl(kitap);
        // Doğrula
        assertTrue(sonuc);
        assertTrue(ayse.elindeMi(kitap));     // üye -> kitap
        assertSame(ayse, kitap.getOduncAlan()); // kitap -> üye
        assertEquals(1, ayse.oduncKitapSayisi());
    }

    @Test
    @DisplayName("Ödünçteki kitap ikinci bir üyeye verilemez")
    void oduncteKitapBaskasinaVerilemez() {
        Uye ayse = new Uye("U-001", "Ayşe");
        Uye mehmet = new Uye("U-002", "Mehmet");
        Kitap kitap = new Kitap("111", "Simyacı");
        ayse.oduncAl(kitap);

        assertFalse(mehmet.oduncAl(kitap));
        assertSame(ayse, kitap.getOduncAlan());
        assertEquals(0, mehmet.oduncKitapSayisi());
    }

    @Test
    @DisplayName("Üye en fazla MAKS_KITAP kitap alabilir (sınır durumu)")
    void kitapLimitiAsilamaz() {
        Uye ayse = new Uye("U-001", "Ayşe");
        for (int i = 0; i < Uye.MAKS_KITAP; i++) {
            assertTrue(ayse.oduncAl(new Kitap("K-" + i, "Kitap " + i)));
        }
        Kitap fazladan = new Kitap("K-X", "Fazladan");

        assertFalse(ayse.oduncAl(fazladan));
        assertFalse(fazladan.oduncteMi());
        assertEquals(Uye.MAKS_KITAP, ayse.oduncKitapSayisi());
    }

    @Test
    @DisplayName("iadeEt ilişkinin iki tarafını da koparır")
    void iadeIkiTarafiGunceller() {
        Uye ayse = new Uye("U-001", "Ayşe");
        Kitap kitap = new Kitap("111", "Simyacı");
        ayse.oduncAl(kitap);

        assertTrue(ayse.iadeEt(kitap));
        assertFalse(ayse.elindeMi(kitap));
        assertNull(kitap.getOduncAlan());
        assertEquals(0, ayse.oduncKitapSayisi());
    }

    @Test
    @DisplayName("Üye başkasının elindeki kitabı iade edemez")
    void baskasininKitabiIadeEdilemez() {
        Uye ayse = new Uye("U-001", "Ayşe");
        Uye mehmet = new Uye("U-002", "Mehmet");
        Kitap kitap = new Kitap("111", "Simyacı");
        ayse.oduncAl(kitap);

        assertFalse(mehmet.iadeEt(kitap));
        assertSame(ayse, kitap.getOduncAlan());
    }

    @Test
    @DisplayName("null kitap ödünç alınamaz")
    void nullKitapReddedilir() {
        Uye ayse = new Uye("U-001", "Ayşe");

        assertThrows(IllegalArgumentException.class, () -> ayse.oduncAl(null));
    }
}
