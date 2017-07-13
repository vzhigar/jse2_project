package db;

import beans.Result;
import readers.IResultDAO;
import utils.GlobalConstants;

import java.sql.*;


public class ResultsLoader {

    public static void loadResults(IResultDAO reader) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement psInsertLogin = null;
        PreparedStatement psInsertTest = null;
        PreparedStatement psInsertResult = null;
        try {
            psInsertLogin = connection.prepareStatement(Constants.INSERT_LOGIN_QUERY);
            psInsertTest = connection.prepareStatement(Constants.INSERT_TEST_QUERY);
            psInsertResult = connection.prepareStatement(Constants.RESULTS_INSERT_QUERY);
            while (reader.hasResult()) {
                Result result = reader.nextResult();
                String login = result.getLogin();
                String test = result.getTest();
                insertId(login, psInsertLogin);
                insertId(test, psInsertTest);
                Date date = result.getDate();
                int mark = result.getMark();
                psInsertResult.setString(Constants.LOGIN, login);
                psInsertResult.setString(Constants.TEST, test);
                psInsertResult.setDate(Constants.DATE, date);
                psInsertResult.setInt(Constants.MARK, mark);
                psInsertResult.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println(GlobalConstants.SQL_EXCEPTION + e);
        } finally {
            reader.closeReader();
            DBConnector.closePreparedStatements(psInsertResult, psInsertTest, psInsertLogin);
            DBConnector.closeConnection(connection);
        }
    }

    private static void insertId(String value, PreparedStatement ps) throws SQLException {
        ps.setString(Constants.FIRST_VALUE, value);
        ps.executeUpdate();
    }

    private static class Constants {
        private static final int FIRST_VALUE = 1;
        private static final int LOGIN = 1;
        private static final int TEST = 2;
        private static final int DATE = 3;
        private static final int MARK = 4;
        private static final String INSERT_LOGIN_QUERY = "INSERT IGNORE INTO logins (name) VALUES(?)";
        private static final String INSERT_TEST_QUERY = "INSERT IGNORE INTO tests (name) VALUES(?)";
        private static final String RESULTS_INSERT_QUERY = "INSERT INTO results VALUES((SELECT idLogin FROM logins" +
                " WHERE name = ?), (SELECT idTest FROM tests WHERE name = ?), ?, ?)";
    }
}
