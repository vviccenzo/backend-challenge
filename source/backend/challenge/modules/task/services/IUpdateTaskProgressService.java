package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;

public interface IUpdateTaskProgressService {

	TaskDTO execute(Long taskId, TaskProgressView taskProgressView);

}
