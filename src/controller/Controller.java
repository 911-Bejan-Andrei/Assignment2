package controller;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import domain.interfaces.InterfaceStatement;
import domain.PrgState;
import domain.adts.MyInterfaceStack;
import repository.InterfaceRepository;

import java.io.IOException;

public class Controller {
    InterfaceRepository repository;
    boolean displayFlag;

    public Controller(InterfaceRepository repository) {
        this.repository = repository;
        this.displayFlag = false;
    }

    public void oneStep(PrgState state) throws ADTException, StatementException, ExpressionException {
        MyInterfaceStack<InterfaceStatement> stk = state.getExeStack();
        if (stk.empty()) {
            throw new StatementException("Stack is empty!");
        }
        InterfaceStatement crtStmt = stk.pop();
        crtStmt.execute(state);
    }

    public void allStep() throws ADTException, StatementException, ExpressionException, IOException {
        PrgState prg = repository.getCrtPrg();
        repository.logPrgStateExec();
        while (!prg.getExeStack().empty()) {
            oneStep(prg);
            repository.logPrgStateExec();
        }
    }

    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    public void setNotDisplayFlag() {
        this.displayFlag = false;
    }

    void display() {
        if(this.displayFlag) {
            System.out.println(this.repository.getCrtPrg().toString());
        }
    }
}
