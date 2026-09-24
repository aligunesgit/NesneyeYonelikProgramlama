# 🏁 Proje Sprinti B

<p align="center"><em>Hafta 14 (final)</em></p>

Bu haftada yeni konu anlatılmaz. Tüm dönemi kapsayan final projesi: istisnalar, koleksiyonlar, lambda/stream, tasarım ilkeleri ve kalıplar.

## 🎯 Amaç

Sprint A'da nesneye yönelik temelleri tek bir programda birleştirdiniz. Final projesinde bir adım öteye
geçiyorsunuz: gerçekçi bir alan problemini, dönemin tüm araçlarıyla ve **gerekçelendirilmiş tasarım
kararlarıyla** çözeceksiniz. Hataları özel istisnalarla anlatan, veriyi uygun koleksiyonlarda tutan,
raporları stream'lerle üreten, bağımlılıkları arayüzlerle gevşetilmiş ve testlerle korunan bir sistem
bekliyoruz. "Çalışıyor" yetmez; **neden böyle tasarladığınızı** da yazılı ve sözlü olarak
açıklayacaksınız.

Sprint B **2–3 kişilik gruplarla** yapılır (öğretim üyesi farklı duyurmadıkça).

## 🎯 Beklenen çıktılar

* Problemin tanımı ve gereksinim listesi
* UML sınıf diyagramı (Mermaid veya draw.io)
* Java kaynak kodu (Maven projesi)
* JUnit 5 testleri

Bunlara ek olarak Sprint B'de bir **tasarım kararları raporu** (`TASARIM.md`) ve kısa bir **sunum**
istenir.

## 📌 Proje konuları

Aşağıdakilerden **birini** seçin ya da aynı kriterleri karşılayan kendi konunuzu onaya sunun.

Tüm konularda ortak asgari koşullar:

* 3 kişilik gruplarda en az **12 sınıf**, 2 kişilik gruplarda en az **10 sınıf** (arayüz, `enum`,
  `record` dahil); en az **2 arayüz**
* En az **2 özel istisna** sınıfı (M8), anlamlı adlarla (ör. `StokYetersizException`); istisnalar
  yakalandıkları yerde anlamlı biçimde ele alınır, yutulmaz
* **`List`'in yanında `Map` ve `Set`** kullanımı (M9); en az bir **generic** sınıf ya da metot
  (ör. `Depo<T>`)
* En az **3 stream raporu** (M10): filtreleme, gruplama (`Collectors.groupingBy`) ve sıralama/toplama
* En az **1 SOLID ilkesinin** kodda bilinçli uygulanması ve `TASARIM.md`'de gerekçelendirilmesi (M11)
* En az **2 tasarım kalıbı** (M12: Strategy, Observer, Factory, Singleton, Builder ya da gerekçeli
  başka bir GoF kalıbı); her biri `TASARIM.md`'de "hangi problemi çözüyor?" sorusuyla açıklanır
* Sprint A'daki OOP koşulları (kapsülleme, kalıtım/arayüz hiyerarşisi, çok biçimlilik) geçerliliğini korur

### B1. E-ticaret sipariş sistemi

Müşteriler ürünleri sepete ekler, sipariş verir ve ödeme yapar. Siparişin durumu değiştikçe müşteri
bilgilendirilir; kampanyalar ve kargo seçenekleri fiyatı etkiler.

* **Zorunlu özellikler:** ürün kataloğu, sepet, sipariş durumu takibi, en az iki ödeme ya da kargo
  seçeneği, stok düşümü, kategori bazında satış raporu
* **Ölçülebilir kriterler:** stok yetmezse özel istisna; ürünler `Map<String, Urun>` ile kodla
  bulunur; kargo ya da indirim hesabı değiştirilebilir (ör. Strategy); durum değişikliği bildirimleri
  (ör. Observer)

### B2. Hastane randevu sistemi

Hastalar bölüm ve doktor seçerek randevu alır, randevularını iptal eder. Doktorların çalışma saatleri
ve günlük randevu kapasitesi vardır.

* **Zorunlu özellikler:** doktor ve bölüm tanımı, uygun saat listesi, randevu alma/iptal, çakışma
  kontrolü, bölüm bazında doluluk raporu
* **Ölçülebilir kriterler:** çakışan ya da geçmiş tarihli randevuda özel istisna; bir doktorun dolu
  saatleri `Set` ile tutulur; `java.time.LocalDateTime` kullanımı; en az iki kalıp (ör. randevu
  nesnesi için Builder, bildirim kanalı için Factory)

### B3. Kargo takip sistemi

Bir kargo firması gönderileri kabul eder, şubeler arasında taşır ve teslim eder. Gönderici ve alıcı her
aşamada bilgilendirilir.

* **Zorunlu özellikler:** gönderi kabulü, ağırlık/mesafe/hizmet türüne göre ücret, şube hareketleri
  geçmişi, takip numarasıyla sorgulama, şube bazında gecikme raporu
* **Ölçülebilir kriterler:** geçersiz takip numarası ve yanlış durum geçişinde özel istisna; takip
  numarası → gönderi `Map`; durum `enum` ve geçiş kuralları; ücret hesabı için Strategy; durum
  değişikliği için Observer

### B4. Öğrenci bilgi sistemi

Öğrenciler ders kaydı yapar, öğretim üyeleri not girer, sistem ortalama ve transkript üretir.

* **Zorunlu özellikler:** öğrenci ve ders tanımı, kontenjan ve ön koşul kontrolü, not girişi, harf
  notu dönüşümü, dönem ve genel not ortalaması, bölüm bazında başarı raporu
* **Ölçülebilir kriterler:** kontenjan dolu ya da ön koşul eksikse özel istisna; öğrencinin aldığı
  dersler `Set`, ders kodu → ders `Map`; harf notu hesaplama farklı ölçeklerle değiştirilebilir
  (ör. Strategy); raporlar stream ile

### B5. Envanter ve stok yönetimi

Bir işletme birden fazla depoda ürün stoklarını izler. Stok kritik seviyenin altına inince uyarı
üretilir; giriş ve çıkış hareketleri kaydedilir.

* **Zorunlu özellikler:** ürün ve depo tanımı, stok girişi/çıkışı, depolar arası transfer, kritik
  stok uyarısı, hareket geçmişi, depo ve kategori bazında stok değeri raporu
* **Ölçülebilir kriterler:** yetersiz stokta ve bilinmeyen üründe özel istisna; generic bir
  `Depo<T extends Urun>` ya da benzeri; kritik stok uyarıları için Observer; ürün oluşturma için
  Factory ya da Builder

## 📦 Teslim paketi

Grup başına tek bir Git deposu (tercihen) ya da sıkıştırılmış Maven projesi:

```text
grupN_sprintB/
├── pom.xml                         ← Java 21, JUnit 5
├── README.md                       ← konu, grup üyeleri, gereksinim listesi, nasıl çalıştırılır
├── TASARIM.md                      ← tasarım kararları raporu
├── uml/
│   ├── sinif_diyagrami.md          ← Mermaid (ya da .drawio + .png)
│   └── sequence_diyagrami.md       ← en az bir ana senaryo için (isteğe bağlı, önerilir)
├── sunum.pdf
└── src/
    ├── main/java/proje/
    │   ├── model/                  ← alan sınıfları
    │   ├── servis/                 ← iş kuralları
    │   ├── istisna/                ← özel istisnalar
    │   └── Uygulama.java           ← main
    └── test/java/proje/
        ├── model/
        └── servis/
```

* **README.md:** konu tanımı, grup üyeleri ve her üyenin katkısı, numaralı gereksinim listesi,
  `mvn test` ve çalıştırma komutları.
* **UML:** alt paketlere göre düzenlenmiş sınıf diyagramı; arayüzler, soyut sınıflar, generic türler
  ve ilişkiler (çokluklarla). Diyagram kodla tutarlı olmalı.
* **Testler:** 3 kişilik gruplarda en az **25**, 2 kişilik gruplarda en az **20 test**; her
  gereksinim ve her özel istisna için en az bir test (`assertThrows`); kalıplar için davranış testleri
  (ör. strateji değişince sonuç değişir, gözlemci bildirim alır — sahte gözlemciyle).
* **TASARIM.md** (1–3 sayfa): kullanılan her kalıp ve ilke için **nerede** (sınıf adları), **hangi
  problemi çözdüğü** ve **kullanılmasaydı kodun nasıl olacağı**; değerlendirip **reddettiğiniz** en az
  bir alternatif ve nedeni.
* **Sunum:** 8–10 dakika; ana senaryonun canlı gösterimi, UML, iki tasarım kararı. Ardından her grup
  üyesine kod üzerinden soru sorulur.
* Proje klasöründe `mvn test` **hatasız geçmeli**.

## 🗓️ Çalışma planı

| Gün | Yapılacaklar | Gün sonunda elinizde olan |
|---|---|---|
| 1 | Konu seçimi, gereksinimler, iş bölümü; Git deposu ve Maven iskeleti | README (gereksinimler, üyeler), boş proje `mvn test` geçiyor |
| 2 | UML; alan modeli (model paketi), özel istisnalar; ilk testler | Sınıf diyagramı, model sınıfları ve testleri |
| 3 | İş kuralları (servis paketi), koleksiyonlar, kalıpların uygulanması | Ana senaryo uçtan uca çalışıyor |
| 4 | Stream raporları, eksik testler, kod gözden geçirme (grup içinde birbirinizin kodunu okuyun) | Tüm gereksinimler ve testler tamam |
| 5 | `TASARIM.md`, UML güncellemesi, sunum provası, teslim | Teslim paketi |

!!! tip "İş bölümü dikey olsun"

    "Biri UML çizer, biri kod yazar, biri test yazar" bölüşümü sözlü kontrolde herkesi zor duruma
    düşürür. Her üye bir **özellikten** (ör. sipariş akışı, raporlar, bildirimler) baştan sona,
    kodu ve testleriyle birlikte sorumlu olsun; kalıpları ve istisnaları herkes bilsin.

## 📋 Değerlendirme

| Ölçüt | Ağırlık | Neye bakılır? |
|---|:---:|---|
| Gereksinimler ve UML | %10 | Numaralı, test edilebilir gereksinimler; eksiksiz ve kodla tutarlı UML |
| OOP ilkelerinin doğru kullanımı | %20 | Kapsülleme, anlamlı kalıtım/arayüz hiyerarşisi, çok biçimlilik; özel istisnalar, uygun koleksiyon seçimi (`List`/`Set`/`Map`), generics, stream |
| Kod doğruluğu | %15 | Tüm gereksinimler çalışıyor; hatalar özel istisnalarla doğru katmanda ele alınıyor; `main` senaryosu hatasız |
| Testler | %15 | Asgari test sayısı, her gereksinim ve istisna kapsanmış, sınır durumları, kalıp davranış testleri, `mvn test` geçiyor |
| Kod kalitesi ve okunabilirlik | %10 | Paket düzeni, adlandırma, kısa metotlar, tekrarsız kod, Javadoc |
| Tasarım kararları raporu (`TASARIM.md`) | %15 | En az 2 kalıp ve 1 SOLID ilkesi doğru yerde, gerekçesi problemden yola çıkıyor; reddedilen alternatif tartışılmış |
| Sunum ve sözlü kontrol | %15 | Anlaşılır gösterim; her üye kendi kısmını ve projenin genel tasarımını açıklayabiliyor |
| **Toplam** | **%100** | |

Grup notu ortaktır; ancak sözlü kontrolde kendi kısmını açıklayamayan üyenin notu öğretim üyesi
tarafından ayrıca düşürülebilir. "Kalıp olsun diye kalıp" (problemi olmayan yerde kalıp) tasarım
raporu ölçütünden puan kazandırmaz; gerekçesiz Singleton kullanımı puan kaybettirir.

## ⚖️ Akademik dürüstlük ve yapay zekâ araçları

* Gruplar arası fikir alışverişi serbesttir; kod paylaşmak ve başka bir grubun (ya da önceki
  dönemlerin) kodunu teslim etmek değildir.
* Yapay zekâ araçlarını **kullanabilirsiniz**, ama README'nin sonuna bir **"Araç beyanı"** bölümü
  ekleyin: hangi aracı, hangi iş için ve hangi dosyalarda kullandınız.
* **Her grup üyesi, teslim edilen her satırı açıklayabilmelidir.** Sözlü kontrolde bir kalıbın neden
  seçildiğini, bir istisnanın nerede yakalandığını ya da bir stream raporunun ne yaptığını
  açıklayamıyorsanız, o kısım grubun çalışması sayılmaz.
* Git geçmişi, katkının dağılımını görmek için incelenebilir; her üye kendi değişikliklerini kendi
  hesabıyla işlesin (commit).
* Dış kaynaklardan alınan kod parçaları kaynağıyla belirtilir.
