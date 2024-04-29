package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private IRetrieveTaskByIdService retrieveTaskByIdService;

	private Long taskId = 123807128930L;

	private Task taskToBeFounded = null;

	private List<Task> tasks = new ArrayList<>();

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
		taskToBeFounded = createTask();
		tasks = generateRandomTasks(10);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		/*
			TODO: Para que esse teste passe, sua aplicação deve permitir que seja
			 			retornado uma tarefa com o mesmo id informado.
		*/
	}

	private Task createTask() {
		Task task = new Task();
		task.setId(taskId);
		task.setTitle("Titulo");
		task.setDescription("Descrição");
		task.setProgress(0);
		task.setStatus(TaskStatus.PROGRESS);
		task.setCreatedAt(new Date());

		return task;
	}

	public static List<Task> generateRandomTasks(int numberOfTasks) {
		List<Task> tasks = new ArrayList<>();

		String[] possibleTitles = {"Task 1", "Task 2", "Task 3", "Task 4", "Task 5"};
		String[] possibleDescriptions = {"Description 1", "Description 2", "Description 3", "Description 4", "Description 5"};

		Random random = new Random();

		for (int i = 0; i < numberOfTasks; i++) {
			String possibleTitle = possibleTitles[random.nextInt(possibleTitles.length)];
			String possibleDescription = possibleDescriptions[random.nextInt(possibleDescriptions.length)];
			tasks.add(new Task(new TaskDTO(possibleTitle, possibleDescription)));
		}

		return tasks;
	}
}
