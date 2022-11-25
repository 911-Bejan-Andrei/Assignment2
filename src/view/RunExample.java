package view;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;
import controller.Controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Do you want to display steps?[y/n]");
            Scanner scanner = new Scanner(System.in);
            String option = scanner.next();
            controller.setDisplayFlag(Objects.equals(option, "y"));
            controller.allStep();
        } catch (ExpressionException | ADTException | StatementException | IOException exception) {
            System.out.println("\u001B[31m" + exception.getMessage() + "\u001B[0m");
        }
    }
}
