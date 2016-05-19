package com.biology.model.entity;

import org.apache.commons.lang.StringEscapeUtils;


public class Comment {
    int id;
    int score;
    int postId;
    boolean edited;
    long creationDate;
    String body;
    Integer userId;
    boolean questionComment;

    public Comment(int id, int score, int postId, boolean edited, long creationDate, String body, Integer userId, boolean questionComment) {
        this.id = id;
        this.score = score;
        this.postId = postId;
        this.edited = edited;
        this.creationDate = creationDate;
        this.body = StringEscapeUtils.escapeSql(body).replace("\\", "\\\\");
        this.userId = userId;
        this.questionComment = questionComment;
    }

    public int getEdited() {
        if (edited) {
            return 1;
        }
        return 0;
    }

    public boolean isQuestionComment() {
        return questionComment;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getPostId() {
        return postId;
    }

    public boolean isEdited() {
        return edited;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getBody() {
        return body;
    }

    public Integer getUserId() {
        return userId;
    }
}
