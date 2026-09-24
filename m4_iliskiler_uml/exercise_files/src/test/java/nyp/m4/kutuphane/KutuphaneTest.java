package nyp.m4.kutuphane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KutuphaneTest {

    private static final LocalDate EKIM_1 = LocalDate.of(2026, 10, 1);

    @Test
    @DisplayName("Aynı ISBN'li kitap ikinci kez eklenmez")
    void ayniIsbnIkiKezEklenmez() {
        Kutuphane kutuphane = new Kutuphane("Merkez");

        assertTrue(kutuphane.kitapEkle(new Kitap("111", "Simyacı")));
        assertFalse(kutuphane.kitapEkle(new Kitap("111", "Simyacı (2. kopya)")));
        assertEquals(1, kutuphane.kitapSayisi());
    }

    @Test
    @DisplayName("Olmayan ISBN aranınca null döner")
    void olmayanKitapBulunamaz() {
        Kutuphane kutuphane = new Kutuphane("Merkez");

        assertNull(kutuphane.kitapBul("999"));
    }

    @Test
    @DisplayName("Toplama: kitap bir kütüphaneden çıkıp başkasına geçebilir")
    void kitapBaskaKutuphaneyeGecebilir() {
        Kutuphane merkez = new Kutuphane("Merkez");
        Kutuphane sube = new Kutuphane("Şube");
        Kitap kitap = new Kitap("111", "Simyacı");
        merkez.kitapEkle(kitap);

        assertTrue(merkez.kitapCikar(kitap));
        assertTrue(sube.kitapEkle(kitap));
        assertEquals(0, merkez.kitapSayisi());
        assertSame(kitap, sube.kitapBul("111"));   // aynı nesne, yeni bütün
    }

    @Test
    @DisplayName("Ödünçteki kitap kütüphaneden çıkarılamaz")
    void oduncteKitapCikarilamaz() {
        Kutuphane kutuphane = new Kutuphane("Merkez");
        Kitap kitap = new Kitap("111", "Simyacı");
        Uye ayse = new Uye("U-001", "Ayşe");
        kutuphane.kitapEkle(kitap);
        kutuphane.uyeEkle(ayse);
        kutuphane.oduncVer(ayse, kitap, EKIM_1);

        assertFalse(kutuphane.kitapCikar(kitap));
        assertEquals(1, kutuphane.kitapSayisi());
    }

    @Test
    @DisplayName("oduncVer kaydı döndürür ve üye ile kitabı bağlar")
    void oduncVerKayitDondurur() {
        Kutuphane kutuphane = new Kutuphane("Merkez");
        Kitap kitap = new Kitap("111", "Simyacı");
        Uye ayse = new Uye("U-001", "Ayşe");
        kutuphane.kitapEkle(kitap);
        kutuphane.uyeEkle(ayse);

        Odunc odunc = kutuphane.oduncVer(ayse, kitap, EKIM_1);

        assertSame(ayse, odunc.uye());
        assertSame(kitap, odunc.kitap());
        assertSame(ayse, kitap.getOduncAlan());
        assertTrue(ayse.elindeMi(kitap));
    }

    @Test
    @DisplayName("Kayıtlı olmayan üyeye kitap verilmez")
    void kayitsizUyeyeVerilmez() {
        Kutuphane kutuphane = new Kutuphane("Merkez");
        Kitap kitap = new Kitap("111", "Simyacı");
        kutuphane.kitapEkle(kitap);
        Uye yabanci = new Uye("U-999", "Yabancı");

        assertThrows(IllegalArgumentException.class,
                () -> kutuphane.oduncVer(yabanci, kitap, EKIM_1));
        assertFalse(kitap.oduncteMi());
    }

    @Test
    @DisplayName("Zamanında iadede ceza yok, 3 gün gecikmede 7.5 TL")
    void iadeCezasi() {
        Kutuphane kutuphane = new Kutuphane("Merkez");
        Kitap simyaci = new Kitap("111", "Simyacı");
        Kitap nutuk = new Kitap("222", "Nutuk");
        Uye ayse = new Uye("U-001", "Ayşe");
        kutuphane.kitapEkle(simyaci);
        kutuphane.kitapEkle(nutuk);
        kutuphane.uyeEkle(ayse);
        Odunc birinci = kutuphane.oduncVer(ayse, simyaci, EKIM_1);
        Odunc ikinci = kutuphane.oduncVer(ayse, nutuk, EKIM_1);

        assertEquals(0.0, kutuphane.iadeAl(birinci, LocalDate.of(2026, 10, 15)), 1e-9);
        assertEquals(7.5, kutuphane.iadeAl(ikinci, LocalDate.of(2026, 10, 18)), 1e-9);
        assertFalse(nutuk.oduncteMi());
        assertEquals(0, ayse.oduncKitapSayisi());
    }
}
