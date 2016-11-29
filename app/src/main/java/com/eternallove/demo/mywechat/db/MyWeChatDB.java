package com.eternallove.demo.mywechat.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eternallove.demo.mywechat.modle.ContactsBean;
import com.eternallove.demo.mywechat.modle.GeneralBean;
import com.eternallove.demo.mywechat.modle.HeadBean;

import java.util.ArrayList;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/11
 */
public class MyWeChatDB {
    public static final String DB_NAME ="MyWeChat";
    public static final int VERSION = 1;

    private static MyWeChatDB myWeChatDB;
    private SQLiteDatabase db;

    private MyWeChatDB(Context context){
        MyWeChatOpenHelper dbHelper = new MyWeChatOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获得MyWeChatDB实例
     * @param context
     * @return
     */
    public synchronized static MyWeChatDB getInstance(Context context){
        if(myWeChatDB == null){
            myWeChatDB = new MyWeChatDB(context);
        }
        return myWeChatDB;
    }
    public void saveContent(String id,int user_id,String name,String email,String address,String gender,
                            String mobile,String home,String office){
        Cursor c = db.rawQuery("select * from Content where id = ?",new String[]{id});
       if(c.moveToFirst()) return;
        db.execSQL("INSERT INTO Content(id,user_id,name,email,mobile) VALUES(?,?,?,?,?)",new Object[]{id,user_id,name,email,mobile});
    }
    public ArrayList<ContactsBean> selectContacts(){
        ArrayList<ContactsBean> contactlist = new ArrayList<>();
        ContactsBean contact;
        Cursor c = db.rawQuery("select id,name,email,mobile from Content where user_id = ?",new String[]{0+""});
        while(c.moveToNext())
        {
            contact = new ContactsBean();
            contact.setId(c.getString(0));
            contact.setName(c.getString(1));
            contact.setEmail(c.getString(2));
            contact.setMobile(c.getString(3));
            contactlist.add(contact);
        }
        return contactlist;
    }
    public HeadBean refreshHead(){
        HeadBean headBean = new HeadBean();
        return headBean;
    }
    public ArrayList<GeneralBean> refreshGeneral(){
        ArrayList<GeneralBean> momentlist = new ArrayList<>();
        return momentlist;
    }
}
