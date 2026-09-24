package nyp.m3;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BankaHesabiTest {

    @Test
    @DisplayName("Yeni hesabın bakiyesi 0, limiti varsayılan değerdir")
    void yeniHesap() {
        BankaHesabi hesap = new BankaHesabi("Ayşe");

        assertEquals("Ayşe", hesap.getSahip());
        assertEquals(0.0, hesap.getBakiye(), 1e-9);
        assertEquals(BankaHesabi.VARSAYILAN_ISLEM_LIMITI, hesap.getIslemLimiti(), 1e-9);
    }

    @Test
    @DisplayName("Boş ya da null sahip adıyla hesap açılamaz")
    void bosSahipReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> new BankaHesabi(null));
        assertThrows(IllegalArgumentException.class, () -> new BankaHesabi("   "));
    }

    @Test
    @DisplayName("Hesap numaraları static sayaçla sırayla verilir")
    void numaralarSirayla() {
        BankaHesabi a = new BankaHesabi("Ayşe");
        BankaHesabi b = new BankaHesabi("Mehmet");

        assertEquals(a.getNumara() + 1, b.getNumara());
        assertEquals(b.getNumara(), BankaHesabi.acilanHesapSayisi());
    }

    @Test
    @DisplayName("Pozitif olmayan tutar yatırmak IllegalArgumentException fırlatır, bakiye değişmez")
    void pozitifOlmayanYatirmaReddedilir() {
        BankaHesabi hesap = new BankaHesabi("Ayşe");

        IllegalArgumentException hata =
                assertThrows(IllegalArgumentException.class, () -> hesap.paraYatir(-50));

        assertEquals("Tutar pozitif olmalı: -50.0", hata.getMessage());
        assertThrows(IllegalArgumentException.class, () -> hesap.paraYatir(0));
        assertEquals(0.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Yetersiz bakiyede para çekilemez: istisna değil, false")
    void yetersizBakiyeFalse() {
        BankaHesabi hesap = new BankaHesabi("Ayşe");
        hesap.paraYatir(100);

        assertFalse(hesap.paraCek(100.01));
        assertTrue(hesap.paraCek(100));
        assertEquals(0.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("İşlem limitinin üstünde çekim yapılamaz")
    void limitUstuCekilemez() {
        BankaHesabi hesap = new BankaHesabi("Ayşe", 1_000);
        hesap.paraYatir(5_000);

        assertFalse(hesap.paraCek(1_500));
        assertTrue(hesap.paraCek(1_000));
        assertEquals(4_000.0, hesap.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Setter geçersiz limiti reddeder ve eski limit korunur (sınıf değişmezi)")
    void setterGecersizLimitiReddeder() {
        BankaHesabi hesap = new BankaHesabi("Ayşe", 5_000);

        assertThrows(IllegalArgumentException.class, () -> hesap.setIslemLimiti(0));
        assertThrows(IllegalArgumentException.class,
                () -> hesap.setIslemLimiti(BankaHesabi.UST_ISLEM_LIMITI + 1));
        assertEquals(5_000.0, hesap.getIslemLimiti(), 1e-9);

        hesap.setIslemLimiti(BankaHesabi.UST_ISLEM_LIMITI);   // sınır değeri geçerli
        assertEquals(BankaHesabi.UST_ISLEM_LIMITI, hesap.getIslemLimiti(), 1e-9);
    }

    @Test
    @DisplayName("Havale: başarılıysa iki bakiye de güncellenir")
    void havaleBasarili() {
        BankaHesabi ayse = new BankaHesabi("Ayşe");
        BankaHesabi mehmet = new BankaHesabi("Mehmet");
        ayse.paraYatir(500);

        assertTrue(ayse.havaleYap(mehmet, 120));

        assertEquals(380.0, ayse.getBakiye(), 1e-9);
        assertEquals(120.0, mehmet.getBakiye(), 1e-9);
    }

    @Test
    @DisplayName("Havale: yetersiz bakiyede iki hesap da değişmez; kendine havale reddedilir")
    void havaleBasarisiz() {
        BankaHesabi ayse = new BankaHesabi("Ayşe");
        BankaHesabi mehmet = new BankaHesabi("Mehmet");
        ayse.paraYatir(50);

        assertFalse(ayse.havaleYap(mehmet, 80));
        assertEquals(50.0, ayse.getBakiye(), 1e-9);
        assertEquals(0.0, mehmet.getBakiye(), 1e-9);
        assertThrows(IllegalArgumentException.class, () -> ayse.havaleYap(ayse, 10));
    }
}
