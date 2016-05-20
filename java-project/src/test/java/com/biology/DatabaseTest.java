package com.biology;


import com.biology.model.entity.Answer;
import com.biology.model.entity.Question;
import com.biology.repository.AppRepository;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;


public class DatabaseTest {


    private AppRepository appRepository;
    private SqlGen sqlGen;

    @Before
    public void before() throws IOException, URISyntaxException {
        sqlGen = new SqlGen();
        appRepository = new AppRepository();
    }

    @Test
    public void testAnswer() {
        Collection<Answer> answerList = sqlGen.getAnswerListLoader().getAnswerList();
        assertFalse(answerList.isEmpty());
        for(Answer answer: answerList) {
            Answer a = appRepository.getAnswerById(answer.getId());
            assertEquals(a.getId(), answer.getId());
            assertEquals(a.getUpVoteCount(), answer.getUpVoteCount());
            assertEquals(a.getDownVoteCount(), answer.getDownVoteCount());
            assertEquals(a.getAccepted(), answer.getAccepted());
            assertEquals(a.getQuestionId(), answer.getQuestionId());
            assertEquals(a.getScore(), answer.getScore());
            assertEquals(a.getUserId(), answer.getUserId());
            assertEquals((a.getCreationDate() + "").substring(0, 5), (answer.getCreationDate() + "").substring(0, 5));
            assertEquals((a.getLastActivityDate() + "").substring(0, 5), (answer.getLastActivityDate() + "").substring(0, 5));
            //assertEquals(a.getBody(), answer.getBody());
        }
    }

    @Test
    public void testQuestion() {
        Collection<Question> questionList = sqlGen.getQuestionListLoader().getQuestionList();
        assertFalse(questionList.isEmpty());
        for(Question question: questionList) {
            Question q = appRepository.getQuestionById(question.getId());
            assertEquals(question.getId(), q.getId());
            assertEquals(question.getAnswerCount(), q.getAnswerCount());
            assertEquals(question.getAnswered(), q.getAnswered());

            assertEquals((q.getCreationDate() + "").substring(0, 5), (question.getCreationDate() + "").substring(0, 5));
            assertEquals(question.getDownVoteCount(), q.getDownVoteCount());
            assertEquals(question.getUpVoteCount(), q.getUpVoteCount());
            assertEquals(question.getScore(), q.getScore());
            assertEquals(question.getUserId(), q.getUserId());
            assertEquals((q.getLastActivityDate() + "").substring(0, 5), (question.getLastActivityDate() + "").substring(0, 5));

            assertEquals(question.getViewCount(), q.getViewCount());

            // assertEquals(question.getTitle().substring(0, 5), q.getTitle().substring(0, 5));
            // assertTrue(question.getBody().startsWith(q.getBody()));
        }
    }

}
