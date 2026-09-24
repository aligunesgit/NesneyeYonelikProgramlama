package nyp.m4.kutuphane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OduncTest {

    private final Uye ayse = new Uye("U-001", "Ayşe");
    private final Kitap kitap = new Kitap("111", "Simyacı");

    @Test
    @DisplayName("Son teslim tarihi alış tarihinden 14 gün sonradır")
    void sonTeslimTarihi() {
        Odunc odunc = new Odunc(ayse, kitap, LocalDate.of(2026, 10, 1));

        assertEquals(LocalDate.of(2026, 10, 15), odunc.sonTeslimTarihi());
    }

    @Test
    @DisplayName("Son gün iade edilirse gecikme 0, bir gün sonra 1 (sınır durumu)")
    void sonGunGecikmeSayilmaz() {
        Odunc odunc = new Odunc(ayse, kitap, LocalDate.of(2026, 10, 1));

        assertEquals(0, odunc.gecikmeGunu(LocalDate.of(2026, 10, 15)));
        assertEquals(1, odunc.gecikmeGunu(LocalDate.of(2026, 10, 16)));
        assertEquals(0, odunc.gecikmeGunu(LocalDate.of(2026, 10, 3)));
    }

    @Test
    @DisplayName("Eksik bilgiyle ödünç kaydı oluşturulamaz")
    void nullAlanReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> new Odunc(ayse, null, LocalDate.now()));
    }
}
