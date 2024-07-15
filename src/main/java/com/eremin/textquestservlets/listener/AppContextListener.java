package com.eremin.textquestservlets.listener;

import com.eremin.textquestservlets.service.DataService;
import com.eremin.textquestservlets.service.QuestionService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;


import static com.eremin.textquestservlets.consts.Const.*;


@Slf4j
@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ObjectMapper objectMapper = new ObjectMapper();
        log.debug("create objectMapper");
        context.setAttribute(OBJECT_MAPPER, objectMapper);
        log.debug("set objectMapper");

        DataService dataService = new DataService(context);
        log.debug("create dataService");
        context.setAttribute(DATA_SERVICE, dataService);
        log.debug("set dataService");

        QuestionService questionService = new QuestionService(context);
        log.debug("create questionService");
        context.setAttribute(QUESTION_SERVICE, questionService);
        log.debug("set questionService");

        ServletContextListener.super.contextInitialized(sce);
        log.debug("context initialized");
    }
}
