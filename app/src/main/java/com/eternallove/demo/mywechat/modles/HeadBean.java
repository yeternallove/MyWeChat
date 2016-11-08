package com.eternallove.demo.mywechat.modles;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/7
 */
public class HeadBean {
    private String mHeadBackground;
    private String mHeadAvatar;
    private String mHeadName;

    public HeadBean(String headBackground, String headAvatar, String headName) {
        mHeadBackground = headBackground;
        mHeadAvatar = headAvatar;
        mHeadName = headName;
    }

    public String getHeadBackground() {
        return mHeadBackground;
    }

    public void setHeadBackground(String headBackground) {
        mHeadBackground = headBackground;
    }

    public String getHeadAvatar() {
        return mHeadAvatar;
    }

    public void setHeadAvatar(String headAvatar) {
        mHeadAvatar = headAvatar;
    }

    public String getHeadName() {
        return mHeadName;
    }

    public void setHeadName(String headName) {
        mHeadName = headName;
    }
}
