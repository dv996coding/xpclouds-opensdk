package io.github.dv996coding.util;

import com.openhtmltopdf.swing.Java2DRenderer;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;

/**
 * html 转图片打印
 *
 * @author JavaLyl
 */
public class HtmlToImageUtil {
    private Integer paperWidth;
    private Integer paperHeight;

    public HtmlToImageUtil(Integer paperWidth, Integer paperHeight) {
        this.paperHeight = paperHeight;
        this.paperWidth = paperWidth;
    }

    public HtmlToImageUtil(Integer paperWidth) {
        this(paperWidth, -1);
    }

    /**
     * 最终生成海报
     *
     * @param content html内容
     * @return
     */
    public String getHtml2ImageContent(String content) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        Document document = null;
        StringReader reader = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            reader = new StringReader(content);
            InputSource inputSource = new InputSource(reader);
            document = documentBuilder.parse(inputSource);
            Java2DRenderer renderer = new Java2DRenderer(document, paperWidth, paperHeight);
            ImageIO.write(renderer.getImage(), "png", stream);
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将图片转换成Base64编码
     *
     * @param image 待处理图片
     * @return
     */

    public String getImage2Base64Str(BufferedImage image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", stream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }
}
