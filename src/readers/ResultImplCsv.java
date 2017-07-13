package readers;

import utils.GlobalConstants;
import beans.Result;
import factories.ResultFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;


public class ResultImplCsv implements IResultDAO {

    private Scanner scanner;
    private ResultFactory resultFactory;

    public ResultImplCsv(String fileName, ResultFactory resultFactory) {
        this.resultFactory = resultFactory;
        try {
            scanner = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(GlobalConstants.ERR_FILE_NOT_FOUND + fileName);
        }
    }

    @Override
    public Result nextResult() {
        final String DELIMITER = ";";
        final int LOGIN_INDEX = 0;
        final int TEST_INDEX = 1;
        final int DATE_INDEX = 2;
        final int MARK_INDEX = 3;
        String line = scanner.nextLine();
        String[] result = line.split(DELIMITER);
        String login = result[LOGIN_INDEX];
        String test = result[TEST_INDEX];
        String date = result[DATE_INDEX];
        String mark = result[MARK_INDEX];
        return resultFactory.getResultFromFactory(login, test, date, mark);
    }

    @Override
    public boolean hasResult() {
        return scanner != null && scanner.hasNextLine();
    }

    @Override
    public void closeReader() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
