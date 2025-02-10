package fr.cococode.taskmanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// Task est une entité. (provient de JPA (Jakarta))
// Une entité est simplement une instance d’une classe qui sera persistante
// (que l’on pourra sauvegarder dans / charger depuis une base de données relationnelle). 

// Une entité est signalée par l’annotation @Entity sur la classe. De plus, une entité JPA doit 
// disposer d’un ou plusieurs attributs définissant un identifiant grâce à l’annotation @Id. 
// Cet identifiant correspondra à la clé primaire dans la table associée.

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Title is required")
	@Size(max = 100, message = "Title cannot exceed 100 characters")
	private String title;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.TODO;
	
	@Size(max = 500, message = "Description cannot exceed 500 characters")
	private String description;
	
	private LocalDateTime createdAt = LocalDateTime.now();
	
	private LocalDateTime updatedAt = LocalDateTime.now();
	
	// setters and getters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
