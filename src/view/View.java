package view;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.MyException;
import com.exception.StatementException;
import controller.Controller;
import domain.interfaces.InterfaceValue;
import domain.statements.IfStatement;
import domain.PrgState;
import domain.adts.*;
import domain.expresions.ArithmeticExp;
import domain.expresions.ValueExp;
import domain.expresions.VarExp;
import domain.interfaces.InterfaceStatement;
import domain.statements.AssignStatement;
import domain.statements.CompoundStatement;
import domain.statements.PrintStatement;
import domain.statements.VarDeclStatement;
import domain.types.BoolType;
import domain.types.IntType;
import domain.values.BoolValue;
import domain.values.IntValue;
import repository.InterfaceRepository;
import repository.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class View {
    public void start() {
        printMenu();
        boolean done = false;
        while(!done) {
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();
            try {
                if (Objects.equals(option, "1")) {
                    runProgram1();
                } else if (Objects.equals(option, "2")) {
                    runProgram2();
                } else if (Objects.equals(option, "3")) {
                    runProgram3();
                } else if (Objects.equals(option, "4")) {
                    done = true;
                }
            } catch (Exception exception) {
                System.out.println(exception.toString());
            }
        }
    }
    public void runProgram1() throws MyException, ADTException, StatementException, ExpressionException, IOException {
        InterfaceStatement ex1 =
                new CompoundStatement(
                        new VarDeclStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("v", new ValueExp(new IntValue(2))), new PrintStatement(new VarExp("v")))
                );
        runStatement(ex1);
    }

    public void runProgram2() throws MyException, ADTException, StatementException, ExpressionException, IOException {
        InterfaceStatement ex2 =
                new CompoundStatement(
                        new VarDeclStatement("a", new IntType()),
                        new CompoundStatement(
                                new VarDeclStatement("b", new IntType()),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "a",
                                                new ArithmeticExp(
                                                        "+",
                                                        new ValueExp(new IntValue(2)),
                                                        new ArithmeticExp(
                                                                "*",
                                                                new ValueExp(new IntValue(3)),
                                                                new ValueExp(new IntValue(5))))),
                                        new CompoundStatement(
                                                new AssignStatement(
                                                        "b",
                                                        new ArithmeticExp(
                                                                "+",
                                                                new VarExp("a"),
                                                                new ValueExp(new IntValue(1)))),
                                                new PrintStatement(new VarExp("b"))))));
        runStatement(ex2);
    }

    public void runProgram3() throws ADTException, MyException, StatementException, ExpressionException, IOException {
        InterfaceStatement ex3 =
                new CompoundStatement(
                        new VarDeclStatement("a", new BoolType()),
                        new CompoundStatement(
                                new VarDeclStatement("v", new IntType()),
                                new CompoundStatement(
                                        new AssignStatement("a", new ValueExp(new BoolValue(true))),
                                        new CompoundStatement(
                                                new IfStatement(new VarExp("a"),
                                                        new AssignStatement("v", new ValueExp(new IntValue(2))),
                                                        new AssignStatement("v", new ValueExp(new IntValue(3)))),
                                                new PrintStatement(new VarExp("v"))))));
        runStatement(ex3);
    }

    private void runStatement(InterfaceStatement statement) throws ADTException, MyException, StatementException, ExpressionException, IOException {
        MyInterfaceStack<InterfaceStatement> stack = new MyStack<>();
        MyInterfaceDictionary<String, InterfaceValue> dictionary = new MyDictionary<>();
        MyInterfaceList<InterfaceValue> output = new MyList<>();
        MyInterfaceDictionary<String, BufferedReader> fileTable = new MyDictionary<>();
        MyInterfaceHeap heap = new MyHeap();

        PrgState prgState = new PrgState(stack, dictionary, output, fileTable, heap, statement);

        InterfaceRepository repository = new Repository(prgState, "log.txt");
        Controller controller = new Controller(repository);

        boolean result = printSteps();

        if(result) {
            controller.setDisplayFlag(true);
        } else {
            controller.setNotDisplayFlag();
        }

        controller.allStep();
        System.out.println("Result is" + prgState.getOut().toString());

    }

    private void printMenu() {
        System.out.println("1. Run program 1.");
        System.out.println("2. Run program 2.");
        System.out.println("3. Run program 3.");
        System.out.println("4. Exit.");
    }

    private boolean printSteps() {
        System.out.println("Do you want to print the program steps?y/n");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        return Objects.equals(option, "y");
    }
}
