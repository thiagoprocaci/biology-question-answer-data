package com.biology.model.list;


import com.biology.model.entity.Question;

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
        for (Question q: questionMap.values()) {
            String sql = String.format("INSERT INTO `question` (`id`, " +
                    "`lastActivityDate`, `creationDate`, `answerCount`, `title`, `body`, `score`, `downVoteCount`, `viewCount`, " +
                    "`answered`, `upVoteCount`, `userId`) " +
                    "VALUES (%d, FROM_UNIXTIME(%d), FROM_UNIXTIME(%d), %d, '%s', '%s', %d, %d, %d, %d, %d, %d); \n",
                    q.getId(), q.getLastActivityDate(), q.getCreationDate(), q.getAnswerCount(), q.getTitle(), q.getBody(),
                    q.getScore(), q.getDownVoteCount(), q.getViewCount(), q.getAnswered(), q.getUpVoteCount(), q.getUserId());
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


}
