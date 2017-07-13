import factories.ResultFactory;
import utils.GlobalConstants;
import factories.DecimalResultFactory;
import readers.IResultDAO;
import db.ResultsLoader;
import readers.ResultImplCsv;
import utils.RunnerLogic;


/**
 * Created by vladimir on 27.05.16.
 */
public class RunnerTask1 {
    public static void main(String[] args) {
        final String FILE_NAME = GlobalConstants.TASK1_CSV_FILE_NAME;
        ResultFactory resultFactory = new DecimalResultFactory();
        IResultDAO reader = new ResultImplCsv(FILE_NAME, resultFactory);
        RunnerLogic.clearAllTables();
        ResultsLoader.loadResults(reader);
        RunnerLogic.execute(resultFactory);
    }
}
