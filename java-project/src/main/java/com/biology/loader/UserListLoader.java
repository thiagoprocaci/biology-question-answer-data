package com.biology.loader;


import com.biology.model.entity.User;
import com.biology.model.list.UserList;
import com.biology.util.FileUtil;

import org.json.JSONObject;

import java.util.Map;


public class UserListLoader implements Loader {

    private UserList userList = new UserList();

    public Integer loadTagFromJsonItem(JSONObject item) {
        JSONObject owner = item.getJSONObject("owner");
        if(!owner.has("user_id"))
            return null;
        int reputation = owner.getInt("reputation");
        int id = owner.getInt("user_id");
        String name = owner.getString("display_name");
        userList.add(id, reputation, name);
        return id;
    }

    public void printSql() {
        FileUtil.writeString("user.sql", getSql());
    }

    public String getSql() {
        String createTable = userList.genCreateTable();
        String insert = userList.genInsert();
        return createTable + insert;
    }

    public void report() {
        Map<Integer, User> map = userList.getUserMap();
        System.out.println("Total user: " + map.size());
    }
}
