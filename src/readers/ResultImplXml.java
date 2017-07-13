package readers;

import utils.GlobalConstants;
import beans.Result;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class ResultImplXml implements IResultDAO {
    private List<Result> list;
    private ResultHandler handler;
    Iterator<Result> iterator;

    public ResultImplXml(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            handler = new ResultHandler();
            parser.parse(fileName, handler);
            list = handler.getResultList();
            iterator = list.iterator();
        } catch (ParserConfigurationException e) {
            System.err.println(GlobalConstants.ERR_PARSER_CONFIG + e);
        } catch (SAXException e) {
            System.err.println(GlobalConstants.ERR_PARSER_ERROR + e);
        } catch (IOException e) {
            System.err.println(GlobalConstants.ERR_FILE_NOT_FOUND + fileName);
        }
    }

    @Override
    public Result nextResult() {
        return iterator.next();
    }

    @Override
    public boolean hasResult() {
        return iterator != null && iterator.hasNext();
    }

    @Override
    public void closeReader() {
        iterator = null;
    }
}
