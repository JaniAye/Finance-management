package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.AccountsDAO;
import com.finance.financemanagement.dao.UserDAO;
import com.finance.financemanagement.dao.LoansDAO;
import com.finance.financemanagement.model.Loan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Loans", value = "/Loans")
public class LoansController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("userName");
        int uid = new UserDAO().getUserByName(username);
        int accountId = new AccountsDAO().getAccountByIdAndType(uid, "loan");

        if (accountId>0){
            Loan sv= new LoansDAO().getLoanByAccid(accountId);

            if (sv!=null){
                request.setAttribute("result",sv);
                request.getRequestDispatcher("loan.jsp").include(request, response);

            }else {
                request.setAttribute("status","fail");
                request.getRequestDispatcher("createAccount.jsp").include(request, response);

            }
        }else {
           RequestDispatcher dispatcher= request.getRequestDispatcher("createAccount.jsp");
            dispatcher.forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int loanId = Integer.parseInt(request.getParameter("accNumber"));
        double total = Double.parseDouble(request.getParameter("totAmo"));
        double payment = Double.parseDouble(request.getParameter("amount"));

        int i = new LoansDAO().updateLoan(loanId, (total - payment));
        if (i>0){
            Loan sv= new LoansDAO().getLoanByAccid(loanId);

            request.setAttribute("result",sv);

            request.getRequestDispatcher("loan.jsp").include(request, response);
        }else {
            request.getRequestDispatcher("accounts.jsp").include(request, response);
        }
    }


}