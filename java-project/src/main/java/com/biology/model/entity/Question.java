package com.biology.model.entity;

import org.apache.commons.lang.StringEscapeUtils;


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

    public int getAnswered() {
        if (answered) {
            return 1;
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public long getLastActivityDate() {
        return lastActivityDate;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public int getScore() {
        return score;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public boolean isAnswered() {
        return answered;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public Integer getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", lastActivityDate=" + lastActivityDate +
                ", creationDate=" + creationDate +
                ", answerCount=" + answerCount +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", score=" + score +
                ", downVoteCount=" + downVoteCount +
                ", viewCount=" + viewCount +
                ", answered=" + answered +
                ", upVoteCount=" + upVoteCount +
                ", userId=" + userId +
                '}';
    }
}
