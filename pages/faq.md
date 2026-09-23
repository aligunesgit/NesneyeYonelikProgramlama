# Sıkça Sorulan Sorular

??? question "Java temellerini unuttum. Nereden tekrar edebilirim?"

    [Dev.java'daki "Learn Java"](https://dev.java/learn/) sayfasındaki dil temelleri bölümü değişkenler,
    kontrol yapıları, döngüler ve metotları kısa örneklerle tekrar eder. İlk iki haftada ihtiyaç
    duyduğumuz temelleri de modüller içinde hatırlatıyoruz.

??? question "Neden Java 21?"

    Java 21 uzun süreli destek (LTS) sürümüdür ve `record`, `sealed` sınıflar, `switch` ile desen
    eşleme gibi bu derste kullandığımız modern özellikleri içerir. Bilgisayarınızda daha yeni bir JDK
    varsa sorun değil: proje `--release 21` ile derlenir.

??? question "IntelliJ mi, VS Code mu?"

    İkisi de olur. IntelliJ IDEA Community Java için daha fazla hazır destek sunar ve derste
    ekran görüntüleri onunla alınır. VS Code kullanıyorsanız "Extension Pack for Java" eklentisini kurun.

??? question "`mvn test` hata veriyor."

    Önce `java -version` ve `mvn -v` çıktılarında Java 21 veya üstünü gördüğünüzden, komutu repo
    klasörünün içinde çalıştırdığınızdan emin olun. Sorun devam ederse hata çıktısıyla birlikte ders
    sorumlusuna e-posta gönderin.
