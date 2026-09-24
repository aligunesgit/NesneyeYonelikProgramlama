package nyp.m7;

import java.util.List;
import nyp.m7.hayvan.Ordek;
import nyp.m7.odeme.DijitalCuzdan;
import nyp.m7.odeme.Havale;
import nyp.m7.odeme.KrediKarti;
import nyp.m7.odeme.Odeme;
import nyp.m7.personel.Personel;
import nyp.m7.personel.SaatlikPersonel;
import nyp.m7.personel.TamZamanliPersonel;
import nyp.m7.sekil.Daire;
import nyp.m7.sekil.Dikdortgen;
import nyp.m7.sekil.Sekil;
import nyp.m7.sekil.SekilIslemleri;
import nyp.m7.sekil.Ucgen;
import nyp.m7.siparis.SiparisDurumu;

/** M7 - Soyut sınıf, arayüz, sealed ve enum örneklerini çalıştıran küçük program. */
public class SoyutArayuzUygulamasi {

    public static void main(String[] args) {
        List<Odeme> secenekler = List.of(new KrediKarti(1), new Havale(), new DijitalCuzdan());
        for (Odeme o : secenekler) {
            System.out.println(o.ad() + ": " + o.toplamTutar(200));
        }
        System.out.println("200 TL için en uygun: " + Odeme.enUygun(secenekler, 200).ad());

        List<Personel> personeller = List.of(
                new TamZamanliPersonel("Ayşe", 40_000), new SaatlikPersonel("Mehmet", 50, 100));
        for (Personel p : personeller) {
            System.out.println(p.getAd() + " net: " + p.netMaas());
        }

        System.out.println("Ördek " + new Ordek().hareket());

        for (Sekil s : List.<Sekil>of(new Daire(1), new Dikdortgen(2, 2), new Ucgen(4, 3))) {
            System.out.println(SekilIslemleri.tarif(s) + " -> alan " + SekilIslemleri.alan(s));
        }

        for (SiparisDurumu d : SiparisDurumu.values()) {
            System.out.println(d + " " + d.etiket() + " -> " + d.sonraki());
        }
    }
}
