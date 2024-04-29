package backend.challenge.modules.task.services;

import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;

public interface IUpdateTaskProgressService {

	Task execute(Long taskId, TaskProgressView taskProgressView);

}
