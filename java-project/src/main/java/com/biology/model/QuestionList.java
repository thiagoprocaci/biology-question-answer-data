package com.biology.model;


import org.apache.commons.lang.StringEscapeUtils;

import java.util.HashMap;
import java.util.Map;

public class QuestionList implements Insertable {

    private Map<Integer, Question> questionMap = new HashMap<Integer, Question>();

    public Map<Integer, Question> getQuestionMap() {
        return questionMap;
    }

    public void add(int id, long lastActivityDate, long creationDate, int answerCount,
                    String title, String body, int score, int downVoteCount,
                    int viewCount, boolean answered, int upVoteCount, Integer userId) {
        if (!questionMap.containsKey(id)) {
            questionMap.put(id, new Question(id, lastActivityDate, creationDate, answerCount,
                    title, body, score, downVoteCount, viewCount, answered, upVoteCount, userId));
        }
    }
    public String genInsert() {

        StringBuilder builder = new StringBuilder();
        for (QuestionList.Question q: questionMap.values()) {
            String sql = String.format("INSERT INTO `question` (`id`, " +
                    "`lastActivityDate`, `creationDate`, `answerCount`, `title`, `body`, `score`, `downVoteCount`, `viewCount`, " +
                    "`answered`, `upVoteCount`, `userId`) " +
                    "VALUES (%d, FROM_UNIXTIME(%d), FROM_UNIXTIME(%d), %d, '%s', '%s', %d, %d, %d, %d, %d, %d); \n",
                    q.id, q.lastActivityDate, q.creationDate, q.answerCount, q.title, q.body,
                    q.score, q.downVoteCount, q.viewCount, q.getAnswered(), q.upVoteCount, q.userId);
            builder.append(sql);
        }
        return builder.toString();


    }

    public String genCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `question` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `lastActivityDate` timestamp NOT NULL ,\n" +
                "  `creationDate` timestamp NOT NULL ,\n" +
                "  `answerCount` int(11) NOT NULL ,\n" +
                "  `title` text NOT NULL,\n" +
                "  `body` text NOT NULL,\n" +
                "  `score` int(11) NOT NULL ,\n" +
                "  `downVoteCount` int(11) NOT NULL ,\n" +
                "  `viewCount` int(11) NOT NULL ,\n" +
                "  `answered` int(1) NOT NULL ,\n" +
                "  `upVoteCount` int(11) NOT NULL ,\n" +
                "  `userId` bigint(20)\n" +
                "); \n" +
                "\n";
        return sql;
    }


    public class Question {
        int id;
        long lastActivityDate;
        long creationDate;
        int answerCount;
        String title;
        String body;
        int score;
        int downVoteCount;
        int viewCount;
        boolean answered;
        int upVoteCount;
        Integer userId;

        public Question(int id, long lastActivityDate, long creationDate, int answerCount,
                        String title, String body, int score, int downVoteCount, int viewCount, boolean answered, int upVoteCount, Integer userId) {
            this.id = id;
            this.lastActivityDate = lastActivityDate;
            this.creationDate = creationDate;
            this.answerCount = answerCount;
            this.title = StringEscapeUtils.escapeSql(title);
            this.body = StringEscapeUtils.escapeSql(body);
            this.score = score;
            this.downVoteCount = downVoteCount;
            this.viewCount = viewCount;
            this.answered = answered;
            this.upVoteCount = upVoteCount;
            this.userId = userId;
        }

        int getAnswered() {
            if(answered) {
                return 1;
            }
            return 0;
        }
    }
}
