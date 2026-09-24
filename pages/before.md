# Giriş

Nesneye Yönelik Programlama dersine hoş geldiniz! Bu sayfa dersin nasıl işlediğini ve ilk haftadan
önce yapmanız gereken kurulumları anlatır.

## 🧰 Kurulum { #kurulum }

Derste kullanılan araçların hepsi ücretsizdir.

| Araç | Ne için | Platform | Gerekli mi? |
|---|---|---|---|
| [JDK 21 (Eclipse Temurin)](https://adoptium.net/) | Java derleyicisi ve çalışma ortamı | Hepsi | Evet |
| [Maven](https://maven.apache.org/download.cgi) | Derleme, bağımlılık yönetimi, testleri çalıştırma | Hepsi | Evet |
| [IntelliJ IDEA Community](https://www.jetbrains.com/idea/download/) | Java IDE'si (önerilen) | Hepsi | Evet (veya VS Code) |
| [VS Code](https://code.visualstudio.com/) + [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) | Hafif alternatif editör | Hepsi | İsteğe bağlı |
| [Git](https://git-scm.com/) | Materyali indirme ve güncelleme | Hepsi | Önerilir |
| [draw.io](https://app.diagrams.net/) | UML diyagramı çizimi | Web, masaüstü | İsteğe bağlı |

macOS'ta Homebrew ile: `brew install --cask temurin@21` ve `brew install maven`.
Windows'ta: `winget install EclipseAdoptium.Temurin.21.JDK` ve `winget install Apache.Maven`
(ya da Maven'i IntelliJ'nin içindeki sürümüyle kullanabilirsiniz).

### Kurulumu doğrulama

```bash
java -version   # 21 veya üstü
mvn -v
git --version
```

Ardından repoyu indirip testleri çalıştırın:

```bash
git clone https://github.com/aligunesgit/NesneyeYonelikProgramlama.git
cd NesneyeYonelikProgramlama
mvn test
```

`BUILD SUCCESS` çıktısını görüyorsanız ortamınız hazırdır.
