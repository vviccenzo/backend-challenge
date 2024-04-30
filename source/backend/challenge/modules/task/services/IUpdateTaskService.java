package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

public interface IUpdateTaskService {

	TaskDTO execute(TaskDTO task, Long taskId);

}
