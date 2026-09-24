package nyp.m5.zincir;

/**
 * M5 - Kurucu zincirinin çağrı sırasını gösteren üç katmanlı küçük hiyerarşi.
 *
 * <p>{@code new Kedi(gunluk)} yazıldığında önce {@code Canli}, sonra {@code Hayvan}, en son
 * {@code Kedi} kurucusunun gövdesi çalışır. Her kurucu gövdesine girdiğinde günlüğe bir satır
 * ekler; böylece sıra hem {@code main}'de yazdırılabilir hem de testte doğrulanabilir.
 */
public final class KurucuZinciri {

    private KurucuZinciri() {
    }

    /** Zincirin tepesi (kendisi örtük olarak {@code Object}'ten türer). */
    public static class Canli {

        public Canli(StringBuilder gunluk) {
            // Burada örtük bir super() çağrısı var: önce Object kurulur.
            gunluk.append("Canli kurucusu\n");
        }
    }

    /** Ortadaki sınıf. */
    public static class Hayvan extends Canli {

        public Hayvan(StringBuilder gunluk) {
            super(gunluk);                       // ilk satır olmak zorunda
            gunluk.append("Hayvan kurucusu\n");
        }
    }

    /** Zincirin en altı. */
    public static class Kedi extends Hayvan {

        public Kedi(StringBuilder gunluk) {
            super(gunluk);
            gunluk.append("Kedi kurucusu\n");
        }
    }
}
