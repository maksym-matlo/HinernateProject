package com.maximalus.servlets;

import com.maximalus.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Maksym Matlo
 */

public class DeleteServlet extends HttpServlet {
    private UserService userService = new UserService();
    private IndexServlet indexServlet = new IndexServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("id"));
        deleteUser(req);
        indexServlet.doGet(req, resp);
    }

    private void deleteUser(HttpServletRequest req){
        Long id = Long.valueOf(req.getParameter("id"));
        userService.delete(id);
    }
}
