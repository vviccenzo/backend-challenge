package backend.challenge.modules.task.dtos;

import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor(staticName = "create")
@AllArgsConstructor
public class TaskDTO {

    private String title;

    private String description;

    private Long id;

    private int progress;

    private TaskStatus status;

    public TaskDTO(Task task) {
        this.description = task.getDescription();
        this.title = task.getTitle();
        this.id = task.getId();
        this.progress = task.getProgress();
        this.status = task.getStatus();
    }

    public TaskDTO(String title, String description) {
        this.description = description;
        this.title = title;
    }
}
