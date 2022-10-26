package com.example.firebaseapp;

public class Message {
    private String msgId;
    private String senderId;
    private String message;

    public Message() {
    }

    public Message(String msgId, String senderId, String message) {
        this.msgId = msgId;
        this.senderId = senderId;
        this.message = message;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }
}
