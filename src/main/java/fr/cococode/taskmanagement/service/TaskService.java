package fr.cococode.taskmanagement.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import fr.cococode.taskmanagement.dao.TaskDAO;
import fr.cococode.taskmanagement.dto.TaskDTO;
import fr.cococode.taskmanagement.exception.TaskNotFoundException;
import fr.cococode.taskmanagement.model.Task;
import fr.cococode.taskmanagement.model.TaskStatus;

// On encapsule dans un Service la logique métier. Un service gère la logique métier et 
// utilise des DAO pour déléguer l'accès aux bases de données, et réaliser des requêtes.

// @Service est purement descriptif, il permet d'offrir plus d'informations au développeur.
// Un Service renvoie aux classes qui ont la charge de réaliser les fonctionnalités principales,
// celui-ci est normalement stateless (ne maintient pas d'état conversationnel)

// @Transactional demarre une transaction (commit & rollback) à l’appel de la méthode utilisant cette annotation
// et est validée au retour de la méthode. Nous n’avons pas de code particulier à écrire pour cela.
// Spring Transaction utilise la programmation orientée aspect (AOP) pour instrumenter notre code afin d’obtenir le comportement souhaité.

@Service
@Transactional
public class TaskService {
	
	private final TaskDAO taskDAO;
	
	public TaskService(TaskDAO taskDAO) {
		this.taskDAO = taskDAO;
	}
	
	/* *** CRUD Operations *** */
	
	public List<TaskDTO> getAllTasks() {
		return taskDAO.findAll()
				.stream()
				.map(this::convertToDTO)
				.collect(Collectors.toList());
	}
	
	public TaskDTO getTaskById(Long id) {
		Task task = findTaskOrThrow(id);
		
		return convertToDTO(task);
	}
	
	public TaskDTO createTask(TaskDTO taskDTO) {
		Task task = convertToEntity(taskDTO);
		
		validateTaskCreation(task);
		
		taskDAO.save(task);
		
		return convertToDTO(task);
		
	}
	
	public TaskDTO updateTask(Long taskId,TaskDTO taskDTO) {
		Task existingTask = findTaskOrThrow(taskId);
		
		validateTaskUpdate(existingTask, taskDTO);
		updateTaskFields(existingTask,taskDTO);
		
		Task updatedTask = taskDAO.save(existingTask);
		
		return convertToDTO(updatedTask);
	}

	public void deleteTask(Long taskId) {
		// Task existingTask = findTaskOrThrow(taskId); PAS BESOIN de récupérer la task existante pour la supprimer
		
        if (!taskDAO.existsById(taskId)) {
            throw new TaskNotFoundException(taskId);
        }
		
		validateTaskDeletion(taskId);
		
		taskDAO.deleteById(taskId);
	}
	
	/* *** 				 *** */
	
	/* *** 	Business logic methods  *** */

	private Task findTaskOrThrow(Long id) {
        return taskDAO.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
	
    private TaskDTO convertToDTO(Task task) {
    	TaskDTO dto = new TaskDTO();
    	
    	dto.setId(task.getId());
    	dto.setTitle(task.getTitle());
    	dto.setDescription(task.getDescription());
    	dto.setStatus(task.getStatus().toString());
    	
    	return dto;
    }

    private Task convertToEntity(TaskDTO taskDTO) {
    	Task task = new Task();
    	
    	task.setTitle(taskDTO.getTitle());
    	task.setDescription(taskDTO.getDescription());
    	task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
    	
    	return task;
    	
    }
    
    private void updateTaskFields(Task task, TaskDTO taskDTO) {
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        
        if (taskDTO.getStatus() != null) {
            task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
        }
        
        task.setUpdatedAt(LocalDateTime.now());
    }
    
	private void validateTaskCreation(Task task) {
    	// Add business rules for task creation
        // For example: check if user has reached task limit, validate task priority, etc.
	}
    
	private void validateTaskUpdate(Task existingTask, TaskDTO taskDTO) {
        // Add business rules for task deletion
        // For example: check if task can be deleted based on its status
	}
    
    private void validateTaskDeletion(Long taskId) {
        // Add business rules for task deletion
        // For example: check if task can be deleted based on its status
	}
    
    /* *** 				 *** */

}
