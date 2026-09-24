package nyp.m7.odeme;

/** M7 - Banka havalesi. Sabit 5 TL ücret; 1000 TL ve üzeri ödemelerde ücretsiz. */
public class Havale implements Odeme {

    public static final double SABIT_UCRET = 5.0;
    public static final double UCRETSIZ_ESIK = 1000.0;

    @Override
    public String ad() {
        return "Havale";
    }

    @Override
    public double komisyon(double tutar) {
        return tutar >= UCRETSIZ_ESIK ? 0 : SABIT_UCRET;
    }
}
