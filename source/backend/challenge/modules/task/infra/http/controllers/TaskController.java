package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;

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
		return DefaultResponse.ok().entity(this.retrieveAllTasksService.execute());
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") Long taskId) {
		return DefaultResponse.ok().entity(this.retrieveTaskByIdService.execute(taskId));
	}

	@POST
	public Response create(TaskDTO taskDTO) {
		return DefaultResponse.ok().entity(this.createTaskService.execute(taskDTO));
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") Long taskId, TaskDTO task) {
		return DefaultResponse.ok().entity(this.updateTaskService.execute(task, taskId));
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") Long taskId) {
		try {
			this.deleteTaskService.execute(taskId);
		} catch (Exception e) {
			return DefaultResponse.serverError();
		}

		return DefaultResponse.ok();
	}

}
