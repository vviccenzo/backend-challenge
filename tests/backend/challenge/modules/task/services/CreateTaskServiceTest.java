package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(KikahaRunner.class)
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		String title = "SizeBay";
		String description = "Completar desafio backend Sizebay";

		TaskDTO taskDTO = new TaskDTO(title, description);
		Task task = createTaskService.execute(taskDTO);

		Assert.assertNotNull(task.getId());
		Assert.assertEquals(task.getTitle(), title);
		Assert.assertEquals(task.getDescription(), description);
	}

}