package nyp.m5;

import java.util.HashSet;

import nyp.m5.sekil.Dikdortgen;
import nyp.m5.sekil.Kare;
import nyp.m5.zincir.KurucuZinciri;

/** M5 - Kurucu zincirini, çalışan hiyerarşisini, equals/hashCode ve Kare sorununu gösterir. */
public class CalisanUygulamasi {

    public static void main(String[] args) {
        StringBuilder gunluk = new StringBuilder();
        new KurucuZinciri.Kedi(gunluk);
        System.out.print(gunluk);

        TamZamanliCalisan ayse = new TamZamanliCalisan("S-001", "Ayşe", 45000);
        SaatlikCalisan mehmet = new SaatlikCalisan("S-002", "Mehmet", 200);
        mehmet.saatEkle(170);
        Yonetici zeynep = new Yonetici("S-003", "Zeynep", 60000, 15000);

        System.out.println(ayse + " -> " + ayse.aylikUcret());
        System.out.println(mehmet + " -> " + mehmet.aylikUcret());
        System.out.println(zeynep + " -> " + zeynep.aylikUcret());

        HashSet<Calisan> kayitlar = new HashSet<>();
        kayitlar.add(ayse);
        kayitlar.add(new TamZamanliCalisan("S-001", "Ayşe", 45000));
        System.out.println("Kayıt sayısı: " + kayitlar.size());

        Dikdortgen d = new Kare(2);
        d.setEn(5);
        System.out.println("Beklenen alan 10, gerçek alan " + d.alan());
    }
}
