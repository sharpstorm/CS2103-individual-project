package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.testutil.PrinterStub;

public class DeleteCommandTest {
    private final Task[] sourceTasks = new Task[] {
        new Todo("Test"),
        new Event("Test", LocalDateTime.parse("2022-12-22T12:00")),
        new Deadline("Test", LocalDateTime.parse("2022-12-22T13:00")),
        new Deadline("Test", LocalDateTime.parse("2022-12-22T14:00")),
        new Event("Test", LocalDateTime.parse("2022-12-23T15:00")),
        new Event("Test", LocalDateTime.parse("2022-12-24T16:00"))
    };
    private TaskList taskList;

    @BeforeEach
    public void initTaskList() {
        taskList = new TaskList();
        Arrays.stream(sourceTasks).forEachOrdered(taskList::addTask);
    }

    @Test
    public void testParsing_valid_success() throws DukeIllegalArgumentException {
        PrinterStub linePrinter = new PrinterStub();

        new DeleteCommand("1").execute(linePrinter, taskList);
        assertEquals("Deleted the task:", linePrinter.getLines().get(0));
        assertEquals("\t " + sourceTasks[0].getReadableString(), linePrinter.getLines().get(1));

        linePrinter.clear();
        new DeleteCommand("2").execute(linePrinter, taskList);
        assertEquals("Deleted the task:", linePrinter.getLines().get(0));
        assertEquals("\t " + sourceTasks[2].getReadableString(), linePrinter.getLines().get(1));

        linePrinter.clear();
        new DeleteCommand("4").execute(linePrinter, taskList);
        assertEquals("Deleted the task:", linePrinter.getLines().get(0));
        assertEquals("\t " + sourceTasks[5].getReadableString(), linePrinter.getLines().get(1));
    }

    @Test
    public void testParsing_invalidArgs_exceptionRaised() {
        TaskList list = new TaskList();
        list.addTask(new Todo("Test"));
        PrinterStub linePrinter = new PrinterStub();

        try {
            new DeleteCommand("test").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("Task Number must be a number", ex.getMessage());
        }

        try {
            new DeleteCommand("0").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("No matching task with given number", ex.getMessage());
        }

        try {
            new DeleteCommand("2").execute(linePrinter, list);
            fail();
        } catch (DukeIllegalArgumentException ex) {
            assertEquals("No matching task with given number", ex.getMessage());
        }
    }
}
