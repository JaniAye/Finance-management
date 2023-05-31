package com.finance.financemanagement.dao;

import com.finance.financemanagement.db.ConnectionPool;
import com.finance.financemanagement.model.FixedDeposit;
import com.finance.financemanagement.model.Savings;

import java.sql.*;

public class SavingsDAO {
    public Savings getSvAccountByAccid(int accId){

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM savingsaccount WHERE account_id=? LIMIT 1");
            pst.setInt(1,accId);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                Savings sv = new Savings();
                sv.setAccount_id("SV"+rst.getInt("savings_id"));
                sv.setInterest_rate(rst.getDouble("interest_rate"));
                sv.setBalance(rst.getDouble("balance"));
                return sv;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
    public Savings getSvAccountById(int savId){

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM savingsaccount WHERE savings_id=? LIMIT 1");
            pst.setInt(1,savId);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                Savings sv = new Savings();
                sv.setAccount_id("SV"+rst.getInt("savings_id"));
                sv.setInterest_rate(rst.getDouble("interest_rate"));
                sv.setBalance(rst.getDouble("balance"));
                return sv;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
    public int updateSv(double balance, int accId){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("UPDATE savingsaccount SET balance = ? WHERE savings_id = ?");
            pst.setDouble(1,balance);
            pst.setDouble(2,accId);
            return pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }

}
