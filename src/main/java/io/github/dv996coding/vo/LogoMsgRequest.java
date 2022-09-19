package io.github.dv996coding.vo;

/**
 * logo 上传
 *
 * @author star
 */
public class LogoMsgRequest extends RestRequest {
    /**
     * 打印机编号
     */
    private String sn;
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }
    /**
     * logo base64格式数据 用户指定
     */
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 图片大小，优先比对宽度，当原图的宽度>原图的高度时，按照指定大小，如果宽度操出指定大小，宽度不变，高度等比缩放，否则高度不变，宽度等不缩放
     */
    private Integer imageSize = 200;

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }

    /**
     * logo 写入打印机之后的提示内容，该接口必须要加限制，方式用户用来打印正式订单
     */
    private boolean printLogo = true;

    public boolean isPrintLogo() {
        return printLogo;
    }

    public void setPrintLogo(boolean printLogo) {
        this.printLogo = printLogo;
    }

    /**
     * 纸张类型，1默认间隙纸 2连续纸 3黑标纸
     */
    private Integer paperType = 1;

    public Integer getPaperType() {
        return paperType;
    }

    public void setPaperType(Integer paperType) {
        this.paperType = paperType;
    }

    /**
     * 是否直传，
     * true 直传方式不经过OSS，同时打印内容必须为打印机可识别的ZPL和TSPL指令。
     * false 为了兼容现有对接的开发者不受影响，走OSS，此时打印内容可以是Url链接也可以是XML方式
     */
    private boolean direct = true;

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }

    public LogoMsgRequest(String sn, String content) {
        this.sn = sn;
        this.content = content;
    }
}
