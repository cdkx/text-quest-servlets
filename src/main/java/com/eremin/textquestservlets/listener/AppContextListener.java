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

        log.debug("creating objectMapper");
        ObjectMapper objectMapper = new ObjectMapper();
        log.debug("setting objectMapper");
        context.setAttribute(OBJECT_MAPPER, objectMapper);

        log.debug("creating dataService");
        DataService dataService = new DataService(objectMapper);
        log.debug("setting dataService");
        context.setAttribute(DATA_SERVICE, dataService);

        log.debug("creating questionService");
        QuestionService questionService = new QuestionService(dataService);
        log.debug("setting questionService");
        context.setAttribute(QUESTION_SERVICE, questionService);

        ServletContextListener.super.contextInitialized(sce);
        log.debug("context initialized");
    }
}
