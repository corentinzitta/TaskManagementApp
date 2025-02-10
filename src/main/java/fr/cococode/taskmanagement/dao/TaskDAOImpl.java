package fr.cococode.taskmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import fr.cococode.taskmanagement.model.Task;
import fr.cococode.taskmanagement.model.TaskStatus;
import fr.cococode.taskmanagement.repository.TaskRepository;

// Implémentation du DAO Task.

// @Repository est un stéréotype purement descriptif, il représente un mécanisme 
// permettant de stocker et de rechercher une collection d’objets.

// un DAO (Data Access Object) est une forme de repository qui réalise, 
// la plupart du temps, une interface avec un système de gestion de base de données.

@Repository
public class TaskDAOImpl implements TaskDAO {
	
	private final TaskRepository taskRepository;
	
	public TaskDAOImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Optional<Task> findById(Long id) {
		return taskRepository.findById(id);
	}

	@Override
	public Task save(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void deleteById(Long id) {
		taskRepository.deleteById(id);
	}

	@Override
	public Boolean existsById(Long id) {
		return taskRepository.existsById(id);
	}

	@Override
	public List<Task> findByStatus(TaskStatus status) {
		return findByStatus(status);
	}

}
