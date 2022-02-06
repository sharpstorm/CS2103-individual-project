package duke.command;

import duke.task.TaskList;
import duke.util.Printable;

/**
 * Represents a handler for the list command.
 */
public class ListCommand extends Command {
    /**
     * Creates a handler for the list command.
     *
     * @param args Arguments supplied to the command handler.
     */
    ListCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) {
        linePrinter.print("This is your task list:");
        taskList.doForEach((index, task) -> {
            // Note that index passed into this consumer is 0-based. Increment by 1 for readability
            assert task != null;
            assert index >= 0;
            linePrinter.print(String.format("%d. %s", index + 1, task.getReadableString()));
        });
        return true;
    }
}
