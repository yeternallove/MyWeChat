package com.eternallove.demo.mywechat.modle;

import android.util.Log;

import com.eternallove.demo.mywechat.util.JsonConcentUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/7
 */
public class MomentBean {
    private String mAvatar;
    private String mName;
    private String mContent;
    private LinkBean mLinkData;
    private List<String> mImageList;
    private List<LikeBean> mLikeData;
    private List<CommentBean> mCommentData;
    private Date mPublishDate;

    public MomentBean(){};

    public MomentBean(String avatar,String name,String JsonConcent,List<LikeBean> likeData,List<CommentBean> commentData,Date publishDate){

        this.mAvatar = avatar;
        this.mName = name;
        this.mLikeData = likeData;
        this.mCommentData = commentData;
        this.mPublishDate = publishDate;
        MomentBean momentBean = new JsonConcentUtil(JsonConcent).getMoment();
        this.mContent = momentBean.getContent();
        this.mImageList = momentBean.getImageList();
        this.mLinkData = momentBean.getLinkData();
    }

    public MomentBean(String avatar, String name, String content, LinkBean linkData,
                       List<String> imageList, List<LikeBean> likeData, List<CommentBean> commentData, Date publishDate) {
        this.mAvatar = avatar;
        this.mName = name;
        this.mContent = content;
        this.mLinkData = linkData;
        this.mImageList = imageList;
        this.mLikeData = likeData;
        this.mCommentData = commentData;
        this.mPublishDate = publishDate;
    }

    public String getAvatar() {
        return mAvatar;
    }

    public void setAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public LinkBean getLinkData() {
        return mLinkData;
    }

    public void setLinkData(LinkBean mLinkData) {
        this.mLinkData = mLinkData;
    }

    public List<String> getImageList() {
        return mImageList;
    }

    public void setImageList(List<String> mImageList) {
        this.mImageList = mImageList;
    }

    public List<LikeBean> getLikeData() {
        return mLikeData;
    }

    public void setLikeData(List<LikeBean> mLikeData) {
        this.mLikeData = mLikeData;
    }

    public List<CommentBean> getCommentData() {
        return mCommentData;
    }

    public void setCommentData(List<CommentBean> mCommentData) {
        this.mCommentData = mCommentData;
    }

    public Date getPublishDate() {
        return mPublishDate;
    }

    public void setPublishDate(Date mPublishDate) {
        this.mPublishDate = mPublishDate;
    }
}
