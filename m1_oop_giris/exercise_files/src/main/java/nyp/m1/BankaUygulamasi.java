package nyp.m1;

import nyp.m1.prosedurel.ProsedurelBanka;

/** M1 - Aynı senaryoyu önce prosedürel, sonra nesne yönelimli biçimde çalıştırır. */
public class BankaUygulamasi {

    public static void main(String[] args) {
        // Prosedürel: veri dizilerde, işlemler ayrı fonksiyonlarda.
        String[] sahipler = {"Ayşe", "Mehmet"};
        double[] bakiyeler = {0, 0};
        ProsedurelBanka.paraYatir(bakiyeler, 0, 500);
        ProsedurelBanka.paraCek(bakiyeler, 0, 120);
        System.out.println("[Prosedürel] " + sahipler[0] + ": " + bakiyeler[0]);

        // Nesne yönelimli: her hesap kendi verisini ve işlemlerini taşıyan bir nesne.
        Hesap ayse = new Hesap("Ayşe");
        Hesap mehmet = new Hesap("Mehmet");
        ayse.paraYatir(500);
        ayse.paraCek(120);
        System.out.println("[Nesne]      " + ayse.getSahip() + ": " + ayse.getBakiye());
        System.out.println("[Nesne]      " + mehmet.getSahip() + ": " + mehmet.getBakiye());
    }
}
