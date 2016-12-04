package com.eternallove.demo.mywechat.modle;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/20
 */
public class ContactsBean {

    private String id;
    private String contact_id;
    private String name;
    private String avatar;

    public void setId(String id) {
        this.id = id;
    }

    public String getContact_id() {
        return contact_id;
    }

    public void setContact_id(String contact_id) {
        this.contact_id = contact_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
