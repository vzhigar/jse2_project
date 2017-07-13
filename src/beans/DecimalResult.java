package beans;


public class DecimalResult extends Result {

    public DecimalResult(String login, String test, String date, int mark) {
        super(login, test, date, mark);
    }

    public DecimalResult(String login, String test, String date, String mark) {
        super(login, test, date, mark);
    }

    @Override
    public void setMark(String mark) {
        super.setMark(Integer.parseInt(mark));
    }

    @Override
    public String getStringMark() {
        return String.valueOf(super.getMark());
    }
}
