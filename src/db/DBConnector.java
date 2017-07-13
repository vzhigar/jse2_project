package db;

import utils.GlobalConstants;
import java.sql.*;


public class DBConnector {
    private static Connection connection;

    private DBConnector() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                final String DRIVER = "org.gjt.mm.mysql.Driver";
                final String DB_URL = "jdbc:mysql://localhost/results?characterEncoding=UTF-8&useSSL=false";
                final String LOGIN = "jse";
                final String PASSWORD = "jse";
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, LOGIN, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            System.err.println(GlobalConstants.ERR_DRIVER_NOT_FOUND);
            System.exit(0);
        } catch (SQLException e) {
            System.err.println(GlobalConstants.SQL_EXCEPTION + e);
            System.exit(0);
        }
    return connection;
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println(GlobalConstants.ERR_RESULTSET_CLOSE + e);
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println(GlobalConstants.ERR_STATEMENT_CLOSE + e);
            }
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(GlobalConstants.ERR_CONNECTION_CLOSE + e);
            }
        }
    }

    static void closePreparedStatements(PreparedStatement... statements) {
        for (PreparedStatement statement : statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.err.println(GlobalConstants.ERR_PREPAREDSTATEMENT_CLOSE + e);
                }
            }
        }
    }

    public static void closeStRs(Statement statement, ResultSet resultSet) {
        closeResultSet(resultSet);
        closeStatement(statement);
    }
}
