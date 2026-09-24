package nyp.m5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M5 - Calisan: kurucu doğrulaması, toString ve equals/hashCode sözleşmesi. */
class CalisanTest {

    @Test
    @DisplayName("Genel çalışanın ücret kuralı yoktur: 0 döner")
    void genelCalisanUcretiSifir() {
        Calisan c = new Calisan("S-100", "Deniz");

        assertEquals(0.0, c.aylikUcret(), 1e-9);
        assertEquals("S-100 Deniz", c.toString());
    }

    @Test
    @DisplayName("Boş sicil numarası ya da null ad kabul edilmez")
    void kurucuGecersizDegerleriReddeder() {
        assertThrows(IllegalArgumentException.class, () -> new Calisan("  ", "Deniz"));
        assertThrows(IllegalArgumentException.class, () -> new Calisan(null, "Deniz"));
        assertThrows(IllegalArgumentException.class, () -> new Calisan("S-100", null));
    }

    @Test
    @DisplayName("equals yansımalıdır: x.equals(x)")
    void equalsYansimali() {
        Calisan x = new TamZamanliCalisan("S-001", "Ayşe", 45000);

        assertTrue(x.equals(x));
    }

    @Test
    @DisplayName("equals simetriktir, farklı alt sınıflar arasında da")
    void equalsSimetrik() {
        // Hazırla: aynı sicil, farklı çalışan türleri (ör. tam zamanlıdan saatliğe geçiş)
        Calisan x = new TamZamanliCalisan("S-001", "Ayşe", 45000);
        Calisan y = new SaatlikCalisan("S-001", "Ayşe", 250);
        // Çalıştır ve doğrula
        assertTrue(x.equals(y));
        assertTrue(y.equals(x));
    }

    @Test
    @DisplayName("equals geçişlidir: x=y ve y=z ise x=z")
    void equalsGecisli() {
        Calisan x = new Calisan("S-001", "Ayşe");
        Calisan y = new TamZamanliCalisan("S-001", "Ayşe", 45000);
        Calisan z = new Yonetici("S-001", "Ayşe", 60000, 5000);

        assertTrue(x.equals(y));
        assertTrue(y.equals(z));
        assertTrue(x.equals(z));
    }

    @Test
    @DisplayName("equals tutarlıdır: saat eklemek eşitliği değiştirmez")
    void equalsTutarli() {
        SaatlikCalisan x = new SaatlikCalisan("S-002", "Mehmet", 200);
        SaatlikCalisan y = new SaatlikCalisan("S-002", "Mehmet", 200);

        boolean once = x.equals(y);
        x.saatEkle(40);
        boolean sonra = x.equals(y);

        assertTrue(once);
        assertEquals(once, sonra);
    }

    @Test
    @DisplayName("x.equals(null) false döner, istisna fırlatmaz")
    void equalsNullIcinFalse() {
        Calisan x = new Calisan("S-001", "Ayşe");

        assertFalse(x.equals(null));
    }

    @Test
    @DisplayName("Farklı sicil ya da Calisan olmayan nesne eşit değildir")
    void farkliSicilVeFarkliTurEsitDegil() {
        Calisan x = new Calisan("S-001", "Ayşe");

        assertNotEquals(x, new Calisan("S-002", "Ayşe"));   // ad aynı, sicil farklı
        assertFalse(x.equals("S-001"));                      // String bir Calisan değildir
    }

    @Test
    @DisplayName("Eşit nesnelerin hashCode değerleri eşittir")
    void esitNesnelerinHashCodeuEsit() {
        Calisan x = new TamZamanliCalisan("S-001", "Ayşe", 45000);
        Calisan y = new Yonetici("S-001", "Ayşe Yılmaz", 60000, 5000);

        assertEquals(x, y);
        assertEquals(x.hashCode(), y.hashCode());
    }

    @Test
    @DisplayName("HashSet aynı sicilli çalışanı ikinci kez eklemez")
    void hashSetTekrariEngeller() {
        Set<Calisan> kayitlar = new HashSet<>();

        boolean ilk = kayitlar.add(new TamZamanliCalisan("S-001", "Ayşe", 45000));
        boolean ikinci = kayitlar.add(new TamZamanliCalisan("S-001", "Ayşe", 45000));

        assertTrue(ilk);
        assertFalse(ikinci);
        assertEquals(1, kayitlar.size());
        assertTrue(kayitlar.contains(new SaatlikCalisan("S-001", "Ayşe", 250)));
    }

    /** equals'ı ezip hashCode'u unutan, bilerek hatalı bir sınıf. */
    private static final class HashCodeUnutan {
        private final String kod;

        HashCodeUnutan(String kod) {
            this.kod = kod;
        }

        @Override
        public boolean equals(Object o) {
            return o instanceof HashCodeUnutan diger && Objects.equals(kod, diger.kod);
        }
        // hashCode ezilmedi: Object'in kimliğe dayalı hashCode'u kullanılır
    }

    @Test
    @DisplayName("hashCode ezilmezse HashSet eşit nesneleri ayrı sayar")
    void hashCodeUnutulursaHashSetBozulur() {
        HashCodeUnutan a = new HashCodeUnutan("S-001");
        HashCodeUnutan b = new HashCodeUnutan("S-001");
        Set<HashCodeUnutan> kume = new HashSet<>();

        kume.add(a);
        kume.add(b);

        assertEquals(a, b);                  // equals'a göre eşitler...
        assertEquals(2, kume.size());        // ...ama küme iki ayrı eleman görüyor
    }
}
