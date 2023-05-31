package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.UserDAO;
import com.finance.financemanagement.model.User;
import com.finance.financemanagement.util.EncryptPassword;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.finance.financemanagement.util.EncryptPassword.encryptPassword;

@WebServlet(name = "User", value = "/User")
public class UserController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        User user = new User(userName, password);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Map<String, Object> responseObject = new HashMap<>();
        Gson gson = new Gson();
        response.setContentType("application/json");
        if (userName==null || email==null || password==null){
            responseObject.put("result", "empty");
            String jsonResponse = gson.toJson(responseObject);
            response.getWriter().write(jsonResponse);
        }else {
            String hashedPass =encryptPassword(password);
            User user = new User(userName, hashedPass, email);
            RequestDispatcher  dispatcher=request.getRequestDispatcher("index.jsp");
            if (new UserDAO().insertUser(user))
                responseObject.put("result", "success");
            else
                responseObject.put("result", "fail");

            String jsonResponse = gson.toJson(responseObject);
            response.getWriter().write(jsonResponse);

        }

    }
}