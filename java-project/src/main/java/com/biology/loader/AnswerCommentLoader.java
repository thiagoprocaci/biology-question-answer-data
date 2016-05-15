package com.biology.loader;


public class AnswerCommentLoader extends CommentListLoader {

    public AnswerCommentLoader(UserListLoader userListLoader) {
        super(userListLoader);
    }

    public boolean isQuestionComment() {
        return false;
    }
}
