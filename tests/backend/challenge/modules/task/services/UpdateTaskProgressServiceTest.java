package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.infra.http.views.TaskProgressView;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.mockito.Mockito.when;

@RunWith(KikahaRunner.class)
public class UpdateTaskProgressServiceTest {

    private IUpdateTaskProgressService updateTaskProgressService;

    @Mock
    private final ITaskRepository taskRepository = new TaskRepository();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
    }

    @Test
    public void shouldBeAbleToUpdateTaskProgress() {
        TaskProgressView taskProgressView = new TaskProgressView(20);
        TaskDTO task = createTask(1L, "title", "description", 10, TaskStatus.PROGRESS, new Date());
        TaskDTO taskWithNewProgress = createTask(1L, "title", "description", 20, TaskStatus.PROGRESS, new Date());

        when(taskRepository.updateTaskProgress(1L, taskProgressView)).thenReturn(taskWithNewProgress);
        when(taskRepository.index(1L)).thenReturn(taskWithNewProgress);

        TaskDTO taskNew = updateTaskProgressService.execute(1L, taskProgressView);

        Assert.assertNotEquals(task.getProgress(), taskNew.getProgress());
    }

    @Test
    public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
        TaskProgressView taskProgressView = new TaskProgressView(20);
        TaskDTO task = createTask(1L, "title", "description", 10, TaskStatus.PROGRESS, new Date());
        TaskDTO taskWithNewProgress = createTask(1L, "title", "description", 100, TaskStatus.COMPLETE, new Date());

        when(taskRepository.updateTaskProgress(1L, taskProgressView)).thenReturn(taskWithNewProgress);

        TaskDTO taskNew = updateTaskProgressService.execute(1L, taskProgressView);

        Assert.assertNotEquals(task.getProgress(), taskNew.getProgress());
        Assert.assertEquals(taskNew.getStatus(), TaskStatus.COMPLETE);
    }

    @Test
    public void shouldNotBeAbleToUpdateTaskProgressWhenProgressLessThanOneHundred() {
        TaskProgressView taskProgressView = new TaskProgressView(-1);
        TaskDTO task = createTask(1L, "title", "description", 10, TaskStatus.PROGRESS, new Date());
        TaskDTO taskWithNewProgress = createTask(1L, "title", "description", 10, TaskStatus.PROGRESS, new Date());

        when(taskRepository.updateTaskProgress(1L, taskProgressView)).thenReturn(taskWithNewProgress);

        TaskDTO taskNew = updateTaskProgressService.execute(1L, taskProgressView);

        Assert.assertEquals(task.getProgress(), taskNew.getProgress());
    }

    @Test
    public void shouldNotBeAbleToUpdateTaskProgressWhenProgressGreaterThanOneHundred() {
        TaskProgressView taskProgressView = new TaskProgressView(101);
        TaskDTO task = createTask(1L, "title", "description", 10, TaskStatus.PROGRESS, new Date());
        TaskDTO taskWithNewProgress = createTask(1L, "title", "description", 10, TaskStatus.COMPLETE, new Date());

        when(taskRepository.updateTaskProgress(1L, taskProgressView)).thenReturn(taskWithNewProgress);

        TaskDTO taskNew = updateTaskProgressService.execute(1L, taskProgressView);

        Assert.assertEquals(task.getProgress(), taskNew.getProgress());
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
