package com.biology.model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TagList implements Insertable {


    private Map<String, Integer> tagMap = new HashMap<String, Integer>();
    private int id = 1;

    public Map<String, Integer> getTagMap() {
        return tagMap;
    }

    public Integer add(String tag) {
        if(!tagMap.containsKey(tag)) {
            tagMap.put(tag, id);
            id++;
        }
        return tagMap.get(tag);
    }

    public String genInsert() {
        StringBuilder builder = new StringBuilder();
        for (String tag: tagMap.keySet()) {
            String sql = String.format("INSERT INTO `tag` (`id`, `name`) VALUES (%d, '%s');", tagMap.get(tag), tag);
            builder.append(sql);
            builder.append("\n");
        }
        return builder.toString();
    }

    public String genCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `tag` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `name` varchar(50) NOT NULL\n" +
                ");\n" +
                "\n";
        return sql;
    }
}
