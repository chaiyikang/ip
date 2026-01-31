package steve;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineConstructor_validDate_success() {
        // Test yyyy-MM-dd format
        Deadline deadline1 = new Deadline("return book", "2024-08-22");
        assertEquals("[D][ ] return book (by: Aug-22-2024)", deadline1.toString());

        // Test ISO-8601 format
        Deadline deadline2 = new Deadline("submit assignment", "2024-08-22T18:00:00");
        assertEquals("[D][ ] submit assignment (by: Aug-22-2024)", deadline2.toString());
    }

    @Test
    public void deadlineConstructor_invalidDate_throwsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("invalid date format", "22-08-2024");
        });

        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("invalid date format", "some random string");
        });
    }
}
