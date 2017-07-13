package factories;

import beans.HalfResult;


public class HalfResultFactory extends ResultFactory {
    @Override
    public HalfResult getResultFromFactory(String login, String test, String date, String mark) {
        return new HalfResult(login, test, date, mark);
    }

    @Override
    public HalfResult getResultFromFactory(String login, String test, String date, int mark) {
        return new HalfResult(login, test, date, mark);
    }

    @Override
    public int getDivider() {
        final int HALF_RESULT_DIVIDER = 2;
        return HALF_RESULT_DIVIDER;
    }
}
