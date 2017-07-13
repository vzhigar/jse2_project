package factories;

import beans.DecimalResult;


public class DecimalResultFactory extends ResultFactory {
    @Override
    public DecimalResult getResultFromFactory(String login, String test, String date, String mark) {
        return new DecimalResult(login, test, date, mark);
    }

    @Override
    public DecimalResult getResultFromFactory(String login, String test, String date, int mark) {
        return new DecimalResult(login, test, date, mark);
    }

    @Override
    public int getDivider() {
        final int DECIMAL_RESULT_DIVIDER = 1;
        return DECIMAL_RESULT_DIVIDER;
    }
}
