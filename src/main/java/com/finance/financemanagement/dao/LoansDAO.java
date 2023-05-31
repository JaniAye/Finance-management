package com.finance.financemanagement.dao;

import com.finance.financemanagement.db.ConnectionPool;
import com.finance.financemanagement.model.Loan;
import com.finance.financemanagement.model.Savings;

import java.sql.*;

public class LoansDAO {

    public Loan getLoanByAccid(int accId){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM loanaccount WHERE account_id=? LIMIT 1");
            pst.setInt(1,accId);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                Loan ln = new Loan();
                ln.setAccId(rst.getInt("loan_id"));
                ln.setIrate(rst.getDouble("interest_rate"));
                ln.setLoanAmo(rst.getDouble("loan_amount"));
                ln.setMonthlyInterest(rst.getDouble("monthly_intrest"));
                ln.setTotAmount(rst.getDouble("total_amount"));
                System.out.println("ln.getLoanAmo() = " + ln.getLoanAmo());
                return ln;
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public int updateLoan(int accId,double pay){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("UPDATE loanaccount SET total_amount = ? WHERE loan_id = ?");
            pst.setDouble(1,pay);
            pst.setDouble(2,accId);
            return pst.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
}
