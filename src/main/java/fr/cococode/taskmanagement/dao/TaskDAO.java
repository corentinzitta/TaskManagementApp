package fr.cococode.taskmanagement.dao;

import java.util.List;
import java.util.Optional;

import fr.cococode.taskmanagement.model.Task;
import fr.cococode.taskmanagement.model.TaskStatus;

// Dao (Data Access Object) gère toutes les opérations avec les bases de données

public interface TaskDAO {
	List<Task> findAll();
	Optional<Task> findById(Long id);
	Task save(Task task);
	void deleteById(Long id);
	Boolean existsById(Long id);
	
	//more specific database operations : 
	
	List<Task> findByStatus(TaskStatus status);
	
}
