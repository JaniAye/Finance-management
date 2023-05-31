package com.finance.financemanagement.controller;

import com.finance.financemanagement.dao.AccountsDAO;
import com.finance.financemanagement.dao.LoansDAO;
import com.finance.financemanagement.dao.UserDAO;
import com.finance.financemanagement.dao.FixedDepositDAO;
import com.finance.financemanagement.model.FixedDeposit;
import com.finance.financemanagement.model.Loan;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

@WebServlet(name = "FixedDeposit", value = "/FixedDeposit")
public class FixedDepositController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("userName");
            int uid = new UserDAO().getUserByName(username);
        System.out.println("uid = " + uid);
            int accountId = new AccountsDAO().getAccountByIdAndType(uid, "fixedDeposit");
        System.out.println("accountId = " + accountId);
        if (accountId>0){
            FixedDeposit fd= new FixedDepositDAO().getFdAccountByAccid(accountId);

            if (fd!=null){
                double interest = 0;
                LocalDate localEndDate = new java.util.Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate localStartDate = new java.util.Date(fd.getCreateDate().getTime()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                long daysBetween = ChronoUnit.DAYS.between(localStartDate, localEndDate);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String interestEarned = decimalFormat.format(((fd.getDepositAmo()*20.0/100)/365)*daysBetween);
                fd.setInterestEarned(Double.parseDouble(interestEarned));

                request.setAttribute("result",fd);

                request.getRequestDispatcher("fixedDeposit.jsp").include(request, response);

            }else {
                System.out.println("111111111111111");
                request.setAttribute("status","fail");
                request.getRequestDispatcher("createAccount.jsp").include(request, response);

            }
        }else {
            System.out.println("2222222222222222222222");
            RequestDispatcher dispatcher= request.getRequestDispatcher("createAccount.jsp");
            dispatcher.forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("userName");
        int uid = new UserDAO().getUserByName(username);
        System.out.println("uid = " + uid);
        int accountId = new AccountsDAO().getAccountByIdAndType(uid, "fixedDeposit");
        System.out.println("accountId = " + accountId);

        if (accountId>0){
            FixedDeposit sv = new FixedDepositDAO().getFdAccountByAccid(accountId);

            if (sv!=null){
                request.setAttribute("result",sv);
                request.getRequestDispatcher("fixedDeposit.jsp").include(request, response);

            }else {
                request.setAttribute("status","fail");
                request.getRequestDispatcher("createAccount.jsp").include(request, response);

            }
        }else {
            System.out.println("788888888888");
            RequestDispatcher dispatcher= request.getRequestDispatcher("createAccount.jsp");
            dispatcher.forward(request,response);
        }
    }



}