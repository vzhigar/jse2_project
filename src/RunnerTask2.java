import factories.ResultFactory;
import utils.GlobalConstants;
import db.ResultsLoader;
import readers.IResultDAO;
import readers.ResultImplXml;
import utils.RunnerLogic;


/**
 * Created by vladimir on 28.05.16.
 */
public class RunnerTask2 {
    public static void main(String[] args) {
        final String FILE_NAME = GlobalConstants.XML_FILE_NAME;
        ResultFactory resultFactory = new ResultFactory();
        IResultDAO reader = new ResultImplXml(FILE_NAME);
        RunnerLogic.clearAllTables();
        ResultsLoader.loadResults(reader);
        RunnerLogic.execute(resultFactory);
    }
}
