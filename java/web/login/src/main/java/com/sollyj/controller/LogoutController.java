package com.sollyj.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LogoutController() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
        ServletException,
        IOException {
        // 세션초기화
        request.getSession().invalidate();
        response.sendRedirect("./");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
        ServletException,
        IOException {

    }
}
