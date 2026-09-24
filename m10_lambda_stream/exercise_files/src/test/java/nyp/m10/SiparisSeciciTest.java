package nyp.m10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SiparisSeciciTest {

    private final List<Siparis> siparisler = List.of(
            new Siparis("Ayşe", "Kitap", 120, SiparisDurumu.TESLIM_EDILDI),
            new Siparis("Mehmet", "Elektronik", 2500, SiparisDurumu.KARGODA),
            new Siparis("Zeynep", "Kitap", 45, SiparisDurumu.IPTAL));

    @Test
    @DisplayName("Ayrı sınıf, anonim sınıf ve lambda aynı sonucu verir")
    void ucYazimAyniSonucuVerir() {
        // Hazırla
        SiparisFiltresi ayriSinif = new PahaliSiparisFiltresi(100);
        SiparisFiltresi anonim = new SiparisFiltresi() {
            @Override
            public boolean uygunMu(Siparis siparis) {
                return siparis.tutar() >= 100;
            }
        };
        SiparisFiltresi lambda = s -> s.tutar() >= 100;
        // Çalıştır
        List<Siparis> a = SiparisSecici.filtrele(siparisler, ayriSinif);
        List<Siparis> b = SiparisSecici.filtrele(siparisler, anonim);
        List<Siparis> c = SiparisSecici.filtrele(siparisler, lambda);
        // Doğrula
        assertEquals(2, a.size());
        assertEquals(a, b);
        assertEquals(a, c);
    }

    @Test
    @DisplayName("Eşiğe eşit tutar pahalı sayılır")
    void esigeEsitTutarDahil() {
        List<Siparis> sonuc = SiparisSecici.filtrele(siparisler, new PahaliSiparisFiltresi(120));

        assertEquals(List.of(siparisler.get(0), siparisler.get(1)), sonuc);
    }

    @Test
    @DisplayName("Metot referansı da bir filtre olarak geçilebilir")
    void metotReferansiFiltreOlur() {
        List<Siparis> iptaller = SiparisSecici.filtrele(siparisler, Siparis::iptalMi);

        assertEquals(1, iptaller.size());
        assertEquals("Zeynep", iptaller.get(0).musteri());
    }

    @Test
    @DisplayName("ve() iki filtreyi birleştirir")
    void veIleFiltreBirlestirme() {
        SiparisFiltresi kitap = s -> s.kategori().equals("Kitap");
        SiparisFiltresi iptalDegil = s -> !s.iptalMi();

        List<Siparis> sonuc = SiparisSecici.filtrele(siparisler, kitap.ve(iptalDegil));

        assertEquals(List.of(siparisler.get(0)), sonuc);
    }

    @Test
    @DisplayName("Boş liste ya da hiçbir şeyin uymadığı filtre boş liste döndürür")
    void bosSonuc() {
        assertTrue(SiparisSecici.filtrele(List.of(), s -> true).isEmpty());
        assertTrue(SiparisSecici.filtrele(siparisler, s -> false).isEmpty());
    }
}
