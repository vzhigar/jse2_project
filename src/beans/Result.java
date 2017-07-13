package beans;

import java.sql.Date;


public class Result {
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result(String login, String test, String date, int mark) {
        this.login = login;
        this.test = test;
        setDate(date);
        this.mark = mark;
    }

    public Result(String login, String test, String date, String mark) {
        this.login = login;
        this.test = test;
        setDate(date);
        setMark(mark);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = Date.valueOf(date);
    }

    public int getMark() {
        return mark;
    }

    public String getStringMark() {
        final String MARK_PRINT_FORMAT = "%.1f";
        double mark = (double)this.mark / 10;
        return String.format(MARK_PRINT_FORMAT, mark);
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setMark(String mark) {
        this.mark = (int)(Double.parseDouble(mark) * 10);
    }

    @Override
    public String toString() {
        final String DELIMITER = ";";
        return login + DELIMITER + test + DELIMITER + date + DELIMITER + getStringMark();
    }
}
