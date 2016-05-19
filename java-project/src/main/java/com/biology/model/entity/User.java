package com.biology.model.entity;

import org.apache.commons.lang.StringEscapeUtils;


public class User {
    int id;
    int reputation;
    String name;

    public User(int id, int reputation, String name) {
        this.id = id;
        this.reputation = reputation;
        this.name = StringEscapeUtils.escapeSql(name);
    }

    public int getId() {
        return id;
    }

    public int getReputation() {
        return reputation;
    }

    public String getName() {
        return name;
    }
}
