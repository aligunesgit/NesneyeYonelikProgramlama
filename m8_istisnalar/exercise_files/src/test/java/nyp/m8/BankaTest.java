package nyp.m8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankaTest {

    private Banka banka;
    private Hesap ayse;
    private Hesap mehmet;

    @BeforeEach
    void hazirla() {
        banka = new Banka();
        ayse = banka.hesapAc("TR01", "Ayşe");
        mehmet = banka.hesapAc("TR02", "Mehmet");
        ayse.paraYatir(500);
    }

    @Test
    @DisplayName("Var olan hesap numarasıyla bulunur")
    void hesapBulunur() throws HesapBulunamadiException {
        assertSame(ayse, banka.hesapBul("TR01"));
    }

    @Test
    @DisplayName("Olmayan hesap HesapBulunamadiException fırlatır")
    void olmayanHesap() {
        HesapBulunamadiException hata =
                assertThrows(HesapBulunamadiException.class, () -> banka.hesapBul("TR99"));

        assertEquals("TR99", hata.getHesapNo());
        assertEquals("Hesap bulunamadı: TR99", hata.getMessage());
    }

    @Test
    @DisplayName("Başarılı havale iki bakiyeyi de günceller")
    void basariliHavale() throws BankaException {
        banka.havale("TR01", "TR02", 200);

        assertEquals(300.0, ayse.getBakiye(), 1e-9);
        assertEquals(200.0, mehmet.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Yetersiz bakiyede havale iki hesabı da değiştirmez")
    void yetersizBakiyedeHavale() {
        assertThrows(YetersizBakiyeException.class, () -> banka.havale("TR01", "TR02", 900));

        assertEquals(500.0, ayse.getBakiye(), 1e-9);
        assertEquals(0.0, mehmet.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Hedef hesap yoksa kaynaktan para çekilmez")
    void hedefYoksaParaCekilmez() {
        assertThrows(HesapBulunamadiException.class, () -> banka.havale("TR01", "TR99", 100));

        assertEquals(500.0, ayse.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("assertThrows alt türleri de kabul eder, assertThrowsExactly etmez")
    void ustTurleYakalama() {
        BankaException hata =
                assertThrows(BankaException.class, () -> banka.havale("TR01", "TR02", 900));

        assertInstanceOf(YetersizBakiyeException.class, hata);
        assertThrowsExactly(YetersizBakiyeException.class, () -> banka.havale("TR01", "TR02", 900));
    }

    @Test
    @DisplayName("Aynı numarayla ikinci hesap açılamaz")
    void ayniNumaraIleHesapAcilamaz() {
        IllegalArgumentException hata =
                assertThrows(IllegalArgumentException.class, () -> banka.hesapAc("TR01", "Zeynep"));

        assertEquals("Bu numarada hesap zaten var: TR01", hata.getMessage());
        assertEquals(2, banka.hesapSayisi());
    }
}
