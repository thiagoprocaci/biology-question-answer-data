package com.biology.loader;

import com.biology.model.entity.Question;
import com.biology.model.list.QuestionList;
import com.biology.model.list.QuestionTag;
import com.biology.util.FileUtil;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;


public class QuestionListLoader implements Loader<Integer> {

    private QuestionList questionList = new QuestionList();
    private QuestionTag questionTag = new QuestionTag();

    private UserListLoader userListLoader;
    private TagListLoader tagListLoader;
    private AnswerListLoader answerListLoader;
    private QuestionCommentLoader questionCommentLoader;

    public QuestionListLoader(UserListLoader userListLoader, TagListLoader tagListLoader, AnswerListLoader answerListLoader, QuestionCommentLoader questionCommentLoader) {
        this.tagListLoader = tagListLoader;
        this.userListLoader = userListLoader;
        this.answerListLoader = answerListLoader;
        this.questionCommentLoader = questionCommentLoader;
    }

    public Integer loadTagFromJsonItem(JSONObject item) {
        int id = item.getInt("question_id");
        long lastActivityDate = item.getLong("last_activity_date");
        long creationDate = item.getLong("creation_date");
        int answerCount = item.getInt("answer_count");
        String title = item.getString("title");
        String body = item.getString("body");
        int score = item.getInt("score");
        int downVoteCount = item.getInt("down_vote_count");
        int viewCount = item.getInt("view_count");
        boolean answered = item.getBoolean("is_answered");
        int upVoteCount = item.getInt("up_vote_count");
        Integer userId = userListLoader.loadTagFromJsonItem(item);

        questionList.add(id, lastActivityDate, creationDate, answerCount,
                title, body, score, downVoteCount, viewCount, answered, upVoteCount, userId);

        List<Integer> idTagList = tagListLoader.loadTagFromJsonItem(item);
        questionTag.add(id, idTagList);
        answerListLoader.loadTagFromJsonItem(item);
        questionCommentLoader.loadTagFromJsonItem(item);

        return id;
    }


    public void printSql() {
        String createTable = questionList.genCreateTable();
        String insert = questionList.genInsert();
        FileUtil.writeString("question.sql", createTable + insert);

        createTable = questionTag.genCreateTable();
        insert = questionTag.genInsert();
        FileUtil.writeString("questionTag.sql", createTable + insert);
    }

    public String getSql() {
        String createTable = questionList.genCreateTable();
        String insert = questionList.genInsert();

        String createTable2 = questionTag.genCreateTable();
        String insert2 = questionTag.genInsert();
        return createTable + insert + createTable2 + insert2;
    }

    public void report() {
        Map<Integer, Question> map = questionList.getQuestionMap();
        System.out.println("Total question: " + map.size());

        Map<Integer, List<Integer>> map2 = questionTag.getQuestionTagMap();
        int count = 0;
        for(Integer i: map2.keySet()) {
            for(Integer j: map2.get(i)) {
                count++;
            }
        }
        System.out.println("Total question tag: " + count);

    }
}
