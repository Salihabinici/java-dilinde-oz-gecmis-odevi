package org.example;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(">> Özgeçmiş verileri yükleniyor...");

            // Belirttiğin masaüstü fotoğrafının tam yolu:
            String photoPath = "C:\\Users\\slhbn\\OneDrive\\Masaüstü\\WhatsApp Image 2026-08-26 at 20.05.41.jpeg";

            PersonalInfo personalInfo = new PersonalInfo(
                    "Saliha Binici",
                    "Yazılım Mühendisi / Software Engineer",
                    "salihabinici@example.com",
                    "+90 555 123 45 67",
                    "Kırklareli, Türkiye",
                    photoPath
            );

            Resume resume = new Resume(personalInfo);

            // Ödev gereksinimi: 3 adet hayali iş deneyimi
            resume.addExperience(new Experience(
                    "NovaTech Yazilim Çözümleri",
                    "Kıdemli Java Geliştirici",
                    "2024 - Günümüz",
                    "Mikroservis mimarisiyle RESTful API geliştirilmesi, Docker ortamlarının yönetimi ve yüksek hacimli veri akışlarının nesne yönelimli tasarımı."
            ));

            resume.addExperience(new Experience(
                    "Bulut Bilişim Sistemleri A.Ş.",
                    "Yazılım Mühendisi",
                    "2022 - 2024",
                    "Kurumsal Java backend servislerinin geliştirilmesi, veri tabanı sorgu optimizasyonları ve JUnit birim testlerinin uygulanması."
            ));

            resume.addExperience(new Experience(
                    "InnoSoft AR-GE Ltd.",
                    "Aday Mühendis",
                    "2021 - 2022",
                    "Nesne tabanlı modelleme analizi, Git tabanlı iş akışları ve algoritma performans geliştirme çalışmalarında görev alındı."
            ));

            // PDF oluşturma servisi çağrılıyor (Ayrı sınıfta işlem kuralı)
            String outputFilePath = "ozgecmis.pdf";
            PdfResumeGenerator generator = new PdfResumeGenerator();

            System.out.println(">> PDF oluşturuluyor...");
            generator.generatePdf(resume, outputFilePath);
            System.out.println(">> PDF başarıyla oluşturuldu: " + outputFilePath);

            // PDF'i otomatik açarak seni doğrudan özgeçmişine götüren kısım:
            File pdfFile = new File(outputFilePath);
            if (Desktop.isDesktopSupported() && pdfFile.exists()) {
                System.out.println(">> Özgeçmiş açılıyor...");
                Desktop.getDesktop().open(pdfFile);
            }

        } catch (IOException e) {
            System.err.println("Bir hata oluştu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}