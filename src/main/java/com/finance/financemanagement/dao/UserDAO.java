package com.finance.financemanagement.dao;

import com.finance.financemanagement.db.ConnectionPool;
import com.finance.financemanagement.model.User;

import java.sql.*;

public class UserDAO {
    public boolean insertUser(User user){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("INSERT INTO user(username, password, email) VALUES (?,?,?)");
            pst.setString(1,user.getUserName());
            pst.setString(2,user.getPassword());
            pst.setString(3,user.getEmail());
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
    public String getUser(User user){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM user WHERE username=? and password =?");
            pst.setString(1,user.getUserName());
            pst.setString(2,user.getPassword());
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                return rst.getString("username");
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }

    public int getUserByName(String name){

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection conn = connectionPool.getConnection();
        try {
            PreparedStatement pst=conn.prepareStatement("SELECT * FROM user WHERE username=?");
            pst.setString(1,name);
            ResultSet rst = pst.executeQuery();
            if (rst.next()){
                return Integer.parseInt(rst.getString("user_id"));
            }
            return 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            connectionPool.releaseConnection(conn);
        }
    }
}
