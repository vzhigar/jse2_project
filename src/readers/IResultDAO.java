package readers;

import beans.Result;


public interface IResultDAO {
        Result nextResult();
        boolean hasResult();
        void closeReader();
}
