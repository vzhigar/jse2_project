package readers;

import beans.Result;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class ResultHandler extends DefaultHandler {
    private String login;
    private List<Result> resultList;
    private ResultEnum currentEnum;

    private static enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST
    }

    public ResultHandler() {
        resultList = new ArrayList<Result>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(qName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            final int NAME_INDEX = 0, DATE_INDEX = 1, MARK_INDEX = 2;
            String name = attributes.getValue(NAME_INDEX);
            String date = attributes.getValue(DATE_INDEX);
            String mark = attributes.getValue(MARK_INDEX);
            resultList.add(new Result(login, name, date, mark));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentEnum == ResultEnum.LOGIN) {
            String login = new String(ch, start, length).trim();
            if (!login.isEmpty()) {
                this.login = login;
            }
        }
    }

    public List<Result> getResultList() {
        return resultList;
    }
}