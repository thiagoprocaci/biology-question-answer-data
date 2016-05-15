package com.biology.model;


import org.apache.commons.lang.StringEscapeUtils;

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
        } /**else {
            System.out.println(id + " " + reputation + " " + name);
            User u = userMap.get(id);
            System.out.println(u.id + " " + u.reputation + " " + u.name);
            System.out.println("----------------------");
        }**/
    }

    public String genInsert() {
        StringBuilder builder = new StringBuilder();
        for (User u: userMap.values()) {
            String sql = String.format("INSERT INTO `user` (`id`, `reputation`, `name`) VALUES (%d, %d, '%s'); \n", u.id, u.reputation, u.name);
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

    public class User {
        int id;
        int reputation;
        String name;

        public User(int id, int reputation, String name) {
            this.id = id;
            this.reputation = reputation;
            this.name = StringEscapeUtils.escapeSql(name);
        }
    }
}
