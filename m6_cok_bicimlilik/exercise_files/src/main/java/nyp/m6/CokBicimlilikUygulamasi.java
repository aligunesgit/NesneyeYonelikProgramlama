package nyp.m6;

import java.util.List;
import nyp.m6.bildirim.Bildirim;
import nyp.m6.bildirim.BildirimServisi;
import nyp.m6.bildirim.EpostaBildirim;
import nyp.m6.bildirim.PushBildirim;
import nyp.m6.bildirim.SmsBildirim;
import nyp.m6.sekil.Daire;
import nyp.m6.sekil.Dikdortgen;
import nyp.m6.sekil.Sekil;
import nyp.m6.sekil.SekilHesaplayici;
import nyp.m6.sekil.SekilTanitici;
import nyp.m6.sekil.Ucgen;

/** M6 - Çok biçimlilik örneklerini çalıştıran küçük program. */
public class CokBicimlilikUygulamasi {

    public static void main(String[] args) {
        List<Sekil> sekiller = List.of(new Daire(1), new Dikdortgen(2, 3), new Ucgen(3, 4, 5));
        for (Sekil s : sekiller) {
            System.out.println(s.ad() + " -> alan: " + s.alan());
        }
        System.out.println("Toplam (instanceof): " + SekilHesaplayici.toplamAlanInstanceofIle(sekiller));
        System.out.println("Toplam (çok biçimli): " + SekilHesaplayici.toplamAlan(sekiller));

        Sekil s = new Daire(2);
        System.out.println(SekilTanitici.tanit(s));
        System.out.println(SekilTanitici.tanit(new Daire(2)));

        List<Bildirim> kanallar = List.of(
                new EpostaBildirim("ayse@ornek.com"), new SmsBildirim("5551234567"), new PushBildirim("cihaz-42"));
        for (String kayit : BildirimServisi.hepsineGonder(kanallar, "Siparişiniz kargoya verildi")) {
            System.out.println(kayit);
        }
    }
}
