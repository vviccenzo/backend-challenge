package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	@Inject
	public TaskController(
		final ICreateTaskService createTaskService,
		final IDeleteTaskService deleteTaskService,
		final IRetrieveAllTasksService retrieveAllTasksService,
		final IRetrieveTaskByIdService retrieveTaskByIdService,
		final IUpdateTaskService updateTaskService
	) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
	}

	@GET
	public Response show() {
		try {
			List<TaskDTO> tasks = this.retrieveAllTasksService.execute();
			return DefaultResponse.ok().entity(tasks);
		} catch (Exception e) {
			return DefaultResponse.serverError();
		}
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		try {
			TaskDTO task = this.retrieveTaskByIdService.execute(taskId);
			if (task != null) {
				return DefaultResponse.ok().entity(task);
			} else {
				return DefaultResponse.notFound().entity("Task not found");
			}
		} catch (Exception e) {
			return DefaultResponse.serverError();
		}
	}

	@POST
	public Response create(TaskDTO taskDTO) {
		try {
			TaskDTO createdTask = this.createTaskService.execute(taskDTO);
			return DefaultResponse.ok().entity(createdTask);
		} catch (Exception e) {
			return DefaultResponse.serverError();
		}
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, TaskDTO task) {
		try {
			TaskDTO updatedTask = this.updateTaskService.execute(task, taskId);
			if (updatedTask != null) {
				return DefaultResponse.ok().entity(updatedTask);
			} else {
				return DefaultResponse.notFound().entity("Task not found");
			}
		} catch (Exception e) {
			return DefaultResponse.serverError();
		}
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		try {
			this.deleteTaskService.execute(taskId);
		} catch (Exception e) {
			return DefaultResponse.serverError();
		}

		return DefaultResponse.ok("Task with id: " + taskId + " deleted.");
	}

}
