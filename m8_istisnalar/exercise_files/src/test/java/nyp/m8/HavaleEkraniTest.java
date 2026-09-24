package nyp.m8;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HavaleEkraniTest {

    private HavaleEkrani ekran;

    @BeforeEach
    void hazirla() {
        Banka banka = new Banka();
        banka.hesapAc("TR01", "Ayşe").paraYatir(500);
        banka.hesapAc("TR02", "Mehmet");
        ekran = new HavaleEkrani(banka);
    }

    @Test
    @DisplayName("Her hata türü kendi catch bloğunda ayrı mesaja çevrilir")
    void hataTurleriAyriMesaj() {
        assertEquals("Havale tamamlandı", ekran.havaleYap("TR01", "TR02", 100));
        assertEquals("Bakiye yetersiz, eksik tutar: 600.0", ekran.havaleYap("TR01", "TR02", 1000));
        assertEquals("Hesap bulunamadı: TR99", ekran.havaleYap("TR99", "TR02", 10));
        assertEquals("Geçersiz tutar", ekran.havaleYap("TR01", "TR02", -10));
    }

    @Test
    @DisplayName("Multi-catch iki banka hatasını tek blokta ele alır")
    void multiCatch() {
        assertEquals("İşlem başarısız: Hesap bulunamadı: TR99",
                ekran.havaleYapOzet("TR01", "TR99", 10));
        assertEquals("İşlem başarısız: Yetersiz bakiye: hesap TR02, bakiye 0.0, istenen 10.0",
                ekran.havaleYapOzet("TR02", "TR01", 10));
    }

    @Test
    @DisplayName("finally bloğu başarıda da hatada da çalışır")
    void finallyHerDurumdaCalisir() {
        ekran.havaleYap("TR01", "TR02", 100);
        ekran.havaleYap("TR01", "TR02", 1000);
        ekran.havaleYapOzet("TR99", "TR01", 1);

        assertEquals(3, ekran.getDenemeSayisi());
    }
}
