package nyp.m9;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** M9 - JDK koleksiyonlarının README'de anlatılan davranışlarını doğrular. */
class KoleksiyonDavranisiTest {

    @Test
    @DisplayName("List ekleme sırasını korur ve tekrara izin verir")
    void listSiraVeTekrar() {
        List<String> liste = new ArrayList<>();
        liste.add("elma");
        liste.add("armut");
        liste.add("elma");

        assertEquals(3, liste.size());
        assertEquals("armut", liste.get(1));
    }

    @Test
    @DisplayName("Üç Set türü: tekrar yok; sıra farklı")
    void setTurleri() {
        List<String> girdi = List.of("muz", "elma", "kiraz", "elma");

        Set<String> hash = new HashSet<>(girdi);
        Set<String> linked = new LinkedHashSet<>(girdi);
        Set<String> tree = new TreeSet<>(girdi);

        assertEquals(3, hash.size());
        assertEquals(List.of("muz", "elma", "kiraz"), List.copyOf(linked));
        assertEquals(List.of("elma", "kiraz", "muz"), List.copyOf(tree));
    }

    @Test
    @DisplayName("List.of ve Map.of değiştirilemez koleksiyon üretir")
    void degistirilemezKoleksiyonlar() {
        List<String> gunler = List.of("pzt", "sal");
        Map<String, Integer> krediler = Map.of("NYP101", 6);

        assertThrows(UnsupportedOperationException.class, () -> gunler.add("car"));
        assertThrows(UnsupportedOperationException.class, () -> krediler.put("MAT101", 5));
    }

    @Test
    @DisplayName("for-each içinde listeden silmek ConcurrentModificationException fırlatır")
    void dolasirkenSilme() {
        List<String> liste = new ArrayList<>(List.of("a", "b", "c"));

        assertThrows(ConcurrentModificationException.class, () -> {
            for (String s : liste) {
                if (s.equals("a")) {
                    liste.remove(s);
                }
            }
        });
    }

    @Test
    @DisplayName("Iterator.remove dolaşırken güvenle siler")
    void iteratorRemove() {
        List<Integer> notlar = new ArrayList<>(List.of(45, 80, 30, 90));

        Iterator<Integer> it = notlar.iterator();
        while (it.hasNext()) {
            if (it.next() < 50) {
                it.remove();
            }
        }

        assertEquals(List.of(80, 90), notlar);
    }

    @Test
    @DisplayName("Map: aynı anahtarla put eski değerin üzerine yazar")
    void mapUzerineYazar() {
        Map<String, Integer> stok = new java.util.HashMap<>();
        stok.put("kalem", 10);

        Integer eski = stok.put("kalem", 7);

        assertEquals(10, eski);
        assertEquals(7, stok.get("kalem"));
        assertEquals(1, stok.size());
        assertEquals(0, stok.getOrDefault("silgi", 0));
    }
}
