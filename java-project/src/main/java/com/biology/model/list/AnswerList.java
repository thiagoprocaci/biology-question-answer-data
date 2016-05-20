package com.biology.model.list;


import com.biology.model.entity.Answer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AnswerList implements Insertable {
    private Map<Integer, Answer> answerMap = new HashMap<Integer, Answer>();

    public Map<Integer, Answer> getAnswerMap() {
        return answerMap;
    }

    public void add(int id, int score, boolean accepted, int downVoteCount,
                    long lastActivityDate, long creationDate, String body, int questionId, int upVoteCount, Integer userId) {
        if(!answerMap.containsKey(id)) {
            answerMap.put(id, new Answer(id, score, accepted, downVoteCount, lastActivityDate, creationDate,
                    body, questionId, upVoteCount, userId));
        }
    }

    public String genInsert() {
        StringBuilder builder = new StringBuilder();
        for (Answer q: answerMap.values()) {
            String sql = String.format("INSERT INTO `answer` (`id`, `score`,  `accepted`, `downVoteCount`," +
                            "`lastActivityDate`, `creationDate`, `body`, `questionId`, `upVoteCount`, `userId` ) " +
                            "VALUES (%d, %d, %d, %d, FROM_UNIXTIME(%d), FROM_UNIXTIME(%d), '%s', %d, %d, %d); \n",
                    q.getId(), q.getScore(), q.getAccepted(), q.getDownVoteCount(),
                    q.getLastActivityDate(), q.getCreationDate(), q.getBody(), q.getQuestionId(), q.getUpVoteCount(), q.getUserId() );
            builder.append(sql);
        }
        return builder.toString();
    }

    public String genCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `answer` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `score` int(11) NOT NULL ,\n" +
                "  `accepted` int(1) NOT NULL ,\n" +
                "  `downVoteCount` int(11) NOT NULL ,\n" +
                "  `lastActivityDate` timestamp NOT NULL ,\n" +
                "  `creationDate` timestamp NOT NULL ,\n" +
                "  `body` text NOT NULL,\n" +
                "  `questionId` bigint(20),\n" +
                "  `upVoteCount` int(11) NOT NULL, \n" +
                "  `userId` bigint(20)\n" +
                "); \n" +
                "\n";
        return sql;
    }

    public Collection<Answer> getAnswerList() {
        return answerMap.values();
    }

}
