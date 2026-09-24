package nyp.m12.gozlemci;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiparisTest {

    /** Sahte gözlemci (fake): aldığı bildirimleri yalnızca kaydeder, testte sorgulanır. */
    static class SahteGozlemci implements SiparisGozlemcisi {
        final List<SiparisDurumu> alinanlar = new ArrayList<>();
        Siparis sonSiparis;

        @Override
        public void durumDegisti(Siparis siparis, SiparisDurumu eski, SiparisDurumu yeni) {
            sonSiparis = siparis;
            alinanlar.add(yeni);
        }
    }

    @Test
    @DisplayName("Durum değişince tüm gözlemciler bildirim alır")
    void tumGozlemcilerBildirimAlir() {
        // Hazırla
        Siparis siparis = new Siparis("S-1");
        SahteGozlemci birinci = new SahteGozlemci();
        SahteGozlemci ikinci = new SahteGozlemci();
        siparis.aboneEkle(birinci);
        siparis.aboneEkle(ikinci);
        // Çalıştır
        siparis.durumGuncelle(SiparisDurumu.HAZIRLANIYOR);
        // Doğrula
        assertEquals(List.of(SiparisDurumu.HAZIRLANIYOR), birinci.alinanlar);
        assertEquals(List.of(SiparisDurumu.HAZIRLANIYOR), ikinci.alinanlar);
        assertSame(siparis, birinci.sonSiparis);
    }

    @Test
    @DisplayName("Abonelikten çıkan gözlemci bildirim almaz")
    void abonelikIptali() {
        Siparis siparis = new Siparis("S-2");
        SahteGozlemci gozlemci = new SahteGozlemci();
        siparis.aboneEkle(gozlemci);

        assertTrue(siparis.aboneCikar(gozlemci));
        siparis.durumGuncelle(SiparisDurumu.KARGODA);

        assertTrue(gozlemci.alinanlar.isEmpty());
        assertFalse(siparis.aboneCikar(gozlemci));
    }

    @Test
    @DisplayName("Aynı duruma geçiş bildirim üretmez")
    void ayniDurumBildirimUretmez() {
        Siparis siparis = new Siparis("S-3");
        SahteGozlemci gozlemci = new SahteGozlemci();
        siparis.aboneEkle(gozlemci);

        siparis.durumGuncelle(SiparisDurumu.ALINDI);

        assertTrue(gozlemci.alinanlar.isEmpty());
    }

    @Test
    @DisplayName("Teslim edilmiş sipariş değişmez, gözlemciler rahatsız edilmez")
    void sonDurumdanSonraDegisiklikYok() {
        Siparis siparis = new Siparis("S-4");
        siparis.durumGuncelle(SiparisDurumu.TESLIM_EDILDI);
        SahteGozlemci gozlemci = new SahteGozlemci();
        siparis.aboneEkle(gozlemci);

        assertThrows(IllegalStateException.class, () -> siparis.durumGuncelle(SiparisDurumu.IPTAL_EDILDI));
        assertEquals(SiparisDurumu.TESLIM_EDILDI, siparis.getDurum());
        assertTrue(gozlemci.alinanlar.isEmpty());
    }

    @Test
    @DisplayName("Log gözlemcisi geçişleri sırayla kaydeder")
    void logGozlemcisiSiraylaKaydeder() {
        Siparis siparis = new Siparis("S-5");
        LogGozlemcisi log = new LogGozlemcisi();
        siparis.aboneEkle(log);

        siparis.durumGuncelle(SiparisDurumu.HAZIRLANIYOR);
        siparis.durumGuncelle(SiparisDurumu.KARGODA);

        assertEquals(List.of("S-5: ALINDI -> HAZIRLANIYOR", "S-5: HAZIRLANIYOR -> KARGODA"),
                log.getKayitlar());
    }

    @Test
    @DisplayName("Boş sipariş numarası reddedilir")
    void bosSiparisNoReddedilir() {
        assertThrows(IllegalArgumentException.class, () -> new Siparis(" "));
    }
}
