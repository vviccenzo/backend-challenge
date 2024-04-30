package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(KikahaRunner.class)
public class UpdateTaskServiceTest {

    private IUpdateTaskService updateTaskService;

    @Mock
    private ITaskRepository taskRepository;

    private TaskDTO existingTask;
    private TaskDTO updatedTask;
    private TaskDTO taskAfterUpdate;
    private TaskDTO taskBeforeUpdate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        updateTaskService = new UpdateTaskService(taskRepository);
        existingTask = new TaskDTO("title", "description");
        updatedTask = new TaskDTO("title alterado", "description alterado");

        taskBeforeUpdate = createTask(1L, "title", "description", 0, TaskStatus.PROGRESS, new Date());
        taskAfterUpdate = createTask(1L, "title alterado", "description alterado", 0, TaskStatus.PROGRESS, new Date());
    }

    @Test
    public void shouldBeAbleToUpdateTaskTitleAndDescription() {
        when(taskRepository.index(1L)).thenReturn(taskBeforeUpdate);
        when(this.taskRepository.update(updatedTask, 1L)).thenReturn(taskAfterUpdate);
        TaskDTO updated = updateTaskService.execute(updatedTask, 1L);

        assertNotEquals("O título da tarefa deve ser alterado", existingTask.getTitle(), updated.getTitle());
        assertNotEquals("A descrição da tarefa deve ser alterada", existingTask.getDescription(), updated.getDescription());
    }

    @Test
    public void shouldNotBeAbleToUpdateNonExistingTask() {
        long nonExistingTaskId = 5101162744757410452L;
        when(taskRepository.index(nonExistingTaskId)).thenReturn(null);

        TaskDTO task = taskRepository.update(updatedTask, nonExistingTaskId);
        assertNull(task);
    }

    @Test
    public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
        long nonExistingTaskId = 5101162744757410452L;
        when(taskRepository.index(nonExistingTaskId)).thenReturn(null);

        updateTaskService.execute(updatedTask, nonExistingTaskId);
    }

    @Test
    public void shouldNotBeAbleToUpdateTaskStatusManually() {
        when(taskRepository.index(1L)).thenReturn(taskBeforeUpdate);
        when(this.taskRepository.update(updatedTask, 1L)).thenReturn(taskBeforeUpdate);

        TaskDTO updated = updateTaskService.execute(updatedTask, 1L);

        assertEquals("O status da tarefa deve permanecer o mesmo", taskBeforeUpdate.getStatus(), updated.getStatus());
    }

    public TaskDTO createTask(long id, String title, String description, int progress, TaskStatus status, Date createdAt) {
        Task task = new Task();
        task.setId(id);
        task.setTitle(title);
        task.setDescription(description);
        task.setProgress(progress);
        task.setStatus(status);
        task.setCreatedAt(createdAt);
        return new TaskDTO(task);
    }
}