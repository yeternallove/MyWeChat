package com.eternallove.demo.mywechat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/11
 */
public class MyWeChatOpenHelper extends SQLiteOpenHelper {
    /**
     *个人信息
     * Json(data)
     * {
     * "id": "q2016",
     * "name": "",
     * "email": "eminem@gmail.com",
     * "avatar": ""
     * }
     */
    public static final String CREATE_USER = "create table User("
            + "id text primary key,"
            + "account text,"
            + "password text,"
            + "name text,"
            + "avatar text,"
            + "moment_background text,"
            + "data text)";
    /**
     * 朋友圈
     */
    public static final String CREATE_MOMENT = "create table Moment("
            + "id text primary key ,"
            + "user_id text,"
            + "publish_date integer,"
            + "content text)";
    /**
     * 点赞
     */
    public static final String CREATE_LIKE = "create table Like("
            + "id text primary key ,"
            + "moment_id text,"
            + "user_id text)";
    /**
     * 评论
     */
    public static final String CREATE_COMMENT = "create table Comment("
            + "id text primary key ,"
            + "moment_id text,"
            + "initiator_id text,"
            + "recipient_id text,"
            + "content text)";
    /**
     * 联系人
     */
    public static  final String CREATE_CONTACTS = "create table Contacts("
            + "id text primary key ,"
            + "user_id text,"
            + "contact_id text,"
            + "name text,"
            + "avatar text)";
    public MyWeChatOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOMENT);
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_LIKE);
        db.execSQL(CREATE_COMMENT);
        db.execSQL(CREATE_CONTACTS);
        db.execSQL("INSERT INTO User(id,account,password,name,avatar,moment_background) VALUES(?,?,?,?,?,?)",
                new Object[]{0,"31401317@stu.zucc.edu.cn",
                        "123456",
                        "苏羽",
                        "http://wenwen.soso.com/p/20110814/20110814160542-1916500023.jpg",
                        "http://img0.imgtn.bdimg.com/it/u=2075776712,3107953298&fm=21&gp=0.jpg"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Moment");
        db.execSQL("drop table if exists Like");
        db.execSQL("drop table if exists Comment");
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Contacts");
        onCreate(db);
    }
}
