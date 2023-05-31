package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.AccountsDAO;
import com.finance.financemanagement.dao.UserDAO;
import com.finance.financemanagement.dao.SavingsDAO;
import com.finance.financemanagement.model.Savings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Savings", value = "/Savings")
public class SavingsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("userName");
        int uid = new UserDAO().getUserByName(username);
        int accountId = new AccountsDAO().getAccountByIdAndType(uid, "savings");

        if (accountId>0){
            Savings sv= new SavingsDAO().getSvAccountByAccid(accountId);
//        double interest = (((fd.getDepositAmo() * 20) / 100)/12)*3;
            double interest = 0;
            request.setAttribute("result", sv);

            if (sv!=null){
                request.setAttribute("result",sv);
                request.getRequestDispatcher("savings.jsp").include(request, response);

            }else {
                request.setAttribute("status","fail");
//            dispatcher= request.getRequestDispatcher("createAccount.jsp");
//            dispatcher.forward(request,response);
                request.getRequestDispatcher("createAccount.jsp").include(request, response);

            }
        }else {
           RequestDispatcher dispatcher= request.getRequestDispatcher("createAccount.jsp");
            dispatcher.forward(request,response);
        }


    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String accountType = request.getParameter("accountType");
//        double depositAmount = Double.parseDouble(request.getParameter("damount"));
//
//        HttpSession session = request.getSession();
//        String username = (String) session.getAttribute("userName");
//        int uid = new DBCon().getUserByName(username);
//        if (uid<0){
//            //need error popup
//        }
//        Accounts accounts = new Accounts(uid, accountType, depositAmount);
//        RequestDispatcher  dispatcher=request.getRequestDispatcher("createAccount.jsp");
//            if (new AccountsDAO().insertAccount(accounts))
//                request.setAttribute("status", "success" );
//            else
//                request.setAttribute("status", "fail" );
//            dispatcher.forward(request,response);
//    }


}