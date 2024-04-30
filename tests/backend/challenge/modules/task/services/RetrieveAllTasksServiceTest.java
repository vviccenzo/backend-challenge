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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;

@RunWith(KikahaRunner.class)
public class RetrieveAllTasksServiceTest {

    private IRetrieveAllTasksService retrieveAllTasksService;

    @Mock
    private ITaskRepository taskRepository;

    private List<TaskDTO> tasksCreated = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
        tasksCreated = generateRandomTasks(10);
    }

    @Test
    public void shouldBeAbleToListTheTasks() {
        when(taskRepository.show()).thenReturn(tasksCreated);
        Assert.assertEquals(retrieveAllTasksService.execute().size(), 10);
    }

    public static List<TaskDTO> generateRandomTasks(int numberOfTasks) {
        List<TaskDTO> tasks = new ArrayList<>();

        String[] possibleTitles = {"Task 1", "Task 2", "Task 3", "Task 4", "Task 5"};
        String[] possibleDescriptions = {"Description 1", "Description 2", "Description 3", "Description 4", "Description 5"};

        Random random = new Random();

        for (int i = 0; i < numberOfTasks; i++) {
            String possibleTitle = possibleTitles[random.nextInt(possibleTitles.length)];
            String possibleDescription = possibleDescriptions[random.nextInt(possibleDescriptions.length)];
            TaskDTO dtoUsedToCreateTask = new TaskDTO(possibleTitle, possibleDescription);
            Task taskCreated = new Task(dtoUsedToCreateTask);
            
            tasks.add(new TaskDTO(taskCreated));
        }

        return tasks;
    }

}