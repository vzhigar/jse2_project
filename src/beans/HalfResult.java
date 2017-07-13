package beans;


public class HalfResult extends Result {

    public HalfResult(String login, String test, String  date, int mark) {
        super(login, test, date, mark);
    }

    public HalfResult(String login, String test, String date, String mark) {
        super(login, test, date, mark);
    }

    @Override
    public void setMark(String mark) {
        setMark((int)(Double.parseDouble(mark) * 2));
    }

    @Override
    public String getStringMark() {
        final String HALF_MARK_PRINT_FORMAT = "%.1f";
        if (getMark() % 2 == 0) {
            return String.valueOf(getMark() / 2);
        }
        return String.format(HALF_MARK_PRINT_FORMAT, (double)getMark() / 2);
    }
}
