package org.yxyqcy.family.common.util;

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

/**
 * Created by lcy on 17/11/14.
 */
public class CutPDFToImageUtil {


    private String sourcePath;

    private String producePath;

    public CutPDFToImageUtil() {
    }

    public CutPDFToImageUtil(String sourcePath,String producePath) {
        this.sourcePath = sourcePath;
        this.producePath = producePath;
    }

    /*@Override
    public void run() {
        this.changePdfToImgByPdfBox(this.sourcePath,this.producePath);
    }*/

    /**
     * pdf renderer
     * 转换pdf 1.4以上版本时 转换失败
     * @return
     */
    /*
    public  int changePdfToImgByPdfRender() {
        int countPage =0;
        try {
            File file = new File(sourcePath);
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            //nio
            FileChannel channel = raf.getChannel();
            MappedByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY,
                    0, channel.size());
            PDFFile pdffile = new PDFFile(buf);
            //创建图片文件夹
            File dirfile = new File(producePath);
            if(!dirfile.exists()){
                dirfile.mkdirs();
            }
            //获得图片页数
            countPage = pdffile.getNumPages();
            //多线程
            CountDownLatch latch=new CountDownLatch(countPage);
            for (int i = 1; i <= pdffile.getNumPages(); i++) {
                PDFPage page = pdffile.getPage(i);
                Rectangle rect = new Rectangle(0, 0, ((int) page.getBBox()
                        .getWidth()), ((int) page.getBBox().getHeight()));
                int n = 1;
                // 图片清晰度（n>0且n<7）【pdf放大参数】
                Image img = page.getImage(rect.width * n, rect.height * n,
                        rect, // 放大pdf到n倍，创建图片。
                        null, // null for the ImageObserver
                        true, // fill background with white
                        true // block until drawing is done
                );
                BufferedImage tag = new BufferedImage(rect.width * n,
                        rect.height * n, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(img, 0, 0, rect.width * n,
                        rect.height * n, null);

                FileOutputStream out = new FileOutputStream(producePath+ File.separator + i
                        + ".png");
                // 输出到文件流
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                JPEGEncodeParam param2 = encoder.getDefaultJPEGEncodeParam(tag);
                param2.setQuality(1f, true);
                // 1f~0.01f是提高生成的图片质量
                encoder.setJPEGEncodeParam(param2);
                encoder.encode(tag);
                // JPEG编码
                out.close();
            }

            channel.close();
            raf.close();
            unmap(buf);
            // 如果要在转图片之后删除pdf，就必须要这个关闭流和清空缓冲的方法
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countPage;

    }

    public static void unmap(final Object buffer) {
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                try {
                    Method getCleanerMethod = buffer.getClass().getMethod(
                            "cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    sun.misc.Cleaner cleaner = (sun.misc.Cleaner) getCleanerMethod
                            .invoke(buffer, new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
    */

    /**
     * ice pdf
     * @param pdfPath
     * @param imgPath
     * @return
     */
    public  int changePdfToImgByIcePdf(String pdfPath, String imgPath) {
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
                File file = new File(imgPath+"" + (i+1) + ".png");
                ImageIO.write(rendImage, "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            image.flush();
        }
        document.dispose();
        return document.getNumberOfPages();
    }

    /**
     *
     * pdfbox
     * rederImageWithDPI的第二个参数为dpi分辨率单位，可根据需求调节大小，代码第八行提供了架包里另一种转图片的方法，第二个参数为缩放比。
     * @param pdfPath
     * @param imgPath
     */
    public  int changePdfToImgByPdfBox(String pdfPath, String imgPath) {
        File file = new File(pdfPath);
        //创建图片文件夹
        File dirfile = new File(imgPath);
        if(!dirfile.exists()){
            dirfile.mkdirs();
        }
        try {
            PDDocument doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();
            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 144);
                //BufferedImage image = renderer.renderImage(i, 2.5f);
                ImageIO.write(image, "PNG", new File(imgPath+ File.separator + (i+1)+".png"));
            }
            doc.close();
            return pageCount;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
