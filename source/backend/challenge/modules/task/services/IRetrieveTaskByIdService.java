package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

public interface IRetrieveTaskByIdService {
	TaskDTO execute(Long taskId);

}
