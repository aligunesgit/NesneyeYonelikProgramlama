package nyp.m11.bilesim;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * M11 - Kalıtım yerine bileşim (composition over inheritance): kümeyi genişletmek yerine İÇİNDE
 * tutar ve işleri ona iletir (delegation).
 *
 * <p>İçerideki kümenin {@code addAll}'ı nasıl gerçeklediği bu sınıfın sayacını etkilemez.
 */
public class SayacliKume<E> {

    private final Set<E> kume;
    private int eklemeDenemesi;

    public SayacliKume() {
        this(new HashSet<>());
    }

    /** Hangi {@code Set} gerçeklemesinin kullanılacağı dışarıdan seçilebilir (ör. TreeSet). */
    public SayacliKume(Set<E> kume) {
        this.kume = kume;
    }

    public boolean add(E e) {
        eklemeDenemesi++;
        return kume.add(e);
    }

    public boolean addAll(Collection<? extends E> c) {
        eklemeDenemesi += c.size();
        return kume.addAll(c);
    }

    public boolean contains(Object o) {
        return kume.contains(o);
    }

    public int size() {
        return kume.size();
    }

    public int getEklemeDenemesi() {
        return eklemeDenemesi;
    }
}
