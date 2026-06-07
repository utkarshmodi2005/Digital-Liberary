package com.example.UM.Digital.Liberary.enums;

public enum SubscriptionType {
    NOTSUBSCRIBED("Not Subscribed"),
    PLUS("Plus"),
    PRO("Pro"),
    PREMIUM("Premium");

    private String name;

    SubscriptionType(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
}
