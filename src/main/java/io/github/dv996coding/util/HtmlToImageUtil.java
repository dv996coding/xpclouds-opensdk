package io.github.dv996coding.util;

import com.openhtmltopdf.swing.Java2DRenderer;
import io.github.dv996coding.contants.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(HtmlToImageUtil.class);
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
     * 将HTML内容转换为base64图片数据
     *
     * @param content html内容
     * @return base64图片数据
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
            ImageIO.write(renderer.getImage(),  Constant.IMAGE_FORMAT, stream);
            renderer.cleanup();
            return Base64.getEncoder().encodeToString(stream.toByteArray());
        } catch (ParserConfigurationException e) {
            log.error("getHtml2ImageContent ParserConfigurationException: {}", e);
        } catch (SAXException e) {
            log.error("getHtml2ImageContent SAXException: {}", e);
        } catch (IOException e) {
            log.error("getHtml2ImageContent IOException: ", e);
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    log.error("getHtml2ImageContent stream.close() IOException: ", e);
                }
            }
        }
        return null;
    }

    /**
     * 将图片转换成Base64编码
     *
     * @param image 待处理图片
     * @return base64图片数据
     */

    public String getImage2Base64Str(BufferedImage image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image,  Constant.IMAGE_FORMAT, stream);
        } catch (IOException e) {
            log.error("getImage2Base64Str IOException: ", e);
            return null;
        }
        return Base64.getEncoder().encodeToString(stream.toByteArray());
    }
}
