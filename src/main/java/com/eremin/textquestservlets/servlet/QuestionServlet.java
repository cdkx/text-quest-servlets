package com.eremin.textquestservlets.servlet;

import java.io.*;

import com.eremin.textquestservlets.model.Root;
import com.eremin.textquestservlets.service.QuestionService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.eremin.textquestservlets.consts.Const.*;


@WebServlet(name = "questionServlet", value = "/question")
public class QuestionServlet extends HttpServlet {
    private QuestionService questionService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        questionService = ((QuestionService) servletContext.getAttribute(QUESTION_SERVICE));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        Root data = questionService.getData();

        if (request.getAttribute("question") == null) {
            request.setAttribute("question", data.getQuestions().getFirst().getQuestion());
            request.setAttribute("answer_1", data.getQuestions().getFirst().getAnswers().get(0));
            request.setAttribute("answer_2", data.getQuestions().getFirst().getAnswers().get(1));
            try {
                request.getRequestDispatcher("/question.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
