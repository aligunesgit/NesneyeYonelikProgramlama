package nyp.m4.kutuphane;

import java.time.LocalDate;

/** M4 - Ödünç alma akışını (README'deki sequence diyagramı) uçtan uca çalıştırır. */
public class KutuphaneUygulamasi {

    public static void main(String[] args) {
        Kutuphane kutuphane = new Kutuphane("Atlas Kütüphanesi");
        Kitap kitap = new Kitap("978-0321193681", "UML Distilled");
        Uye ayse = new Uye("U-001", "Ayşe");
        kutuphane.kitapEkle(kitap);
        kutuphane.uyeEkle(ayse);

        Odunc odunc = kutuphane.oduncVer(ayse, kitap, LocalDate.of(2026, 10, 1));
        System.out.println(kitap.getBaslik() + " -> " + kitap.getOduncAlan().getAd());
        System.out.println("Son teslim: " + odunc.sonTeslimTarihi());

        double ceza = kutuphane.iadeAl(odunc, LocalDate.of(2026, 10, 18));
        System.out.println("Ceza: " + ceza + " TL, rafta mı? " + !kitap.oduncteMi());
    }
}
