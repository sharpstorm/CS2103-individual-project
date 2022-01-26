package duke.command;

import duke.task.TaskList;
import duke.util.Printable;

public class ExitCommand extends Command {
    ExitCommand(String args) {
        super(args);
    }

    @Override
    public boolean execute(Printable linePrinter, TaskList taskList) {
        linePrinter.print("Goodbye! Have a Nice Day.");
        return false;
    }
}
