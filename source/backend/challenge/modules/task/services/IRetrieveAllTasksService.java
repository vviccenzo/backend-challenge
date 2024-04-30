package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

import java.util.List;

public interface IRetrieveAllTasksService {

	List<TaskDTO> execute();

}
