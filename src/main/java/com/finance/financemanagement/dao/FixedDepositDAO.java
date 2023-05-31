package com.finance.financemanagement.dao;

import com.finance.financemanagement.db.ConnectionPool;
import com.finance.financemanagement.model.Accounts;
import com.finance.financemanagement.model.FixedDeposit;

import java.sql.*;

public class FixedDepositDAO {
    public FixedDeposit getFdAccountByAccid(int accId){

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM fdaccount WHERE account_id=? LIMIT 1");
            pst.setInt(1,accId);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                FixedDeposit fd = new FixedDeposit();
                fd.setFdAcc("FD"+rst.getInt("fd_id"));
                fd.setCreateDate(rst.getDate("create_date"));
                fd.setmDate(rst.getDate("maturity_date"));
                fd.setInterestEarned(rst.getDouble("interest_earned"));
                fd.setDepositAmo(rst.getDouble("deposit_amount"));
                return fd;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
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
}
