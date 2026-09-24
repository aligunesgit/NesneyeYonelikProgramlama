package nyp.m12.strateji;

/** M12 - Strategy: taban ücret + kilogram başına ücret alan kargo. */
public class AgirligaGoreKargo implements KargoStratejisi {

    private final double tabanUcret;
    private final double kgBasinaUcret;

    public AgirligaGoreKargo(double tabanUcret, double kgBasinaUcret) {
        if (tabanUcret < 0 || kgBasinaUcret < 0) {
            throw new IllegalArgumentException("Ücretler negatif olamaz");
        }
        this.tabanUcret = tabanUcret;
        this.kgBasinaUcret = kgBasinaUcret;
    }

    @Override
    public double ucretHesapla(double agirlikKg, double sepetTutari) {
        return tabanUcret + kgBasinaUcret * agirlikKg;
    }
}
