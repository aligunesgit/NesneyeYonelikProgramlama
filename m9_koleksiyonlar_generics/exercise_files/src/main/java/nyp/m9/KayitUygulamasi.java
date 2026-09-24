package nyp.m9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** M9 - Koleksiyon, generics ve sıralama örneklerini çalıştırır. */
public class KayitUygulamasi {

    public static void main(String[] args) {
        List<Ogrenci> liste = new ArrayList<>();
        liste.add(new Ogrenci("2024003", "Can", 2.8));
        liste.add(new Ogrenci("2024001", "Ece", 3.5));
        liste.add(new Ogrenci("2024002", "Ali", 3.5));

        Collections.sort(liste);
        System.out.println("Numaraya göre:      " + liste);
        liste.sort(new AdaGoreKarsilastirici());
        System.out.println("Ada göre:           " + liste);
        liste.sort(Ogrenci.ORTALAMAYA_GORE_AZALAN.thenComparing(new AdaGoreKarsilastirici()));
        System.out.println("Ortalama, sonra ad: " + liste);

        Set<Ogrenci> kume = new HashSet<>(liste);
        kume.add(new Ogrenci("2024001", "Ece", 3.5));
        System.out.println("Küme boyutu (Ogrenci): " + kume.size());

        Set<EqualsizOgrenci> hataliKume = new HashSet<>();
        hataliKume.add(new EqualsizOgrenci("2024001", "Ece"));
        hataliKume.add(new EqualsizOgrenci("2024001", "Ece"));
        System.out.println("Küme boyutu (EqualsizOgrenci): " + hataliKume.size());

        Map<String, Ogrenci> numaraIle = new HashMap<>();
        for (Ogrenci o : liste) {
            numaraIle.put(o.getNumara(), o);
        }
        System.out.println("2024002 -> " + numaraIle.get("2024002"));

        KelimeSayaci sayac = new KelimeSayaci();
        sayac.ekle("Bir elma, bir armut; iki elma.");
        System.out.println(sayac.sayimlar());

        System.out.println("En büyük: " + GenericAraclar.enBuyuk(List.of(3, 9, 4)));
    }
}
