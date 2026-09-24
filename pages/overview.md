# Özet

Dersin konu akışı, tek bir sınıftan iyi tasarlanmış nesne sistemlerine doğru ilerler:

```mermaid
flowchart TD
    A["M1 OOP'ye giriş"] --> B["M2 Sınıflar ve nesneler"]
    B --> C["M3 Kapsülleme"]
    C --> D["M4 İlişkiler ve UML"]
    D --> E["M5 Kalıtım"]
    E --> F["M6 Çok biçimlilik"]
    F --> G["M7 Soyut sınıflar ve arayüzler"]
    G --> SA(["Sprint A"])
    SA --> H["M8 İstisnalar"]
    H --> I["M9 Koleksiyonlar ve generics"]
    I --> J["M10 Lambda ve Stream"]
    J --> K["M11 Tasarım ilkeleri"]
    K --> L["M12 Tasarım kalıpları"]
    L --> SB(["Sprint B"])
```

## Modüller

| Modül | Özet |
|---|---|
| [M1](../m1_oop_giris/README.md) | Prosedürel ve nesne yönelimli yaklaşım, sınıf/nesne/durum/davranış, dört temel ilke, Maven proje yapısı, JUnit 5 ile ilk test. |
| [M2](../m2_siniflar_nesneler/README.md) | Alanlar, metotlar, kurucular ve `this`; nesne oluşturma, referans ve değer, stack/heap, `null`. |
| [M3](../m3_kapsulleme/README.md) | Erişim belirleyiciler, getter/setter ve sınıf değişmezleri, `static` ve `final`, değişmez nesneler, savunmacı kopya, `record`. |
| [M4](../m4_iliskiler_uml/README.md) | UML sınıf diyagramı; ilişkilendirme ve çokluk, toplama, bileşim, bağımlılık; diyagramdan koda, koddan diyagrama. |
| [M5](../m5_kalitim/README.md) | `extends` ve `super`, metot ezme, `protected`, `Object` metotları (`toString`, `equals`, `hashCode`), kalıtımın sınırları. |
| [M6](../m6_cok_bicimlilik/README.md) | Dinamik bağlama, yukarı/aşağı tür dönüşümü, `instanceof` ve `switch` ile desen eşleme, aşırı yükleme ile ezme farkı. |
| [M7](../m7_soyut_arayuz/README.md) | Soyut sınıflar, arayüzler, `default` metotlar, `sealed` hiyerarşiler, `enum`. |
| [Sprint A](../sprint_a_proje/README.md) | M1–M7 ile çözülen bireysel proje. |
| [M8](../m8_istisnalar/README.md) | `try`/`catch`/`finally`, checked ve unchecked istisnalar, kendi istisna sınıfları, try-with-resources, istisna testleri. |
| [M9](../m9_koleksiyonlar_generics/README.md) | `List`, `Set`, `Map` ve seçim ölçütleri; generic sınıf ve metotlar; `Comparable` ve `Comparator`. |
| [M10](../m10_lambda_stream/README.md) | Fonksiyonel arayüzler, lambda, metot referansları, Stream API ve `Collectors`, `Optional`. |
| [M11](../m11_tasarim_ilkeleri/README.md) | SOLID ilkeleri ihlal → düzeltme örnekleriyle, bağımlılık enjeksiyonu ve sahte nesnelerle test, kalıtım yerine bileşim. |
| [M12](../m12_tasarim_kaliplari/README.md) | Strategy, Observer, Factory Method, Singleton ve Builder; kalıpları ne zaman kullanmamalı. |
| [Sprint B](../sprint_b_final_proje/README.md) | Tüm dönemi kapsayan grup projesi, tasarım kararları raporu ve sunum. |
