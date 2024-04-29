package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class TaskRepository implements ITaskRepository {

	private final List<Task> tasks = new ArrayList<>();

	@Override
	public Task index(Long taskId) {
		return findTaskById(taskId).orElseThrow(() -> new RuntimeException("Task with id: " + taskId + " not found."));
	}

	@Override
	public List<Task> show() {
		return tasks;
	}

	@Override
	public Task create(TaskDTO taskDTO) {
		Task task = new Task(taskDTO);
		tasks.add(task);
		return task;
	}

	@Override
	public Task update(TaskDTO taskDTO, Long taskId) {
		tasks.stream().filter(task -> task.getId().equals(taskId)).forEach(task -> {
			task.setDescription(taskDTO.getDescription());
			task.setTitle(taskDTO.getTitle());
		});
		return this.index(taskId);
	}

	@Override
	public void delete(Long taskId) {
		tasks.removeIf(task -> task.getId().equals(taskId));
	}

	@Override
	public Task updateTaskProgress(Long taskId, TaskProgressView taskProgressView) {
		boolean isProgressValid = taskProgressView.validateIfProgressIsValid();
		if(!isProgressValid) {
			throw new RuntimeException("Progress % invalid.");
		}

		tasks.stream().filter(task -> task.getId().equals(taskId))
				.forEach(task -> {
					int progress = taskProgressView.getProgress();
					task.setProgress(progress);

					if (progress == 100) {
						task.setStatus(TaskStatus.COMPLETE);
					}
				});

		return this.index(taskId);
	}

	private Optional<Task> findTaskById(Long taskId) {
		return tasks.stream().filter(task -> task.getId().equals(taskId)).findFirst();
	}

}
