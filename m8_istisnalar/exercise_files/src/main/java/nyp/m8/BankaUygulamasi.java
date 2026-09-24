package nyp.m8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/** M8 - Banka, not okuyucu ve try-with-resources örneklerini çalıştırır. */
public class BankaUygulamasi {

    public static void main(String[] args) {
        Banka banka = new Banka();
        banka.hesapAc("TR01", "Ayşe").paraYatir(500);
        banka.hesapAc("TR02", "Mehmet");

        HavaleEkrani ekran = new HavaleEkrani(banka);
        System.out.println(ekran.havaleYap("TR01", "TR02", 120));
        System.out.println(ekran.havaleYap("TR01", "TR02", 1000));
        System.out.println(ekran.havaleYap("TR01", "TR99", 10));
        System.out.println(ekran.havaleYapOzet("TR02", "TR01", 500));
        System.out.println("Deneme sayısı: " + ekran.getDenemeSayisi());

        String dosyaIcerigi = "Ayşe;85\nMehmet;70\nZeynep;yetmiş\n";
        try (BufferedReader okuyucu = new BufferedReader(new StringReader(dosyaIcerigi))) {
            System.out.println(NotOkuyucu.notlariOku(okuyucu));
        } catch (NotFormatException e) {
            System.out.println(e.getMessage() + " (neden: " + e.getCause().getClass().getSimpleName() + ")");
        } catch (IOException e) {
            System.out.println("Okuma hatası: " + e.getMessage());
        }

        List<String> gunluk = new ArrayList<>();
        try (IzlenenKaynak a = new IzlenenKaynak("A", gunluk);
                IzlenenKaynak b = new IzlenenKaynak("B", gunluk)) {
            a.kullan();
            b.kullan();
        }
        System.out.println(gunluk);
    }
}
