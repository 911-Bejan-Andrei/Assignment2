package view;

import com.exception.ADTException;
import com.exception.ExpressionException;
import com.exception.StatementException;
import domain.adts.MyDictionary;
import domain.adts.MyInterfaceDictionary;
import java.util.Scanner;

public class TextMenu {
    private final MyInterfaceDictionary<String, Command> commands;

    public TextMenu() {
        this.commands = new MyDictionary<>();
    }

    public void addCommand(Command command) throws StatementException, ADTException, ExpressionException {
        this.commands.put(command.getKey(), command);
    }

    private void printMenu() {
        for (Command command: commands.values()) {
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            try {
                Command command = commands.lookUp(key);
                command.execute();
            } catch (ADTException exception){
                System.out.println("Invalid option");
            }
        }
    }
}
