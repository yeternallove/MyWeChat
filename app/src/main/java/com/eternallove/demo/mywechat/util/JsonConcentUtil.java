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
                this.mContent = jsonObj.getString("content");
                if("".equals(this.mContent)) this.mContent = null;
                JSONArray imageList = jsonObj.getJSONArray("imageList");
                if(imageList != null) {
                    ArrayList<String> mimageList = new ArrayList<>();
                    for (int i = 0; i < imageList.length(); i++) {
                        JSONObject img = imageList.getJSONObject(i);
                        mimageList.add(img.getString("img"));
                    }
                    this.mImageList = mimageList;
                } else {
                    this.mImageList = null;
                }
                JSONObject link = jsonObj.getJSONObject("link");
                if(link==null || "".equals(link)){
                    this.mLinkData = null;
                } else {
                    String url = link.getString("url");
                    String lingkimg = link.getString("lingimg");
                    String linktitle = link.getString("linktitle");
                    this.mLinkData = new LinkBean(url,lingkimg,linktitle);
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
