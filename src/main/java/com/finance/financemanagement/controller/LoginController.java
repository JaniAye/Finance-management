package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.UserDAO;
import com.finance.financemanagement.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.finance.financemanagement.util.EncryptPassword.encryptPassword;

@WebServlet(name = "Login", value = "/Login")
public class LoginController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        String hashedPass = encryptPassword(password);
        User user = new User(userName, hashedPass);
        String usernm = new UserDAO().getUser(user);
        HttpSession session= request.getSession();
        RequestDispatcher dispatcher=null;
        if (usernm!=null){
            session.setAttribute("userName",usernm);
            request.setAttribute("status","success");
            dispatcher= request.getRequestDispatcher("home.jsp");
        }else {
            request.setAttribute("status","fail");
            dispatcher= request.getRequestDispatcher("index.jsp");

        }
        dispatcher.forward(request,response);
    }
}