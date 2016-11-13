package com.eternallove.demo.mywechat.modle;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/7
 */
public class GeneralBean {
    private String mAvatar;
    private String mName;
    private String Content;
    private List<String> mImageList;
    private Date mPublishDate;

    public GeneralBean(String avatar, String name, String content,
                       List<String> imageList, Date publishDate) {
        mAvatar = avatar;
        mName = name;
        Content = content;
        mImageList = imageList;
        mPublishDate = publishDate;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String avatar) {
        mAvatar = avatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public List<String> getImageList() {
        return mImageList;
    }

    public void setImageList(List<String> imageList) {
        mImageList = imageList;
    }

    public Date getPublishDate() {
        return mPublishDate;
    }

    public void setPublishDate(Date publishDate) {
        mPublishDate = publishDate;
    }
}
