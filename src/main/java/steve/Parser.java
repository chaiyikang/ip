package steve;

import java.util.Scanner;

public class Parser {

    private Scanner scanner;
    private String rawInput;

    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    public String[] getInputParts() {
        String inpt = this.scanner.nextLine();
        this.rawInput = inpt;
        return inpt.split(" ");
    }

    public Command getCommand(String[] inputParts) throws IllegalArgumentException{
        return Command.valueOf(inputParts[0].toUpperCase());
    }

    public String getRawInput() {
        return this.rawInput;
    }

}
