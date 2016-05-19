package com.biology.model.entity;

import org.apache.commons.lang.StringEscapeUtils;


public class Answer {
    int id;
    int score;
    boolean accepted;
    int downVoteCount;
    long lastActivityDate;
    long creationDate;
    String body;
    int questionId;
    int upVoteCount;
    Integer userId;

    public Answer(int id, int score, boolean accepted, int downVoteCount,
                  long lastActivityDate, long creationDate, String body, int questionId, int upVoteCount, Integer userId) {
        this.id = id;
        this.score = score;
        this.accepted = accepted;
        this.downVoteCount = downVoteCount;
        this.lastActivityDate = lastActivityDate;
        this.creationDate = creationDate;
        this.body = StringEscapeUtils.escapeSql(body);
        this.questionId = questionId;
        this.upVoteCount = upVoteCount;
        this.userId = userId;
    }

    public int getAccepted() {
        if (accepted) {
            return 1;
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public String getBody() {
        return body;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public Integer getUserId() {
        return userId;
    }
}
