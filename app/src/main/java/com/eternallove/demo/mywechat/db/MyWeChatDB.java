package com.eternallove.demo.mywechat.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.eternallove.demo.mywechat.R;
import com.eternallove.demo.mywechat.modle.CommentBean;
import com.eternallove.demo.mywechat.modle.ContactsBean;
import com.eternallove.demo.mywechat.modle.LikeBean;
import com.eternallove.demo.mywechat.modle.MomentBean;
import com.eternallove.demo.mywechat.modle.HeadBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Context mcontext;
    private MyWeChatDB(Context context){
        MyWeChatOpenHelper dbHelper = new MyWeChatOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
        this.mcontext = context;
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

    public void saveContacts(String id,String user_id,String contact_id,String name,String avatar){
        Cursor c = db.rawQuery("select * from Contacts where id = ?",new String[]{id});
        if(c.moveToFirst()) return;
        db.execSQL("INSERT INTO Contacts(id,user_id,contact_id,name,avatar) VALUES(?,?,?,?,?)",new Object[]{id,user_id,contact_id,name,avatar});
    }

    public void saveUser(String id,String account,String password,String name,String avatar,String moment_background,String data){
        Cursor c = db.rawQuery("select * from User where id = ?",new String[]{id});
        if(c.moveToFirst()) return;
        db.execSQL("INSERT INTO User(id,account,password,name,avatar,moment_background,data) VALUES(?,?,?,?,?,?,?)",new Object[]{id,account,password,name,avatar,moment_background,data});
    };

    public void saveMoment(String id,String user_id,long publish_date,String content){
        Cursor c = db.rawQuery("select * from Moment where id = ?",new String[]{id});
        if(c.moveToFirst()) return;
        db.execSQL("INSERT INTO Moment(id,user_id,publish_date,content) VALUES(?,?,?,?)",new Object[]{id,user_id,publish_date,content});
    }

    public void saveLike(String id,String moment_id,String  user_id){
        Cursor c = db.rawQuery("select * from Like where id = ?",new String[]{id});
        if(c.moveToFirst()) return;
        db.execSQL("INSERT INTO Like(id,moment_id,user_id) VALUES(?,?,?)",new Object[]{id,moment_id,user_id});
    }

    public void saveComment(String id,String moment_id,String initiator_id,String recipient_id,String content){
        Cursor c = db.rawQuery("select * from Comment where id = ?",new String[]{id});
        if(c.moveToFirst()) return;
        db.execSQL("INSERT INTO Comment(id,moment_id,initiator_id,recipient_id,content) VALUES(?,?,?,?,?)",new Object[]{id,moment_id,initiator_id,recipient_id,content});
    }

    public ArrayList<ContactsBean> loadContacts(String id){
        ArrayList<ContactsBean> contactlist = new ArrayList<>();
        ContactsBean contact;
        Cursor c = db.rawQuery("select id,contact_id,name,avatar from Content where user_id = ?",new String[]{id});
        while(c.moveToNext())
        {
            contact = new ContactsBean();
            contact.setId(c.getString(0));
            contact.setContact_id(c.getString(1));
            contact.setName(c.getString(2));
            contact.setAvatar(c.getString(3));
            contactlist.add(contact);
        }
        return contactlist;
    }

    /**
     * public static final String CREATE_USER = "create table User("
     + "id text primary key,"
     + "account text,"
     + "password text,"
     + "name text,"
     + "avatar text,"
     + "moment_background text,"
     + "data text)";
     * @param id
     * @return
     */
    public HeadBean loadHead(String id){
        HeadBean headBean = new HeadBean();
        Cursor c = db.rawQuery("select name,avatar,moment_background from User where id = ?",new String[]{id});
        if(c.moveToFirst()){
            headBean.setHeadName(c.getString(0));
            headBean.setHeadAvatar(c.getString(1));
            headBean.setHeadBackground(c.getString(2));
        }
        return headBean;
    }

    public ArrayList<LikeBean> loadLike(String moment_id){
        ArrayList<LikeBean> likeData =new ArrayList<>();
        String sql = "select c.name" +
                " from Like l,Contacts c" +
                " where l.user_id = c.contact_id and l.moment_id = ?";
        LikeBean like ;
        Cursor c = db.rawQuery(sql,new String[]{moment_id});
        while(c.moveToNext()){
            like = new LikeBean();
            like.setUsername(c.getString(0));
            likeData.add(like);
        }
        return likeData;
    }
    public ArrayList<CommentBean> loadComment(String moment_id){
        ArrayList<CommentBean> commentData = new ArrayList<>();
        String sql = "select c1.name,c.name,c.content,c.initiator_id,c.recipient_id" +
                " from ( select moment_id,initiator_id,recipient_id,name,content" +
                " from Comment LEFT OUTER JOIN Contacts ON (Comment.recipient_id = Contacts.contact_id) ) AS c,Contacts c1 " +
                " where c.initiator_id = c1.contact_id and c.moment_id = ?";
        CommentBean comment;
        String initiator_id;
        String recipient_id;
        Cursor c = db.rawQuery(sql,new String[]{moment_id});
        while(c.moveToNext()){
            comment = new CommentBean();
            initiator_id = c.getString(3);
            recipient_id = c.getString(4);
            comment.setInitiator_name("0".equals(initiator_id) ? mcontext.getString(R.string.comment_me) :c.getString(0));
            comment.setRecipient_name("0".equals(recipient_id) ? mcontext.getString(R.string.comment_me) :c.getString(1));
            comment.setContent(c.getString(2));
            commentData.add(comment);
        }
        return commentData;
    }
    public ArrayList<MomentBean> loadMoment(String id){
        ArrayList<MomentBean> momentlist = new ArrayList<>();
        ArrayList<LikeBean> likeData;
        ArrayList<CommentBean> commentData;
        String sql = "select Moment.id,Contacts.avatar,Contacts.name,Moment.publish_date,Moment.content" +
                " from Moment,Contacts" +
                " where Moment.user_id = Contacts.contact_id and Contacts.user_id = ?" +
                " order by Moment.publish_date desc";
        String moment_id;
        String avatar;
        String name;
        String content;
        Date publishDate;
        Cursor c1 = db.rawQuery(sql,new String[]{id});
        while(c1.moveToNext())
        {
            moment_id = c1.getString(0);
            avatar = c1.getString(1);
            name = c1.getString(2);
            publishDate = new Date(c1.getLong(3));
            content = c1.getString(4);
            likeData = loadLike(moment_id);
            commentData = loadComment(moment_id);
            momentlist.add(new MomentBean(avatar,name,content,likeData,commentData,publishDate));
        }
        return momentlist;
    }
}
