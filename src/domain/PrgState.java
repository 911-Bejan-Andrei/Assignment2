package domain;

import com.exception.ADTException;
import domain.adts.*;
import domain.interfaces.InterfaceStatement;
import domain.interfaces.InterfaceValue;

import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    MyInterfaceStack<InterfaceStatement> exeStack;
    MyInterfaceDictionary<String, InterfaceValue> symTable;
    MyInterfaceList<InterfaceValue> out;
    private MyInterfaceDictionary<String, BufferedReader> fileTable;
    private MyInterfaceHeap heap;
    InterfaceStatement originalProgram;

    public PrgState(
            MyInterfaceStack<InterfaceStatement> stk,
            MyInterfaceDictionary<String, InterfaceValue> symTbl,
            MyInterfaceList<InterfaceValue> ot,
            MyInterfaceDictionary<String, BufferedReader> fileTable,
            MyInterfaceHeap heap,
            InterfaceStatement prg) {
        exeStack = stk;
        symTable = symTbl;
        out = ot;
        originalProgram = prg.deepCopy();
        stk.push(prg);
    }

    public MyInterfaceStack<InterfaceStatement> getExeStack() {
        return this.exeStack;
    }

    public MyInterfaceDictionary<String, InterfaceValue> getSymTable() {
        return this.symTable;
    }

    public MyInterfaceList<InterfaceValue> getOut() { return this.out; }

    public void setExeStack(MyInterfaceStack<InterfaceStatement> exeStack) {
        this.exeStack = exeStack;
    }

    public void setSymTable(MyInterfaceDictionary<String, InterfaceValue> symTable) {
        this.symTable = symTable;
    }

    public void setOut(MyInterfaceList<InterfaceValue> out) {
        this.out = out;
    }

    public void setFileTable(MyInterfaceDictionary<String, BufferedReader> newFileTable) {
        this.fileTable = newFileTable;
    }

    public void setHeap(MyInterfaceHeap newHeap) {
        this.heap = newHeap;
    }

    public MyInterfaceDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public MyInterfaceHeap getHeap() {
        return this.heap;
    }

    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<InterfaceStatement> stack = exeStack.getReversed();
        for (InterfaceStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString()).append("\n");
        }
        return exeStackStringBuilder.toString();
    }

    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (InterfaceValue elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    public String heapToString() throws ADTException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }

    @Override
    public String toString() {
        String string = null;
        string = "Exe stack:\n" + this.getExeStack().toString() + "\n";
        string += "Sym Table:\n" + this.getSymTable().toString() + "\n";
        string += "Output:" + this.getOut().toString() + "\n";
        return string;
    }

    public String programStateToString() throws ADTException {
        return "\nExecution stack: \n" + exeStackToString() + "Symbol table: \n" + symTableToString() + "Output list: \n" + outToString() + "File table:\n" + fileTableToString() + "Heap memory:\n" + heapToString();
    }
}
