package org.example;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.IOException;

public class PdfResumeGenerator {

    public void generatePdf(Resume resume, String outputPath) throws IOException {
        PdfWriter writer = new PdfWriter(outputPath);
        PdfDocument pdfDoc = new PdfDocument(writer);
        pdfDoc.setDefaultPageSize(PageSize.A4);

        Document document = new Document(pdfDoc);
        document.setMargins(30, 35, 30, 35);

        // Windows Arial Fontu (Türkçe karakterlerin eksiksiz çıkması için UTF-8 tanımlandı)
        String fontPath = "C:/Windows/Fonts/arial.ttf";
        String fontBoldPath = "C:/Windows/Fonts/arialbd.ttf";

        PdfFont normalFont = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
        PdfFont boldFont = PdfFontFactory.createFont(fontBoldPath, PdfEncodings.IDENTITY_H);

        // Varsayılan fontu belgeye uygula
        document.setFont(normalFont);

        // Header Tablosu (Sol %75 Bilgiler, Sağ %25 Fotoğraf)
        Table headerTable = new Table(UnitValue.createPercentArray(new float[]{75, 25}));
        headerTable.setWidth(UnitValue.createPercentValue(100));

        PersonalInfo info = resume.getPersonalInfo();

        // Sol Kolon - İsim ve Bilgiler
        Cell infoCell = new Cell().setBorder(null);
        infoCell.add(new Paragraph(info.getFullName())
                .setFont(boldFont)
                .setFontSize(22)
                .setFontColor(new DeviceRgb(26, 54, 93)));
        infoCell.add(new Paragraph(info.getTitle())
                .setFont(normalFont)
                .setFontSize(13)
                .setFontColor(new DeviceRgb(74, 85, 104)));
        infoCell.add(new Paragraph("E-posta: " + info.getEmail() + " | Tel: " + info.getPhone() + " | Konum: " + info.getLocation())
                .setFont(normalFont)
                .setFontSize(10)
                .setFontColor(ColorConstants.DARK_GRAY));
        headerTable.addCell(infoCell);

        // Sağ Kolon - Fotoğraf
        Cell photoCell = new Cell().setBorder(null);
        File photoFile = new File(info.getPhotoPath());
        if (photoFile.exists()) {
            ImageData imgData = ImageDataFactory.create(info.getPhotoPath());
            Image profileImage = new Image(imgData);
            profileImage.scaleToFit(95, 95);
            photoCell.add(profileImage);
        } else {
            photoCell.add(new Paragraph("[Fotoğraf bulunamadı]").setFont(normalFont).setFontSize(8).setItalic());
        }
        headerTable.addCell(photoCell);

        document.add(headerTable);
        document.add(new Paragraph("\n"));

        // Ayraç Çizgisi
        Table divider = new Table(UnitValue.createPercentArray(new float[]{100}));
        divider.setWidth(UnitValue.createPercentValue(100));
        divider.setBackgroundColor(new DeviceRgb(26, 54, 93));
        divider.setHeight(2);
        document.add(divider);

        // İş Deneyimi Başlığı
        document.add(new Paragraph("\nİŞ DENEYİMİ")
                .setFont(boldFont)
                .setFontSize(14)
                .setFontColor(new DeviceRgb(26, 54, 93)));

        // 3 Hayali Deneyim Ekleme
        for (Experience exp : resume.getExperiences()) {
            Table expHeaderTable = new Table(UnitValue.createPercentArray(new float[]{70, 30}));
            expHeaderTable.setWidth(UnitValue.createPercentValue(100));

            Cell roleCell = new Cell().setBorder(null)
                    .add(new Paragraph(exp.getPosition() + " - " + exp.getCompanyName()).setFont(boldFont).setFontSize(11));
            Cell dateCell = new Cell().setBorder(null)
                    .add(new Paragraph(exp.getDateRange()).setFont(normalFont).setFontSize(10).setFontColor(ColorConstants.GRAY));

            expHeaderTable.addCell(roleCell);
            expHeaderTable.addCell(dateCell);
            document.add(expHeaderTable);

            document.add(new Paragraph(exp.getDescription())
                    .setFont(normalFont)
                    .setFontSize(10)
                    .setFontColor(ColorConstants.BLACK));
            document.add(new Paragraph("\n"));
        }

        document.close();
    }
}