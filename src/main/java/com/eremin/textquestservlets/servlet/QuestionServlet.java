package com.eremin.textquestservlets.servlet;

import java.io.*;
import java.util.List;

import com.eremin.textquestservlets.model.Answer;
import com.eremin.textquestservlets.model.Question;
import com.eremin.textquestservlets.service.DataService;
import com.eremin.textquestservlets.service.QuestionService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.eremin.textquestservlets.consts.Const.*;


@WebServlet(name = QUESTION_SERVLET, value = SLASH + QUESTION)
public class QuestionServlet extends HttpServlet {
    private QuestionService questionService;
    private DataService dataService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.questionService = (QuestionService) config.getServletContext().getAttribute(QUESTION_SERVICE);
        this.dataService = (DataService) config.getServletContext().getAttribute(DATA_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Question> questions = dataService.getData().getQuestions();

        if (req.getParameter(ANSWER) == null) {
            sendRequest(req, resp, questions.getFirst());
        } else {
            String answerId = req.getParameter(ANSWER);
            Integer currentAnswerId = Integer.parseInt(answerId);
            Question questionToRequest = questionService.getQuestionByAnswerId(currentAnswerId);
            sendRequest(req, resp, questionToRequest);
        }
    }

    private void sendRequest(HttpServletRequest req, HttpServletResponse resp, Question questionToRequest) throws ServletException, IOException {
        req.setAttribute(QUESTION, questionToRequest);

        List<Answer> answers = questionService.getAnswersByQuestionId(questionToRequest.getId());

        if (questionToRequest.isFailed()) {
            req.setAttribute(ANSWER_1, answers.get(0));
            req.setAttribute(ANSWER_2, answers.get(1));
            getServletContext().getRequestDispatcher(RESTART_JSP).forward(req, resp);
        }

        if (questionToRequest.isSuccess()) {
            req.setAttribute(ANSWER_1, answers.get(0));
            req.setAttribute(ANSWER_2, answers.get(1));
            getServletContext().getRequestDispatcher(RESTART_JSP).forward(req, resp);
        }

        req.setAttribute(ANSWER_1, answers.get(0));
        req.setAttribute(ANSWER_2, answers.get(1));

        getServletContext().getRequestDispatcher(QUESTION_JSP).forward(req, resp);

    }
}
