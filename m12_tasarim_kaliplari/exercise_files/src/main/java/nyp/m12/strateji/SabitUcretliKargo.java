package nyp.m12.strateji;

/** M12 - Strategy: ağırlıktan bağımsız, her siparişte aynı ücreti alan kargo. */
public class SabitUcretliKargo implements KargoStratejisi {

    private final double ucret;

    public SabitUcretliKargo(double ucret) {
        if (ucret < 0) {
            throw new IllegalArgumentException("Kargo ücreti negatif olamaz: " + ucret);
        }
        this.ucret = ucret;
    }

    @Override
    public double ucretHesapla(double agirlikKg, double sepetTutari) {
        return ucret;
    }
}
