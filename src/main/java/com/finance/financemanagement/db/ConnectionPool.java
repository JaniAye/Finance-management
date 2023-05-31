package com.finance.financemanagement.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final int MAX_POOL_SIZE = 10;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/financemng";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";

    private BlockingQueue<Connection> connectionQueue;

    private static ConnectionPool instance;

    private ConnectionPool() {
        connectionQueue = new ArrayBlockingQueue<>(MAX_POOL_SIZE);
        initializePool();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private void initializePool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                connectionQueue.offer(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return connectionQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connectionQueue.offer(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        for (Connection connection : connectionQueue) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        connectionQueue.clear();
    }
}
