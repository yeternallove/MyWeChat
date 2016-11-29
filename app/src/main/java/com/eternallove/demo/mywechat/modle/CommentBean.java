package com.eternallove.demo.mywechat.modle;

/**
 * @description:
 * @author: eternallove
 * @date: 2016/11/28
 */
public class CommentBean {
    private String initiator_name;
    private String recipient_name;
    private String content;

    public CommentBean(String initiator_name, String recipient_name, String content) {
        this.initiator_name = initiator_name;
        this.recipient_name = recipient_name;
        this.content = content;
    }

    public String getInitiator_name() {
        return initiator_name;
    }

    public void setInitiator_name(String initiator_name) {
        this.initiator_name = initiator_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecipient_name() {
        return recipient_name;
    }

    public void setRecipient_name(String recipient_name) {
        this.recipient_name = recipient_name;
    }
}
