package steve;

import java.util.Scanner;

/**
 * Represents a parser that handles user input.
 */
public class Parser {

    private Scanner scanner;
    private String rawInput;

    /**
     * Returns a new Parser object.
     */
    public Parser() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and splits the user input into parts.
     *
     * @return A string array containing the parts of the user input.
     */
    public String[] getInputParts() {
        String inpt = this.scanner.nextLine();
        this.rawInput = inpt;
        return inpt.split(" ");
    }

    /**
     * Returns the command corresponding to the user input.
     *
     * @param inputParts The parts of the user input.
     * @return The command corresponding to the user input.
     * @throws IllegalArgumentException If the command is invalid.
     */
    public Command getCommand(String[] inputParts) throws IllegalArgumentException{
        return Command.valueOf(inputParts[0].toUpperCase());
    }

    public String getRawInput() {
        return this.rawInput;
    }

}
