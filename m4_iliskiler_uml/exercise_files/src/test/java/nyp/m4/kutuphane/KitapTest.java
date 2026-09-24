package nyp.m4.kutuphane;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KitapTest {

    @Test
    @DisplayName("Yeni kitap raftadır, ödünç alanı yoktur")
    void yeniKitapRaftadir() {
        Kitap kitap = new Kitap("978-0321193681", "UML Distilled");

        assertFalse(kitap.oduncteMi());
        assertNull(kitap.getOduncAlan());
        assertEquals("UML Distilled", kitap.getBaslik());
    }

    @Test
    @DisplayName("Boş ISBN ile kitap oluşturulamaz")
    void bosIsbnReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> new Kitap(" ", "UML Distilled"));
    }
}
