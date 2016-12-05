package com.eternallove.demo.mywechat.util;

import android.util.Log;

import com.eternallove.demo.mywechat.modle.CommentBean;
import com.eternallove.demo.mywechat.modle.LikeBean;
import com.eternallove.demo.mywechat.modle.LinkBean;
import com.eternallove.demo.mywechat.modle.MomentBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/12/4
 */
public class JsonConcentUtil {
    private String JsonStr;
    private String mContent;
    private LinkBean mLinkData;
    private List<String> mImageList;
    public JsonConcentUtil(String JsonConcent){
        if (JsonConcent != null) {
            try {
                JSONObject jsonObj = new JSONObject(JsonConcent);
                if(jsonObj.has("content")) {
                    this.mContent = jsonObj.getString("content");
                } else {
                    this.mContent = null;
                }
                if(jsonObj.has("imageList")) {
                    JSONArray imageList = jsonObj.getJSONArray("imageList");
                    if (imageList != null && imageList.length() > 0) {
                        ArrayList<String> mimageList = new ArrayList<>();
                        for (int i = 0; i < imageList.length(); i++) {
                            JSONObject img = imageList.getJSONObject(i);
                            mimageList.add(img.getString("img"));
                        }
                        this.mImageList = mimageList;
                    } else {
                        this.mImageList = null;
                    }
                } else {
                    this.mImageList = null;
                }
                if(jsonObj.has("link")) {
                    JSONObject link = jsonObj.getJSONObject("link");
                    String url = link.getString("url");
                    String lingkimg = null;
                    if(link.has("lingimg")){
                        lingkimg = link.getString("lingimg");
                    }
                    String linktitle = link.getString("linktitle");
                    this.mLinkData = new LinkBean(url, lingkimg, linktitle);
                } else {
                    this.mLinkData = null;
                }
            } catch (final JSONException e) {
                Log.e(JsonConcentUtil.class.getName(), "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(JsonConcentUtil.class.getName(), "Couldn't get json from server.");
        }
    }
    public MomentBean getMoment(){
        MomentBean momentBean = new MomentBean();
        momentBean.setContent(this.mContent);
        momentBean.setImageList(this.mImageList);
        momentBean.setLinkData(this.mLinkData);
        return momentBean;
    }
}
