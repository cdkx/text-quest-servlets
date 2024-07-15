package com.eremin.textquestservlets.service;

import com.eremin.textquestservlets.model.Answer;
import com.eremin.textquestservlets.model.Question;
import com.eremin.textquestservlets.model.Root;
import jakarta.servlet.ServletContext;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

import static com.eremin.textquestservlets.consts.Const.*;


@Slf4j
public class QuestionService {
    private final Root data;

    public QuestionService(ServletContext context) {
        DataService dataService = (DataService) context.getAttribute(DATA_SERVICE);
        this.data = dataService.getData();
    }

    public List<Answer> getAnswersByQuestionId(Integer questionId) {

        Question question = data.getQuestions().stream()
                .filter(q -> (q.getId() == questionId))
                .findFirst()
                .orElse(null);

        if (question != null) {
            List<Integer> answersIds = question.getAnswers();
            return data.getAnswers().stream()
                    .filter(answer -> answersIds.contains(answer.getId()))
                    .collect(Collectors.toList());
        } else {
            log.error("question is null. Cant find Answers by QuestionId");
            throw new RuntimeException("question is null");
        }
    }

    public Question findQuestionByAnswerId(Integer answerId) {

        Answer answer = data.getAnswers().stream()
                .filter(el -> el.getId() == answerId)
                .findFirst()
                .orElse(null);

        if (answer != null) {
            return data.getQuestions()
                    .stream()
                    .filter(question -> question.getId() == answer.getNextQuestion())
                    .findFirst()
                    .orElse(null);
        } else {
            log.error("answer is null. Cant find question by answerId");
            throw new RuntimeException("answer is null");
        }
    }
}
