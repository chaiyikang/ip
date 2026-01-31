package steve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getCommand_validCommand_success() {
        Parser parser = new Parser();
        String[] input = {"list"};
        assertEquals(Command.LIST, parser.getCommand(input));

        String[] input2 = {"bye"};
        assertEquals(Command.BYE, parser.getCommand(input2));
    }

    @Test
    public void getCommand_invalidCommand_throwsException() {
        Parser parser = new Parser();
        String[] input = {"bippity bopity boop"};
        assertThrows(IllegalArgumentException.class, () -> {
            parser.getCommand(input);
        });
    }

    @Test
    public void getCommand_emptyInput_throwsException() {
        Parser parser = new Parser();
        String[] input = {""};
        assertThrows(IllegalArgumentException.class, () -> {
            parser.getCommand(input);
        });
    }
}
