package com.maximalus.servlets;

import com.maximalus.model.User;
import com.maximalus.service.UserService;
import com.maximalus.util.HibernateSessionFactoryUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Maksym Matlo
 */

public class InsertServlet extends HttpServlet {
    private UserService userService = new UserService();
    private IndexServlet indexServlet = new IndexServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = addUser(req);
        System.out.println("doPost");
        setUserAttributes(user, req);
        indexServlet.doGet(req, resp);
    }

    private User addUser(HttpServletRequest req){
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        userService.save(user);
        return user;
    }

    private void setUserAttributes(User user, HttpServletRequest req){
        req.setAttribute("firstName", user.getFirstName());
        req.setAttribute("lastName", user.getLastName());
    }
}
