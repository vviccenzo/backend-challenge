package backend.challenge.modules.task.models;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Task {

    private Long id;

    private String title;

    private String description;

    private int progress;

    private TaskStatus status;

    private Date createdAt;

    public Task(final TaskDTO dto) {
    	this.id = UUID.randomUUID().getLeastSignificantBits();
		this.createdAt = new Date();
		this.description = dto.getDescription();
		this.progress = 0;
		this.status = TaskStatus.PROGRESS;
		this.title = dto.getTitle();
    }
}
