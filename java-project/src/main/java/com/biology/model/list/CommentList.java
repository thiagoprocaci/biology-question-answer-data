package com.biology.model.list;


import com.biology.model.entity.Comment;

import java.util.HashMap;
import java.util.Map;

public class CommentList implements Insertable {

    private Map<Integer, Comment> commentMap = new HashMap<Integer, Comment>();

    public Map<Integer, Comment> getCommentMap() {
        return commentMap;
    }

    public void add(int id, int score, int postId, boolean edited, long creationDate, String body, Integer userId, boolean questionComment) {
        if (!commentMap.containsKey(id)) {
            commentMap.put(id, new Comment(id, score, postId, edited, creationDate, body, userId, questionComment));
        }
    }

    public String genInsert() {
        StringBuilder builder = new StringBuilder();
        for (Comment q: commentMap.values()) {
            String sql;
            if(q.isQuestionComment()) {
                sql = String.format("INSERT INTO `question_comment` (`id`, `score`,  `questionId`, `edited`," +
                                "`creationDate`, `body`, `userId` ) " +
                                "VALUES (%d, %d, %d, %d, FROM_UNIXTIME(%d), '%s', %d); \n",
                        q.getId(), q.getScore(), q.getPostId(), q.getEdited(), q.getCreationDate(), q.getBody(), q.getUserId() );
            } else {
                sql = String.format("INSERT INTO `answer_comment` (`id`, `score`,  `answerId`, `edited`," +
                                "`creationDate`, `body`, `userId` ) " +
                                "VALUES (%d, %d, %d, %d, FROM_UNIXTIME(%d), '%s', %d); \n",
                        q.getId(), q.getScore(), q.getPostId(), q.getEdited(), q.getCreationDate(), q.getBody(), q.getUserId() );
            }
            builder.append(sql);
        }
        return builder.toString();
    }

    public String genCreateTable() {
        String sql = "CREATE TABLE IF NOT EXISTS `question_comment` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `score` int(11) NOT NULL ,\n" +
                "  `questionId` bigint(20) NOT NULL,\n" +
                "  `edited` int(1) NOT NULL ,\n" +
                "  `creationDate` timestamp NOT NULL ,\n" +
                "  `body` text NOT NULL,\n" +
                "  `userId` bigint(20)\n" +
                "); \n" +
                "\n";
        String sql2 = "CREATE TABLE IF NOT EXISTS `answer_comment` (\n" +
                "  `id` bigint(20) NOT NULL,\n" +
                "  `score` int(11) NOT NULL ,\n" +
                "  `answerId` bigint(20) NOT NULL,\n" +
                "  `edited` int(1) NOT NULL ,\n" +
                "  `creationDate` timestamp NOT NULL ,\n" +
                "  `body` text NOT NULL,\n" +
                "  `userId` bigint(20)\n" +
                "); \n" +
                "\n";
        return sql + sql2;
    }


}
