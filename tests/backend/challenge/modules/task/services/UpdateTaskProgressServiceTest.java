package backend.challenge.modules.task.services;


import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class UpdateTaskProgressServiceTest {

	private IUpdateTaskProgressService updateTaskProgressService;

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository();

		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		/*
			TODO:  Para que esse teste passe, sua aplicação deve permitir que sejam
		         alterados apenas o campo `progress`.
		*/
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		/*
			TODO:  Para que esse teste passe, sua aplicação deve permitir que sejam
		         alterado apenas o campo `status`, quando o progresso for igual a 100.
		*/
	}

}
