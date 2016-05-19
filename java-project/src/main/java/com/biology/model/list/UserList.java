package com.biology.model.list;


import com.biology.model.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserList implements Insertable {


    private Map<Integer, User> userMap = new HashMap<Integer, User>();

    public Map<Integer, User> getUserMap() {
        return userMap;
    }

    public void add(int id, int reputation, String name) {
        if(!userMap.containsKey(id)) {
            userMap.put(id, new User(id, reputation, name));
        }
    }

    public String genInsert() {
        StringBuilder builder = new StringBuilder();
        for (User u: userMap.values()) {
            String sql = String.format("INSERT INTO `user` (`id`, `reputation`, `name`) VALUES (%d, %d, '%s'); \n", u.getId(), u.getReputation(), u.getName());
            builder.append(sql);
        }
        return builder.toString();
    }

    public String genCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `user` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `reputation` int(11) DEFAULT NULL,\n" +
                "  `name` varchar(200) CHARACTER SET utf8 DEFAULT NULL\n" +
                "); \n" +
                "\n";
        return sql;
    }

}
