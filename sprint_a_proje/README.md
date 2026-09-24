# 🚀 Proje Sprinti A

<p align="center"><em>Hafta 8 (ara sınav haftası)</em></p>

Bu haftada yeni konu anlatılmaz. M1–M7'de öğrenilenleri (sınıflar, kapsülleme, ilişkiler, kalıtım, çok biçimlilik, arayüzler) tek bir projede birleştirme.

## 🎯 Amaç

İlk yedi haftada kavramları küçük, ayrı örneklerle öğrendiniz. Bu sprintte bunları **tek bir
çalışan programda** bir araya getireceksiniz: bir problemi okuyup sınıflara ayırmak, sorumlulukları
dağıtmak, UML ile tasarlamak, kodlamak ve testlerle doğrulamak. Değerlendirilen şey programın büyüklüğü
değil, **nesneye yönelik tasarımın doğruluğu** ve kodu açıklayabilmenizdir.

Sprint A **bireyseldir** (öğretim üyesi farklı duyurmadıkça).

!!! warning "Yalnızca M1–M7 araçları"

    Bu projede kullanabileceğiniz araçlar: sınıflar ve nesneler, kapsülleme (`private` alanlar,
    doğrulayan metotlar), ilişkiler (ilişkilendirme, toplama, bileşim), kalıtım, çok biçimlilik,
    soyut sınıf, arayüz ve `enum`. Koleksiyon olarak yalnızca `ArrayList`'in temel kullanımı
    (`add`, `get`, `remove`, `size`, `for` döngüsü) serbesttir. Hatalı girdiye karşı yalnızca
    `IllegalArgumentException` fırlatabilirsiniz. Özel istisnalar (M8), `Map`/`Set` ve kendi generic
    sınıflarınız (M9), lambda/stream (M10) Sprint B'nin konusudur; burada **kullanmayın**.

## 🎯 Beklenen çıktılar

* Problemin tanımı ve gereksinim listesi
* UML sınıf diyagramı (Mermaid veya draw.io)
* Java kaynak kodu (Maven projesi)
* JUnit 5 testleri

## 📌 Proje konuları

Aşağıdakilerden **birini** seçin. Kendi konunuzu önermek isterseniz, aynı ölçülebilir kriterleri
karşılayacak biçimde yazıp öğretim üyesinin onayına sunun.

Tüm konularda ortak asgari koşullar:

* En az **6 sınıf** (arayüz ve `enum` dahil), en az **1 soyut sınıf** ya da **1 arayüz**, en az
  **1 `enum`**
* En az bir **kalıtım hiyerarşisi** (bir üst tür, en az iki alt tür) ve bu hiyerarşi üzerinde
  **çok biçimli** bir metot çağrısı (üst tür referansıyla, `instanceof` zinciri olmadan)
* En az bir **bileşim** ya da **toplama** ilişkisi (bir nesnenin `ArrayList` ile başka nesneleri tutması)
* Tüm alanlar `private`; kuralları koruyan metotlar geçersiz girdide `IllegalArgumentException` fırlatır
* Küçük bir `main` senaryosu (uygulama sınıfı) programın ana akışını gösterir

### A1. Otopark yönetimi

Katlı bir otoparka araçlar giriş yapar, park eder ve çıkarken ücret öder. Otomobil, motosiklet ve
minibüs farklı yer kaplar ve farklı saatlik ücrete tabidir.

* **Zorunlu özellikler:** araç girişi/çıkışı, boş yer kontrolü, park süresine göre ücret, doluluk raporu
* **Ölçülebilir kriterler:** `Arac` soyut sınıfı ve en az 3 alt sınıfı; `ucretHesapla(int saat)` çok
  biçimli; `AracTuru` ya da `KatTipi` enum; dolu otoparka giriş reddedilir

### A2. Kütüphane ödünç sistemi

Bir kütüphane kitap, dergi ve DVD gibi materyalleri üyelere ödünç verir. Her materyal türünün ödünç
süresi ve gecikme cezası farklıdır.

* **Zorunlu özellikler:** materyal ekleme, ödünç verme, iade, gecikme cezası, üye başına ödünç sınırı
* **Ölçülebilir kriterler:** `Materyal` soyut sınıfı ve en az 3 alt sınıfı; `gecikmeCezasi(int gun)`
  çok biçimli; `UyeTipi` enum (öğrenci/personel, farklı sınır); ödünçteki materyal tekrar verilemez

### A3. Hayvan barınağı

Bir barınak hayvanları kaydeder, bakımlarını izler ve sahiplendirme başvurularını değerlendirir.

* **Zorunlu özellikler:** hayvan kaydı, aşı/bakım kaydı, sahiplendirme, türe göre günlük mama ihtiyacı
* **Ölçülebilir kriterler:** `Hayvan` soyut sınıfı ve en az 3 alt sınıfı; `gunlukMamaGram()` çok
  biçimli; `Sahiplenilebilir` arayüzü; `SaglikDurumu` enum; aşısı eksik hayvan sahiplendirilemez

### A4. Basit oyun karakterleri

Sıra tabanlı bir savaş oyununda savaşçı, büyücü ve okçu karakterleri birbirine saldırır. Her
karakterin saldırı ve savunma biçimi farklıdır.

* **Zorunlu özellikler:** karakter oluşturma, saldırı ve hasar hesabı, can puanı, envanterde eşya,
  tur sonu kazanan belirleme
* **Ölçülebilir kriterler:** `Karakter` soyut sınıfı ve en az 3 alt sınıfı; `saldir(Karakter hedef)`
  çok biçimli; `Iyilestirebilir` arayüzü; `Sinif` ya da `EsyaTuru` enum; can puanı 0'ın altına inmez

### A5. Sinema bilet satışı

Bir sinema salonları, seansları ve koltukları yönetir. Tam, öğrenci ve 65 yaş üstü biletlerin fiyatı
farklıdır.

* **Zorunlu özellikler:** salon ve seans tanımı, koltuk seçimi, bilet satışı ve iptali, seans doluluk
  oranı, günlük hasılat
* **Ölçülebilir kriterler:** `Bilet` soyut sınıfı (ya da `Fiyatlandirilabilir` arayüzü) ve en az 3
  alt türü; `fiyat()` çok biçimli; `KoltukDurumu` enum; satılmış koltuk tekrar satılamaz

## 📦 Teslim paketi

Tek bir Maven projesi, sıkıştırılmış klasör ya da Git deposu bağlantısı olarak teslim edilir:

```text
soyad_ad_sprintA/
├── pom.xml                         ← Java 21, JUnit 5
├── README.md                       ← konu, gereksinim listesi, nasıl çalıştırılır
├── uml/
│   └── sinif_diyagrami.md          ← Mermaid (ya da .drawio + .png)
└── src/
    ├── main/java/proje/            ← kaynak kod
    │   ├── Arac.java
    │   ├── ...
    │   └── OtoparkUygulamasi.java  ← main
    └── test/java/proje/            ← testler (aynı paket yapısı)
        ├── OtoparkTest.java
        └── ...
```

* **README.md:** konunun 3–5 cümlelik tanımı; numaralı **gereksinim listesi** (ör. "G3: Dolu otoparka
  araç girişi reddedilir"); `mvn test` ve programın nasıl çalıştırılacağı.
* **UML:** tüm sınıflar, önemli alan ve metotlar, ilişkiler ve çokluklar (M4). Diyagram kodla
  tutarlı olmalı.
* **Testler:** en az **12 test**; her gereksinim için en az bir test, sınır durumları dahil (ör. son
  boş yer, tam sınırda ödünç, 0 can puanı). Testler Hazırla/Çalıştır/Doğrula düzeninde yazılır.
* Proje klasöründe `mvn test` **hatasız geçmeli**. Derlenmeyen proje kod doğruluğu ve test
  ölçütlerinden puan alamaz.

## 🗓️ Çalışma planı

| Gün | Yapılacaklar | Gün sonunda elinizde olan |
|---|---|---|
| 1 | Konuyu seçin, gereksinimleri yazın, isimleri (sınıf adayları) ve fiilleri (metot adayları) çıkarın | README'de gereksinim listesi |
| 2 | UML sınıf diyagramını çizin; kalıtım hiyerarşisini ve ilişkileri belirleyin | `uml/sinif_diyagrami.md` |
| 3 | Maven projesini kurun; temel sınıfları, kurucuları ve doğrulamaları yazın; ilk testler | Derlenen proje, ilk 4–5 test |
| 4 | Kalıtım ve çok biçimli davranışları, ana senaryoyu tamamlayın | Çalışan `main` senaryosu |
| 5 | Testleri tamamlayın, UML'yi kodla eşleyin, README'yi bitirin, teslim edin | `mvn test` geçen teslim paketi |

!!! tip "Önce tasarım, sonra kod"

    UML'yi kodlamadan önce çizin ve kodladıkça güncelleyin. Bir sınıfın sorumluluğunu tek cümleyle
    söyleyemiyorsanız, muhtemelen iki sınıfa bölünmesi gerekir.

## 📋 Değerlendirme

| Ölçüt | Ağırlık | Neye bakılır? |
|---|:---:|---|
| Gereksinimler ve UML | %20 | Gereksinimler numaralı ve test edilebilir; UML eksiksiz, doğru ilişki türleri ve çokluklar, kodla tutarlı |
| OOP ilkelerinin doğru kullanımı | %30 | Kapsülleme (`private` alanlar, kuralı koruyan metotlar); anlamlı kalıtım hiyerarşisi; `instanceof` zinciri yerine çok biçimlilik; soyut sınıf/arayüz/enum'un yerinde kullanımı |
| Kod doğruluğu | %20 | Gereksinimlerin tamamı çalışıyor; geçersiz girdide `IllegalArgumentException`; `main` senaryosu hatasız |
| Testler | %20 | En az 12 test, her gereksinim kapsanmış, sınır durumları var, `mvn test` geçiyor |
| Kod kalitesi ve okunabilirlik | %10 | Java adlandırma kuralları, anlamlı adlar, kısa metotlar, tekrarsız kod, Javadoc |
| **Toplam** | **%100** | |

Asgari koşullardan (6 sınıf, kalıtım hiyerarşisi, enum vb.) biri eksikse ilgili ölçütün en fazla
yarısı verilir. Öğretim üyesi kısa bir sözlü kontrol isteyebilir; kodunuzu açıklayamazsanız ilgili
kısım değerlendirmeye alınmaz.

## ⚖️ Akademik dürüstlük ve yapay zekâ araçları

* Proje bireyseldir. Fikir alışverişi serbesttir; kod paylaşmak ve başkasının kodunu teslim etmek
  değildir.
* Yapay zekâ araçlarını (sohbet asistanları, kod tamamlayıcılar) **kullanabilirsiniz**, ama
  README'nin sonuna bir **"Araç beyanı"** bölümü ekleyin: hangi aracı, hangi iş için kullandınız
  (ör. "test fikirleri için", "derleme hatasını anlamak için").
* **Teslim ettiğiniz her satırı açıklayabilmelisiniz.** Sözlü kontrolde bir sınıfın neden soyut
  olduğunu, bir ilişkinin neden bileşim olduğunu ya da bir testin neyi doğruladığını
  açıklayamıyorsanız, o kısım sizin çalışmanız sayılmaz.
* İnternetten ya da kitaplardan aldığınız kod parçalarını kaynağıyla belirtin.
