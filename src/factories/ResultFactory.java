package factories;

import beans.Result;


public class ResultFactory {
    public Result getResultFromFactory(String login, String test, String date, String mark) {
        return new Result(login, test, date, mark);
    }

    public Result getResultFromFactory(String login, String test, String date, int mark) {
        return new Result(login, test, date, mark);
    }

    public int getDivider() {
        final int RESULT_DIVIDER = 10;
        return RESULT_DIVIDER;
    }
}
