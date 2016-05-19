package com.biology.loader;

import com.biology.model.list.TagList;
import com.biology.util.FileUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TagListLoader implements Loader<List> {

    private TagList tagList;

    public TagListLoader() {
        tagList = new TagList();
    }

    public List<Integer> loadTagFromJsonItem(JSONObject item) {
        JSONArray tags = item.getJSONArray("tags");
        Iterator it = tags.iterator();
        List<Integer> idList = new ArrayList<Integer>();
        while(it.hasNext()) {
            String tag = (String) it.next();
            Integer id = tagList.add(tag);
            if(id != null) {
                idList.add(id);
            }
        }
        if(idList.isEmpty()) {
            return null;
        }
        return idList;
    }

    public void printSql() {
        FileUtil.writeString("tag.sql", getSql());
    }

    public String getSql() {
        String createTable = tagList.genCreateTable();
        String insert = tagList.genInsert();
        return createTable + insert;
    }

    public void report() {
        Map<String, Integer> map = tagList.getTagMap();
        System.out.println("Total tag: " + map.size());
    }

}
