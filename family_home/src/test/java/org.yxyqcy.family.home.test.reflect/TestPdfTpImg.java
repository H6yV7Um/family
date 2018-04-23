package org.yxyqcy.family.home.test.reflect;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import static org.jpedal.io.ObjectStore.time;

/**
 * Created by lcy on 17/11/17.
 */
public class TestPdfTpImg {

    public static void main(String[] args) {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        TestPdfTpImg.pdf2Img_icepdf("/working/test/ang.pdf", "/working/test/pdf/");
                    }
                }
        ).start();



        try {
            Thread.sleep(120000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void pdf2Img_icepdf(String pdfPath, String imgPath) {
        Document document = new Document();
        try {
            document.setFile(pdfPath);
        } catch (PDFException e) {
            e.printStackTrace();
        } catch (PDFSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        float scale = 2.5f;//缩放比例
        float rotation = 0f;//旋转角度
        for (int i = 0; i < document.getNumberOfPages(); i++) {
            BufferedImage image = (BufferedImage)document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
            RenderedImage rendImage = image;
            try {
                File file = new File(imgPath+"" + i + ".png");
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
    }

    /**
     * rederImageWithDPI的第二个参数为dpi分辨率单位，可根据需求调节大小，代码第八行提供了架包里另一种转图片的方法，第二个参数为缩放比。
     * @param pdfPath
     * @param imgPath
     */
    public static void pdf2Img_pdfbox(String pdfPath, String imgPath) {
        File file = new File(pdfPath);
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            long timeTmp = System.currentTimeMillis();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                //BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, "PNG", new File(imgPath+ i+".png"));
                long time2 = System.currentTimeMillis();
                timeTmp = time2;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("icepdf over..."+(System.currentTimeMillis()-time));
    }

    public void pdf2Img_jpedal() {
        /*PdfDecoder decode_pdf = new PdfDecoder(true);
        try {
            decode_pdf.openPdfFile("c:\\test.pdf"); //file
            decode_pdf.openPdfFile("C:/jpedalPDF.pdf", "password"); //encrypted file
            decode_pdf.openPdfArray(bytes); //bytes is byte[] array with PDF
            decode_pdf.openPdfFileFromURL("http://www.mysite.com/jpedalPDF.pdf",false);
            decode_pdf.openPdfFileFromInputStream(in, false);

            int start = 1, end = decode_pdf.getPageCount();
            for (int i = start; i < end + 1; i++) {
                BufferedImage img = decode_pdf.getPageAsImage(i);
                try {
                    ImageIO.write(img, "png", new File("C:\\jpedal_image.png"));
                    } catch (IOException e) {
                    e.printStackTrace();
                    }
            }
            decode_pdf.closePdfFile();
        } catch (PdfException e) {
            e.printStackTrace();
        }*/
    }
}
