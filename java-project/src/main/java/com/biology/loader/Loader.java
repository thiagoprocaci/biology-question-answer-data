package com.biology.loader;


import org.json.JSONObject;

public interface Loader<T> {

    T loadTagFromJsonItem(JSONObject item);

    void printSql();

    String getSql();
}
