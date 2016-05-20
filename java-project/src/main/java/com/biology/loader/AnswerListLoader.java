package com.biology.loader;

import com.biology.model.entity.Answer;
import com.biology.model.list.AnswerList;
import com.biology.util.FileUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;


public class AnswerListLoader implements Loader<List> {

    private AnswerList answerList = new AnswerList();
    private UserListLoader userListLoader;
    private AnswerCommentLoader answerCommentLoader;

    public AnswerListLoader(UserListLoader userListLoader, AnswerCommentLoader answerCommentLoader) {
        this.userListLoader = userListLoader;
        this.answerCommentLoader = answerCommentLoader;
    }

    public List<Integer> loadTagFromJsonItem(JSONObject item) {
        if(!item.has("answers")) {
            return null;
        }
        JSONArray answers = item.getJSONArray("answers");
        Iterator it = answers.iterator();
        List<Integer> idList = new ArrayList<Integer>();
        while(it.hasNext()) {
            JSONObject answer = (JSONObject) it.next();
            int id = answer.getInt("answer_id");
            int score =  answer.getInt("score");
            boolean accepted = answer.getBoolean("is_accepted");
            int downVoteCount = answer.getInt("down_vote_count");
            long lastActivityDate = answer.getLong("last_activity_date");
            long creationDate = answer.getLong("creation_date");
            String body = answer.getString("body");
            int questionId = answer.getInt("question_id");
            int upVoteCount = answer.getInt("up_vote_count");
            Integer userId = userListLoader.loadTagFromJsonItem(answer);
            answerList.add(id, score, accepted, downVoteCount, lastActivityDate, creationDate,
                    body, questionId, upVoteCount, userId);
            answerCommentLoader.loadTagFromJsonItem(answer);
            idList.add(id);

        }
        if(idList.isEmpty()) {
            return null;
        }
        return idList;
    }

    public void printSql() {
        FileUtil.writeString("answer.sql", getSql());
    }

    public String getSql() {
        String createTable = answerList.genCreateTable();
        String insert = answerList.genInsert();
        return createTable + insert;
    }

    public void report() {
        Map<Integer, Answer> map = answerList.getAnswerMap();
        System.out.println("Total answer: " + map.size());
    }

    public Collection<Answer> getAnswerList() {
        return answerList.getAnswerList();
    }
}
