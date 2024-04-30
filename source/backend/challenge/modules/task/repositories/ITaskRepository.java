package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;

import java.util.List;

public interface ITaskRepository {

	TaskDTO index(Long taskId);

	List<TaskDTO> show();

	TaskDTO create(TaskDTO taskDTO);

	TaskDTO update(TaskDTO taskDTO, Long taskId);

	void delete(Long taskId);

	TaskDTO updateTaskProgress(Long taskId, TaskProgressView taskProgressView);
	
}
