package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.*;
import com.finance.financemanagement.model.Savings;
import com.finance.financemanagement.model.User;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.finance.financemanagement.model.Transaction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Transaction", value = "/Transaction")
public class TransactionController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accNo = Integer.parseInt(request.getParameter("accNo"));

        String type = request.getParameter("type");
        String accType = request.getParameter("transactionType");
        String remark = request.getParameter("remark");
        double amount = Double.parseDouble(request.getParameter("damount"));

        double totalAmount=0.0;
        Savings savings = new SavingsDAO().getSvAccountById(accNo);

        java.util.Date date = new java.util.Date();

        Map<String, Object> responseObject = new HashMap<>();
        Gson gson = new Gson();
        response.setContentType("application/json");
        if (savings!=null){
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("userName");
            int userByName = new UserDAO().getUserByName(username);
            int accountID = new AccountsDAO().getAccountByIdAndType(userByName, "savings");

                boolean b = new TransactionDAO().insertTransaction(new Transaction(accountID, type, amount, date, remark));
                if (type.equals("dipo")){
                    totalAmount= savings.getBalance()+amount;
                }else {
                    totalAmount= savings.getBalance()-amount;

                }
                int i=0;
                if (accType.equals("sav") || accType.equals("Savings")){
                    i = new SavingsDAO().updateSv(totalAmount, accNo);
                }else {
//            i = new SavingsDAO().updateSv(totalAmount, accountID);
                }
                if (i>0){

                    responseObject.put("result", "success");
                    String jsonResponse = gson.toJson(responseObject);
                    response.getWriter().write(jsonResponse);
                }else {
                    responseObject.put("result", "updateFail");
                    String jsonResponse = gson.toJson(responseObject);
                    response.getWriter().write(jsonResponse);
//                request.getRequestDispatcher("accounts.jsp").include(request, response);
                }

            }else {
                responseObject.put("result", "fail");
                String jsonResponse = gson.toJson(responseObject);
                response.getWriter().write(jsonResponse);
            }


    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tType = request.getParameter("transactionType");
        System.out.println("tType = " + tType);
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("userName");
        int userId = new UserDAO().getUserByName(username);
//        if (tType.equals("sav"))
//            tType="savings";
//
//        if (tType.equals("fd"))
//            tType="fixedDeposit";

        List accountIdList = new AccountsDAO().getAccountIdList(userId, tType);

        RequestDispatcher dispatcher=null;
        if (accountIdList!=null){
            Gson gson = new Gson();
            String accountIdsJson = gson.toJson(accountIdList);
            request.setAttribute("accountIdsJson",accountIdsJson);
            dispatcher= request.getRequestDispatcher("transaction.jsp");
        }else {
            request.setAttribute("status","fail");
            dispatcher= request.getRequestDispatcher("home.jsp");

        }
        dispatcher.forward(request,response);

    }


}