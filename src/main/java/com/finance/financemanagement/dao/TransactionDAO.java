package com.finance.financemanagement.dao;

import com.finance.financemanagement.db.ConnectionPool;
import com.finance.financemanagement.model.Loan;
import com.finance.financemanagement.model.Transaction;
import com.finance.financemanagement.model.User;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TransactionDAO {

    public boolean insertTransaction(Transaction transaction){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("INSERT INTO transaction(account_id, transaction_type, amount,date,remark) VALUES (?,?,?,?,?)");
            pst.setInt(1,transaction.getAccId());
            pst.setString(2,transaction.gettType());
            pst.setDouble(3,transaction.getAmount());
            pst.setDate(4,new java.sql.Date(transaction.getDate().getTime()));
            pst.setString(5,transaction.getRemark());
            int rowc= pst.executeUpdate();
            conn.close();
            if (rowc>0)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            connectionPool.releaseConnection(conn);
        }

    }
}
