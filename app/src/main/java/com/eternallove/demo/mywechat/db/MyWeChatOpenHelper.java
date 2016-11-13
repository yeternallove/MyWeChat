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
     * 朋友圈
     */
    public static final String CREATE_MOMENT = "create table Moment("
            + "id integer primary key autoincrement,"
            + "user_id integer,"
            + "Content text,"
            + "RE_id integer)";
//            + "FOREIGN KEY(RE_id) REFERENCES Reference(id)";
    /**
     * 引用
     */
    public static final String CREATE_REFERENCE = "create table Reference("
            + "id integer primary key autoincrement,"
            + "moment_id integer,"
            + "is_picture blob,"
            + "link text)";
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
    public static final String CREATE_COMMENTS = "create table Comments("
            + "id integer primary key autoincrement,"
            + "moment_id integer,"
            + "initiator_id integer,"
            + "recipient_id integer,"
            + "reply text)";
    public MyWeChatOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOMENT);
        db.execSQL(CREATE_REFERENCE);
        db.execSQL(CREATE_LIKE);
        db.execSQL(CREATE_COMMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists Moment");
        db.execSQL("drop table if exists Reference");
        db.execSQL("drop table if exists Like");
        db.execSQL("drop table if exists Comments");
        onCreate(db);
    }
}
