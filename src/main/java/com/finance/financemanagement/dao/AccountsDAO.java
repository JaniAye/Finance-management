package com.finance.financemanagement.dao;

import com.finance.financemanagement.db.ConnectionPool;
import com.finance.financemanagement.model.Accounts;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class AccountsDAO {

    public boolean insertAccount(Accounts acc){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();

        try {

            PreparedStatement pst=conn.prepareStatement("INSERT INTO account(user_id, account_type, deposit_amount) VALUES (?,?,?)");
            pst.setInt(1,acc.getUserId());
            pst.setString(2,acc.getAccountType());
            pst.setDouble(3,acc.getDepositAmount());
            int rowc= pst.executeUpdate();

            int curAccId=0;

            PreparedStatement pstget=conn.prepareStatement("SELECT LAST_INSERT_ID() FROM account LIMIT 1");
            ResultSet rst = pstget.executeQuery();
            if (rst.next()){
                curAccId = rst.getInt("LAST_INSERT_ID()");
            }

            if (rowc>0){

                if (acc.getAccountType().equals("savings")){
                    PreparedStatement pst2=conn.prepareStatement("INSERT INTO savingsaccount(account_id, interest_rate , balance ) VALUES (?,?,?)");
                    pst2.setInt(1,curAccId);
                    pst2.setDouble(2,4.5);
                    pst2.setDouble(3,acc.getDepositAmount());
                    int countSav= pst2.executeUpdate();

                } else if (acc.getAccountType().equals("loan")) {
                    double monthlyInterestRate = (20.0 / 100.0) /12;
                    double monthlyPayment =  (acc.getDepositAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, 48))
                            / (Math.pow(1 + monthlyInterestRate, 48) - 1);

                    PreparedStatement pst2=conn.prepareStatement("INSERT INTO loanaccount(account_id, interest_rate , loan_amount,monthly_intrest,total_amount ) VALUES (?,?,?,?,?)");
                    pst2.setInt(1,curAccId);
                    pst2.setDouble(2,20);
                    pst2.setDouble(3,acc.getDepositAmount());
                    pst2.setDouble(4,monthlyPayment);
                    pst2.setDouble(5,monthlyPayment*48);
                    int countSav= pst2.executeUpdate();


                } else if (acc.getAccountType().equals("fixedDeposit")) {
                    PreparedStatement pst2=conn.prepareStatement("INSERT INTO fdaccount(account_id,create_date, maturity_date , deposit_amount,interest_earned ) VALUES (?,?,?,?,?)");
                    pst2.setInt(1,curAccId);
                    Date currentDate = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = dateFormat.format(currentDate);
                    Date referenceDate = new Date();
                    Calendar c = Calendar.getInstance();
                    c.setTime(referenceDate);
                    c.add(Calendar.MONTH, 3);
                    Date time = c.getTime();


                    pst2.setDate(2,  new java.sql.Date(currentDate.getTime()));
                    pst2.setDate(3,  new java.sql.Date(time.getTime()));
                    pst2.setDouble(4,acc.getDepositAmount());
                    pst2.setDouble(5,0.0);
                    int countSav= pst2.executeUpdate();
                }
                conn.close();
                return true;
            }

            else{
                conn.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            connectionPool.releaseConnection(conn);
        }

    }
    public boolean getAccountByuid(int uid){

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM account WHERE user_id=? LIMIT 1");
            pst.setInt(1,uid);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                     return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
    //get account id from db using uid and account type
    public int getAccountByIdAndType(int uid,String type){
         ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();

        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM account WHERE user_id=? and account_type=? LIMIT 1");
            pst.setInt(1,uid);
            pst.setString(2, type);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                     return Integer.parseInt(rst.getString("account_id"));
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
    //get account id list from db using uid and account type
    public List getAccountIdList(int uid, String type){
         ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst =null;
            if (type.equals("sav"))
               pst=conn.prepareStatement(" SELECT s.savings_id as id FROM savingsaccount s JOIN account a ON s.account_id = a.account_id WHERE a.user_id =? AND a.account_type ='savings' ");

            if (type.equals("fd"))
                pst=conn.prepareStatement(" SELECT s.fd_id as id FROM fdaccount s JOIN account a ON s.account_id = a.account_id WHERE a.user_id =? AND a.account_type ='fixedDeposit' ");
            pst.setInt(1,uid);
            ResultSet rst = pst.executeQuery();
            List<Integer> ids = new ArrayList<>();

            while (rst.next()) {
                int accountId = rst.getInt("id");
                ids.add(accountId);
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }

}
