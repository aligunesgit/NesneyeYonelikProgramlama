package nyp.m7.siparis;

/** M7 - Haftanın günleri ve kargo firmasının çalışma saatleri. */
public enum Gun {
    PAZARTESI, SALI, CARSAMBA, PERSEMBE, CUMA, CUMARTESI, PAZAR;

    public boolean haftaSonuMu() {
        return this == CUMARTESI || this == PAZAR;
    }

    /** Kargo şubesinin o gün açık olduğu saat sayısı. */
    public int calismaSaati() {
        return switch (this) {
            case PAZARTESI, SALI, CARSAMBA, PERSEMBE, CUMA -> 9;
            case CUMARTESI -> 5;
            case PAZAR -> 0;
        };
    }

    /** Haftalık toplam çalışma saati: values() ile tüm sabitler gezilir. */
    public static int haftalikCalismaSaati() {
        int toplam = 0;
        for (Gun g : values()) {
            toplam += g.calismaSaati();
        }
        return toplam;
    }
}
