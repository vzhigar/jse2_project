package utils;

import beans.Result;
import db.DBConnector;
import factories.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by vladimir on 28.05.16.
 */
public class RunnerLogic {


    public static void execute(ResultFactory resultFactory) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            final int CURRENT_MONTH = new java.util.Date().getMonth() + 1;
            //final int CURRENT_YEAR = new java.util.Date().getYear();
            final String CURRENT_MONTH_QUERY = "SELECT logins.name, tests.name, date, mark FROM logins, tests," +
                    " results WHERE idLogin = loginId AND idTest = testId AND MONTH(date) = " + CURRENT_MONTH + " ORDER BY date";
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(CURRENT_MONTH_QUERY);
            List<Result> list = createList(resultSet, resultFactory);
            printResultList(list);
            printMeanMark(connection, resultFactory);
            printLastDayTestResults(list);

        } catch (SQLException e) {
            System.err.println(GlobalConstants.SQL_EXCEPTION + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
            DBConnector.closeStatement(statement);
            DBConnector.closeConnection(connection);
        }
    }

    private static List<Result> createList(ResultSet resultSet, ResultFactory resultFactory) {
        Result result;
        List<Result> list = new LinkedList<>();
        try {
            final int LOGIN_COLUMN = 1;
            final int TEST_COLUMN = 2;
            final int DATE_COLUMN = 3;
            final int MARK_COLUMN = 4;
            while (resultSet.next()) {
                result = resultFactory.getResultFromFactory(resultSet.getString(LOGIN_COLUMN),
                        resultSet.getString(TEST_COLUMN), resultSet.getString(DATE_COLUMN), resultSet.getInt(MARK_COLUMN));
                list.add(result);
            }
        } catch (SQLException e) {
            System.err.println(GlobalConstants.SQL_EXCEPTION + e);
        } finally {
            DBConnector.closeResultSet(resultSet);
        }
        return list;
    }

    private static void printResultList(List<Result> list) {
        if (!list.isEmpty()) {
            for (Result result : list) {
                System.out.println(result);
            }
        } else System.out.println("Current month results not found.");
    }

    public static void clearAllTables() {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBConnector.getConnection();
            statement = connection.createStatement();
            final String CLEAR_RESULTS_TABLE = "DELETE FROM results";
            final String CLEAR_TESTS_TABLE = "DELETE FROM tests";
            final String CLEAR_LOGINS_TABLE = "DELETE FROM logins";
            statement.execute(CLEAR_RESULTS_TABLE);
            statement.execute(CLEAR_TESTS_TABLE);
            statement.execute(CLEAR_LOGINS_TABLE);
        } catch (SQLException e) {
            System.err.println(GlobalConstants.SQL_EXCEPTION + e);
        } finally {
            DBConnector.closeConnection(connection);
            DBConnector.closeStatement(statement);
        }
    }

    private static void printMeanMark(Connection connection, ResultFactory factory) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            final int DIVIDER = factory.getDivider();
            final int LOGIN_COLUMN = 1;
            final int MEAN_MARK_COLUMN = 2;
            final String MEAN_MARK_QUERY = "SELECT name, sum(mark)/count(*)/" + DIVIDER + " AS avgMark FROM logins, results" +
                    " WHERE loginId = idLogin GROUP BY name ORDER BY avgMark DESC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(MEAN_MARK_QUERY);
            while (resultSet.next()) {
                String name = resultSet.getString(LOGIN_COLUMN);
                double mark = resultSet.getDouble(MEAN_MARK_COLUMN);
                System.out.printf("Student %s, average mark = %.2f\n", name, mark);
            }
        } catch (SQLException e) {
            System.err.println(GlobalConstants.SQL_EXCEPTION + e);
        } finally {
            DBConnector.closeStRs(statement, resultSet);
        }
    }

    private static void printLastDayTestResults(List<Result> list) {
        ListIterator<Result> iterator = list.listIterator(list.size());
        Date maxDate = null;
        System.out.println("Last day of month test results:");
        if (iterator.hasPrevious()) {
            while (iterator.hasPrevious()) {
                Result result = iterator.previous();
                if (maxDate == null) {
                    maxDate = result.getDate();
                    System.out.println(result);
                } else {
                    if (maxDate.equals(result.getDate())) {
                        System.out.println(result);
                    }
                }
            }
        } else {
            System.out.println("Not found");
        }
    }
}
