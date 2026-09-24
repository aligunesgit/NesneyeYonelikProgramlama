package nyp.m6.sekil;

/**
 * M6 - Aşırı yükleme (overloading) tuzağı.
 *
 * <p>Aynı adlı iki metot vardır. Hangisinin çağrılacağına derleyici, argümanın <b>statik</b>
 * (derleme zamanı) türüne bakarak karar verir. Nesnenin gerçek türü bu seçimi etkilemez.
 */
public final class SekilTanitici {

    private SekilTanitici() {
    }

    public static String tanit(Sekil s) {
        return "Bir şekil";
    }

    public static String tanit(Daire d) {
        return "Bir daire";
    }
}
