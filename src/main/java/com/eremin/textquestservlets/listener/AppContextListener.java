package com.eremin.textquestservlets.listener;

import com.eremin.textquestservlets.service.QuestionService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        QuestionService questionService = new QuestionService();
        context.setAttribute("questionService", questionService);
        ServletContextListener.super.contextInitialized(sce);
    }
}
