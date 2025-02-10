package fr.cococode.taskmanagement.exception;

public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TaskNotFoundException(Long id) {
		super("task not found with id: " + id);
	}

}
