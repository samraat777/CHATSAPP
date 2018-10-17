package com.example.sam.chat;

public class RetrieveMessage {

    public String message;

    public RetrieveMessage()
    {
        //Empty Constructor
    }

    public RetrieveMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
