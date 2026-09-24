package nyp.m11.bilesim;

import java.util.Collection;
import java.util.HashSet;

/**
 * M11 - UYARI ÖRNEĞİ: kalıtımla yazılmış, HATALI sayan küme.
 *
 * <p>Amaç: kümeye kaç kez eleman eklenmeye çalışıldığını saymak. {@code HashSet.addAll} kendi
 * içinde {@code add} metodunu çağırdığı için {@code addAll} ile eklenen her eleman iki kez sayılır.
 * Üst sınıfın iç ayrıntısına bağımlı olmanın ("kırılgan üst sınıf") örneğidir. Doğru sürüm:
 * {@link SayacliKume}.
 */
public class KalitimliSayacliKume<E> extends HashSet<E> {

    private int eklemeDenemesi;

    @Override
    public boolean add(E e) {
        eklemeDenemesi++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        eklemeDenemesi += c.size();
        return super.addAll(c);
    }

    public int getEklemeDenemesi() {
        return eklemeDenemesi;
    }
}
