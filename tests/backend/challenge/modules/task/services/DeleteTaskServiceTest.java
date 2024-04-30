package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(KikahaRunner.class)
public class DeleteTaskServiceTest {

    @Mock
    private ITaskRepository taskRepository;

    private IDeleteTaskService deleteTaskService;
    private List<Task> tasks = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        tasks = generateRandomTasks(10);

        deleteTaskService = new DeleteTaskService(taskRepository);
    }

    @Test
    public void shouldBeAbleToDeleteTaskById() {
        Long taskIdToDelete = tasks.get(0).getId();
        deleteTaskService.execute(taskIdToDelete);
        verify(taskRepository, times(1));
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