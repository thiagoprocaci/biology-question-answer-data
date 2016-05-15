package com.biology.loader;


import com.biology.model.CommentList;
import com.biology.util.FileUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CommentListLoader implements Loader<List>  {

    private CommentList commentList = new CommentList();
    private UserListLoader userListLoader;

    public CommentListLoader(UserListLoader userListLoader) {
        this.userListLoader = userListLoader;
    }

    public List<Integer> loadTagFromJsonItem(JSONObject item) {
        if(!item.has("comments")) {
            return null;
        }
        JSONArray answers = item.getJSONArray("comments");
        Iterator it = answers.iterator();
        List<Integer> idList = new ArrayList<Integer>();
        while(it.hasNext()) {
            JSONObject comment = (JSONObject) it.next();
            int id = comment.getInt("comment_id");
            int score = comment.getInt("score");
            int postId = comment.getInt("post_id");
            boolean edited = comment.getBoolean("edited");
            long creationDate = comment.getLong("creation_date");
            String body = comment.getString("body");
            Integer userId = userListLoader.loadTagFromJsonItem(comment);
            boolean questionComment = isQuestionComment();
            commentList.add(id, score, postId, edited, creationDate, body, userId, questionComment);
            idList.add(id);
        }
        if(idList.isEmpty()) {
            return null;
        }
        return idList;
    }

    public void printSql() {
        if(isQuestionComment()) {
            FileUtil.writeString("questionComment.sql", getSql());
        } else {
            FileUtil.writeString("answerComment.sql", getSql());
        }
    }

    public String getSql() {
        String createTable = commentList.genCreateTable();
        String insert = commentList.genInsert();
        return createTable + insert;
    }

    public void report() {
        Map<Integer, CommentList.Comment> map = commentList.getCommentMap();
        if(isQuestionComment()) {
            System.out.println("Total question comment: " + map.size());
        } else {
            System.out.println("Total answer comment: " + map.size());
        }
    }

    public abstract boolean isQuestionComment();
}
