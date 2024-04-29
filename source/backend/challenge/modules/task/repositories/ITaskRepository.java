package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;

import java.util.List;

public interface ITaskRepository {

	Task index(Long taskId);

	List<Task> show();

	Task create(TaskDTO taskDTO);

	Task update(TaskDTO taskDTO, Long taskId);

	void delete(Long taskId);

	Task updateTaskProgress(Long taskId, TaskProgressView taskProgressView);
	
}
