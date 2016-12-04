package com.eternallove.demo.mywechat.util;

import android.content.Context;
import android.util.Log;

import com.eternallove.demo.mywechat.db.MyWeChatDB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/12/4
 */
public class JsonUtil {
    private String mjsonStr;
    private Context mcontext;
    private MyWeChatDB myWeChatDB;
    public JsonUtil(Context context,String jsonStr){
        this.mjsonStr = jsonStr;
        this.mcontext = context;
    }
    public void refreshSQLite(){
        if(this.mjsonStr == null) return;
        this.myWeChatDB = MyWeChatDB.getInstance(mcontext);
        try {
            JSONObject jsonObj = new JSONObject(this.mjsonStr);
            JSONArray contacts = jsonObj.getJSONArray("contacts");
            for(int i =0;i<contacts.length();i++){
                JSONObject c = contacts.getJSONObject(i);
                String id = c.getString("id");
                String user_id = c.getString("user_id");
                String contact_id = c.getString("contact_id");
                String name = c.getString("name");
                String avatar = c.getString("avatar");
                if("".equals(avatar))avatar =null;
                this.myWeChatDB.saveContacts(id,user_id,contact_id,name,avatar);
            }
            JSONArray moment = jsonObj.getJSONArray("moment");
            for(int i=0;i<moment.length();i++){
                JSONObject m = moment.getJSONObject(i);
                String id = m.getString("id");
                String user_id = m.getString("user_id");
                long publish_date = m.getLong("publish_date");
                String jsonContent = m.getString("jsonContent");
                this.myWeChatDB.saveMoment(id,user_id,publish_date,jsonContent);
            }
            JSONArray like = jsonObj.getJSONArray("like");
            for(int i =0;i<like.length();i++){
                JSONObject l = like.getJSONObject(i);
                String id = l.getString("id");
                String moment_id = l.getString("moment_id");
                String user_id = l.getString("user_id");
                this.myWeChatDB.saveLike(id,moment_id,user_id);
            }
            JSONArray comment = jsonObj.getJSONArray("comment");
            for(int i =0;i<comment.length();i++){
                JSONObject c = comment.optJSONObject(i);
                String id = c.getString("id");
                String moment_id = c.getString("moment_id");
                String initiator_id = c.getString("initiator_id");
                String recipient_id = c.getString("recipient_id");
                if("".equals(recipient_id)) recipient_id = null;
                String content = c.getString("content");
                this.myWeChatDB.saveComment(id,moment_id,initiator_id,recipient_id,content);
            }
        } catch (final JSONException e) {
            Log.e(JsonUtil.class.getName(), "Json parsing error: " + e.getMessage());
        }
    }

    public String getJsonStr() {
        return mjsonStr;
    }

    public void setJsonStr(String jsonStr) {
        this.mjsonStr = jsonStr;
    }
}
