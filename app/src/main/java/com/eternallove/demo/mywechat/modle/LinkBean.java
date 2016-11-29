package com.eternallove.demo.mywechat.modle;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/28
 */
public class LinkBean {
    private String url;
    private String linkimg;
    private String linktitle;

    public LinkBean(String url, String linkimg, String linktitle) {
        this.url = url;
        this.linkimg = linkimg;
        this.linktitle = linktitle;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLinkimg() {
        return linkimg;
    }

    public void setLinkimg(String linkimg) {
        this.linkimg = linkimg;
    }

    public String getLinktitle() {
        return linktitle;
    }

    public void setLinktitle(String linktitle) {
        this.linktitle = linktitle;
    }
}