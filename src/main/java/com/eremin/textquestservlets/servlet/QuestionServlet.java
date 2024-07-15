package com.eremin.textquestservlets.servlet;

import java.io.*;
import java.util.List;

import com.eremin.textquestservlets.model.Question;
import com.eremin.textquestservlets.service.DataService;
import com.eremin.textquestservlets.service.QuestionService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.eremin.textquestservlets.consts.Const.*;


@WebServlet(name = "questionServlet", value = "/question")
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

        if (req.getAttribute("question") == null) {
            req.setAttribute("question", questions.getFirst().getQuestion());
            req.setAttribute("answer_1", questions.getFirst().getAnswers().get(0));
            req.setAttribute("answer_2", questions.getFirst().getAnswers().get(1));

            req.getRequestDispatcher("/question.jsp").forward(req, resp);

        }
    }
}
