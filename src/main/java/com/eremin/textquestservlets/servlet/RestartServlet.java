package com.eremin.textquestservlets.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.eremin.textquestservlets.consts.Const.*;


@WebServlet(name = RESTART_SERVLET, value = SLASH + RESTART)
public class RestartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect(QUESTION);
    }
}
