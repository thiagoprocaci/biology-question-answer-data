ALTER TABLE answer ADD PRIMARY KEY(id);
ALTER TABLE answer_comment ADD PRIMARY KEY(id);
ALTER TABLE question ADD PRIMARY KEY(id);
ALTER TABLE question_comment ADD PRIMARY KEY(id);
ALTER TABLE question_tag ADD PRIMARY KEY(questionId, tagId);
ALTER TABLE tag ADD PRIMARY KEY(id);
ALTER TABLE user ADD PRIMARY KEY(id);


ALTER TABLE answer ADD CONSTRAINT
`fk_answer_user` FOREIGN KEY ( `userId` ) REFERENCES `user` ( `id` ) ;
ALTER TABLE answer ADD CONSTRAINT
`fk_answer_question` FOREIGN KEY ( `questionId` ) REFERENCES `question` ( `id` ) ;


ALTER TABLE answer_comment ADD CONSTRAINT
`fk_answer_comment_answer` FOREIGN KEY ( `answerId` ) REFERENCES `answer` ( `id` ) ;
ALTER TABLE answer_comment ADD CONSTRAINT
`fk_answer_comment_user` FOREIGN KEY ( `userId` ) REFERENCES `user` ( `id` ) ;

ALTER TABLE question ADD CONSTRAINT
`fk_question_user` FOREIGN KEY ( `userId` ) REFERENCES `user` ( `id` ) ;


ALTER TABLE question_comment ADD CONSTRAINT
`fk_question_comment_user` FOREIGN KEY ( `userId` ) REFERENCES `user` ( `id` ) ;

ALTER TABLE question_comment ADD CONSTRAINT
`fk_question_comment_question` FOREIGN KEY ( `questionId` ) REFERENCES `question` ( `id` ) ;
