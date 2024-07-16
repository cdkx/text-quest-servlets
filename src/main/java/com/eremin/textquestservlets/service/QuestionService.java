package com.eremin.textquestservlets.service;

import com.eremin.textquestservlets.model.Answer;
import com.eremin.textquestservlets.model.Question;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
public class QuestionService {
    private final DataService dataService;

    public List<Answer> getAnswersByQuestionId(Integer questionId) {

        Question question = dataService.getData().getQuestions().stream()
                .filter(q -> (q.getId() == questionId))
                .findFirst()
                .orElseThrow();

        List<Integer> answersIds = question.getAnswers();
        return dataService.getData().getAnswers().stream()
                .filter(answer -> answersIds.contains(answer.getId()))
                .collect(Collectors.toList());
    }

    public Question getQuestionByAnswerId(Integer answerId) {

        Answer answer = dataService.getData().getAnswers().stream()
                .filter(el -> el.getId() == answerId)
                .findFirst()
                .orElseThrow();

        return dataService.getData().getQuestions()
                .stream()
                .filter(question -> question.getId() == answer.getNextQuestion())
                .findFirst()
                .orElseThrow();
    }
}
