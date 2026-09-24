package nyp.m7.siparis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EnumTest {

    @Test
    @DisplayName("Sipariş akışı: hazırlanıyor, kargoda, teslim edildi")
    void akis() {
        SiparisDurumu d = SiparisDurumu.HAZIRLANIYOR;

        d = d.sonraki();
        assertSame(SiparisDurumu.KARGODA, d);
        d = d.sonraki();
        assertSame(SiparisDurumu.TESLIM_EDILDI, d);
        assertSame(SiparisDurumu.TESLIM_EDILDI, d.sonraki());
    }

    @Test
    @DisplayName("Yalnızca hazırlanan sipariş iptal edilebilir")
    void iptal() {
        assertTrue(SiparisDurumu.HAZIRLANIYOR.iptalEdilebilirMi());
        assertFalse(SiparisDurumu.KARGODA.iptalEdilebilirMi());
        assertSame(SiparisDurumu.IPTAL_EDILDI, SiparisDurumu.IPTAL_EDILDI.sonraki());
        assertTrue(SiparisDurumu.IPTAL_EDILDI.bittiMi());
        assertFalse(SiparisDurumu.KARGODA.bittiMi());
    }

    @Test
    @DisplayName("Enum alanları ve arayüzden gelen default metot")
    void alanlarVeArayuz() {
        Aciklanabilir a = SiparisDurumu.KARGODA;

        assertEquals("Kargoda", a.aciklama());
        assertEquals("[Kargoda]", a.etiket());
        assertEquals(2, SiparisDurumu.KARGODA.getAdim());
    }

    @Test
    @DisplayName("values(), valueOf() ve ordinal()")
    void yerlesikMetotlar() {
        assertEquals(4, SiparisDurumu.values().length);
        assertSame(SiparisDurumu.KARGODA, SiparisDurumu.valueOf("KARGODA"));
        assertEquals(0, SiparisDurumu.HAZIRLANIYOR.ordinal());
        assertThrows(IllegalArgumentException.class, () -> SiparisDurumu.valueOf("kargoda"));
    }

    @Test
    @DisplayName("Gun: hafta sonu ve çalışma saatleri")
    void gun() {
        assertTrue(Gun.CUMARTESI.haftaSonuMu());
        assertFalse(Gun.CUMA.haftaSonuMu());
        assertEquals(0, Gun.PAZAR.calismaSaati());
        assertEquals(5 * 9 + 5, Gun.haftalikCalismaSaati());
    }
}
