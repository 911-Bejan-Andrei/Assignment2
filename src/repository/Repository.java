package repository;

import com.exception.ADTException;
import domain.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Repository implements InterfaceRepository {

    ArrayList<PrgState> programStateList;
    int currentPosition;
    String logFilePath;

    public Repository(PrgState prgState, String logFilePath) {
        this.programStateList = new ArrayList<>();
        this.programStateList.add(prgState);
        this.currentPosition = 0;
        this.logFilePath = logFilePath;
    }

    @Override
    public PrgState getCrtPrg() {
        return programStateList.get(this.currentPosition);
    }

    @Override
    public void logPrgStateExec() throws IOException, ADTException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(this.programStateList.get(0).programStateToString());
        logFile.close();
    }

    @Override
    public PrgState getCurrentState() {
        return this.programStateList.get(this.currentPosition);
    }
}
