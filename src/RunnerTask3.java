import factories.ResultFactory;
import utils.GlobalConstants;
import db.ResultsLoader;
import factories.HalfResultFactory;
import readers.IResultDAO;
import readers.ResultImplCsv;
import utils.RunnerLogic;


/**
 * Created by vladimir on 28.05.16.
 */
public class RunnerTask3 {
    public static void main(String[] args) {
        final String FILE_NAME = GlobalConstants.TASK3_CSV_FILE_NAME;
        ResultFactory resultFactory = new HalfResultFactory();
        IResultDAO reader = new ResultImplCsv(FILE_NAME, resultFactory);
        RunnerLogic.clearAllTables();
        ResultsLoader.loadResults(reader);
        RunnerLogic.execute(resultFactory);
    }
}
