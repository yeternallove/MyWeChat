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
            + "id integer primary key autoincrement,"
            + "account text,"
            + "password text,"
            + "data text,"
            + "contacts text)";
    /**
     * 朋友圈
     */
    public static final String CREATE_MOMENT = "create table Moment("
            + "id integer primary key autoincrement,"
            + "user_id integer,"
            + "publish_date integer,"
            + "content text)";
    /**
     * 点赞
     */
    public static final String CREATE_LIKE = "create table Like("
            + "id integer primary key autoincrement,"
            + "moment_id integer,"
            + "initiator_id integer,"
            + "recipient_id integer)";
    /**
     * 评论
     */
    public static final String CREATE_COMMENT = "create table Comment("
            + "id integer primary key autoincrement,"
            + "moment_id integer,"
            + "initiator_id integer,"
            + "recipient_id integer,"
            + "content text)";
    /**
     * 联系人
     */
    public static  final String CREATE_CONTACTS = "create table Content("
            + "id text primary key,"
            + "user_id integer,"
            + "name text,"
            + "email text,"
            + "address text,"
            + "gender text,"
            + "mobile text,"
            + "home text,"
            + "office text)";
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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Moment");
        db.execSQL("drop table if exists Like");
        db.execSQL("drop table if exists Comment");
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Content");
        onCreate(db);
    }
}
