package backend.challenge.modules.task.services;


import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

@RunWith(KikahaRunner.class)
public class RetrieveTaskByIdServiceTest {

    private final Long taskId = 123807128930L;

    private TaskDTO taskToBeFounded = null;

    @Mock
    private ITaskRepository taskRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        taskToBeFounded = createTask();
    }

    @Test
    public void shouldBeAbleToListTheTaskById() {
        when(this.taskRepository.index(taskId)).thenReturn(taskToBeFounded);
        Assert.assertEquals(taskToBeFounded.getId(), taskId);
    }

    private TaskDTO createTask() {
        Task task = new Task();
        task.setId(taskId);
        task.setTitle("Titulo");
        task.setDescription("Descrição");
        task.setProgress(0);
        task.setStatus(TaskStatus.PROGRESS);
        task.setCreatedAt(new Date());

        return new TaskDTO(task);
    }

}
