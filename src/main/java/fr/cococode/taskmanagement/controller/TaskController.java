package fr.cococode.taskmanagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cococode.taskmanagement.dto.TaskDTO;
import fr.cococode.taskmanagement.model.Task;
import fr.cococode.taskmanagement.service.TaskService;
import jakarta.validation.Valid;

/* Le controller fait partie de l'architecture MVC pour le dev d'app et API web.
 * 
 * @RequestMapping : Indique le chemin d'URI pour accéder à une ressource en HTTP,
 *  ici, donne des informations applicables par défaut pour l’ensemble des méthodes de cette classe.
 *  (Le chemin '/api/tasks' s'appliquera avant chaque autre chemin déclaré par une méthode)
 *  
 *  @RestController : indique qu’il s’agit d’un contrôleur spécialisé pour le développement d’API Web,
 *   Il s’agit donc d’un contrôleur dont les méthodes retournent par défaut les données à renvoyer au client 
 *   plutôt qu’un identifiant de vue (avec Controller).
 */

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAllTasks() {
		List<TaskDTO> allTasks = taskService.getAllTasks();
		
		return ResponseEntity.ok(allTasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
		TaskDTO task = taskService.getTaskById(id);
		
		return ResponseEntity.ok(task);
	}
	
	/* @Valid : Indique qu'il faut valider l'input de l'utilisateur (taskDTO) (il existe aussi @Validated)
	 * @RequestBody : indique que le paramètre représente le corps de la requête envoyée 
	 *  (il permet de désérialiser (JSON vers TaskDTO) automatiquement)
	 */
	
	@PostMapping
	public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
		TaskDTO createdTask = taskService.createTask(taskDTO);
		
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(
			@PathVariable Long id,
			@Valid @RequestBody TaskDTO taskDTO) {
		
		TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
		
		return ResponseEntity.ok(updatedTask);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
		
		return ResponseEntity.noContent().build();
	}
	
	
	

}
