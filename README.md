
# Java ile PDF Formatında Özgeçmiş (CV) Oluşturucu

Bu proje, Nesne Yönelimli Programlama (OOP) prensiplerine uygun olarak tasarlanmış, dinamik verilerle PDF formatında Türkçe karakter destekli ve fotoğraflı özgeçmiş üreten bir Java uygulamasıdır.

## 📌 Proje Mimarisi ve Kullanılan Bileşenler

Ödev yönergesine uygun olarak PDF oluşturma mantığı `main` metodu içinde tutulmamış, modüler ve nesne yönelimli bir mimariyle ayrı sınıflara bölünmüştür:

1. **`PersonalInfo` (Model Sınıfı):**
   * Kişinin adı, unvanı, iletişim bilgileri ve vesikalık fotoğrafının dosya yolunu tutar.
   * Kapsülleme (Encapsulation) ilkesine uygun olarak alanlar `private` tutulmuş, kontrollü erişim `getter` metotları ile sağlanmıştır.

2. **`Experience` (Model Sınıfı):**
   * Şirket adı, pozisyon, çalışma tarih aralığı ve iş tanımını temsil eder.
   * Her bir iş deneyimi bağımsız bir nesne olarak bellekte (Heap) üretilir.

3. **`Resume` (Ana Veri Toplayıcı Sınıf):**
   * `PersonalInfo` nesnesi ile `Experience` nesnelerinden oluşan bir listeyi (`List<Experience>`) bünyesinde toplar (Aggregation / Composition).
   * Yönergede talep edilen 3 adet iş deneyimini liste yapısında dinamik olarak muhafaza eder.

4. **`PdfResumeGenerator` (Servis Sınıfı):**
   * PDF motoru olarak **iText 7** kütüphanesini kullanır.
   * Belge düzenini, tablo yapılarını, sayfa kenar boşluklarını, renk paletini ve görsel boyutlandırmayı yönetir.
   * Türkçe karakter problemine karşılık Windows sistem fontlarından `Arial` fontunu UTF-8 (`IDENTITY_H`) kodlamasıyla entegre eder.

5. **`Main` (Başlatıcı / Driver Sınıf):**
   * Uygulamanın giriş noktasıdır. Veri modellerini örnekler (instantiate eder), gerekli verileri doldurur ve `PdfResumeGenerator` servisini tetikler.
   * PDF üretimi tamamlandıktan sonra `java.awt.Desktop` sınıfı aracılığıyla oluşturulan PDF dosyasını işletim sisteminin varsayılan PDF görüntüleyicisinde otomatik olarak açar.

## 🛠 Kullanılan Teknolojiler ve Bağımlılıklar

* **Java JDK:** 17+ / 21
* **Build Aracı:** Maven
* **PDF Kütüphanesi:** iText 7 Core (`kernel`, `layout`, `io`) v7.2.5

## 🚀 Çalıştırma

1. Projeyi bir Java IDE'sinde (IntelliJ IDEA) açın.
2. Maven bağımlılıklarının yüklenmesini sağlayın (`pom.xml`).
3. `Main.java` sınıfını çalıştırın.
4. Program çalıştığında proje ana dizininde `ozgecmis.pdf` dosyası üretilecek ve otomatik olarak ekranda açılacaktır.
