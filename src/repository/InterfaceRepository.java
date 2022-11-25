package repository;

import com.exception.ADTException;
import com.exception.MyException;
import domain.PrgState;

import java.io.IOException;

public interface InterfaceRepository {
    PrgState getCrtPrg();
    void logPrgStateExec() throws IOException, ADTException;

    PrgState getCurrentState();
}
