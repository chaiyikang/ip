package steve;

/**
 * Represents a parser that handles user input.
 */
public class Parser {

    private String rawInput;

    /**
     * Returns a new Parser object.
     */
    public Parser() {
    }

    /**
     * Reads and splits the user input into parts.
     *
     * @return A string array containing the parts of the user input.
     */
    public String[] getInputParts(String input) {
        this.rawInput = input;
        return input.split(" ");
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