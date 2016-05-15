package com.biology.loader;


public class QuestionCommentLoader extends CommentListLoader {

    public QuestionCommentLoader(UserListLoader userListLoader) {
        super(userListLoader);
    }

    public boolean isQuestionComment() {
        return true;
    }
}
