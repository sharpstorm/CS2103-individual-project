import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Deadline extends Task {
    private LocalDateTime by;

    public Deadline(String task, LocalDateTime by) {
        super(TaskType.DEADLINE, task);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return this.by;
    }

    @Override
    public Optional<LocalDateTime> getDate() {
        return Optional.of(this.by);
    }

    @Override
    public String getReadableString() {
        return String.format("%s (by: %s)", super.getReadableString(),
                this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy '-' hh:mm a")));
    }
}
