package nyp.m3;

/** M3 - Kapsülleme, static sayaç, değişmez nesneler ve record örneklerini konsolda gösterir. */
public class KapsullemeUygulamasi {

    public static void main(String[] args) {
        BankaHesabi ayse = new BankaHesabi("Ayşe");
        BankaHesabi mehmet = new BankaHesabi("Mehmet", 2_000);
        ayse.paraYatir(500);
        ayse.havaleYap(mehmet, 120);
        System.out.println(ayse);
        System.out.println(mehmet);
        System.out.println("Açılan hesap: " + BankaHesabi.acilanHesapSayisi());

        try {
            ayse.setIslemLimiti(-5);
        } catch (IllegalArgumentException e) {   // try/catch ayrıntısı M8'de
            System.out.println("Reddedildi: " + e.getMessage());
        }

        Sicaklik sabah = new Sicaklik(12.5);
        Sicaklik ogle = sabah.artir(8);
        System.out.println(sabah + " -> " + ogle);

        Para fiyat = Para.tl(12, 50);
        Para toplam = fiyat.topla(Para.tl(7, 75));
        System.out.println(toplam + " = " + toplam.bicimli());
    }
}
