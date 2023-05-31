package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.AccountsDAO;
import com.finance.financemanagement.dao.UserDAO;
import com.finance.financemanagement.model.Accounts;
import com.google.gson.Gson;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "Accounts", value = "/Accounts")
public class AccountController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountType = request.getParameter("transactionType");
        String svType = request.getParameter("svType");


        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        int uid = new UserDAO().getUserByName(username);
        if (svType==null){
            boolean isAccount = new AccountsDAO().getAccountByuid(uid);

            RequestDispatcher dispatcher=null;
            if (isAccount){
                request.setAttribute("status","success");
                if (accountType!=null){
                    request.getRequestDispatcher("transaction.jsp").forward(request, response);
                }else {
                    dispatcher= request.getRequestDispatcher("accounts.jsp");
                    dispatcher.forward(request,response);

                }

            }else {
                request.setAttribute("status","fail");
                dispatcher= request.getRequestDispatcher("createAccount.jsp");
                dispatcher.forward(request,response);

            }
        }else {
            int svId = new AccountsDAO().getAccountByIdAndType(uid, "savings");

            Map<String, Object> responseObject = new HashMap<>();
            Gson gson = new Gson();
            response.setContentType("application/json");

            if (svId>0){
                responseObject.put("status", "success");
                String jsonResponse = gson.toJson(responseObject);
                response.getWriter().write(jsonResponse);
            }else {
                responseObject.put("status", "fail");
                String jsonResponse = gson.toJson(responseObject);
                response.getWriter().write(jsonResponse);
            }


        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountType = request.getParameter("accountType");
        Double depositAmount = Double.parseDouble(request.getParameter("damount"));
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        int uid = new UserDAO().getUserByName(username);

        Map<String, Object> responseObject = new HashMap<>();
        Gson gson = new Gson();
        response.setContentType("application/json");

        if (depositAmount==null || depositAmount<1000){
            responseObject.put("status", "notValid");
            String jsonResponse = gson.toJson(responseObject);
            response.getWriter().write(jsonResponse);
        }else {
            Accounts accounts = new Accounts(uid, accountType, depositAmount);
            RequestDispatcher  dispatcher=request.getRequestDispatcher("accounts.jsp");
            if (new AccountsDAO().insertAccount(accounts))
                responseObject.put("status", "success");
            else
                responseObject.put("status", "fail");

            String jsonResponse = gson.toJson(responseObject);
            response.getWriter().write(jsonResponse);
        }
    }


}