package fr.cococode.taskmanagement.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// DTO pour Data Transfer Object
// Il a pour objectif de représenter le transfert de données entre deux composantes
// Ici, il s'agit du transfert de données de requêtes API. (ça représente un formulaire sous la forme d'un objet)

public class TaskDTO {
	private Long id;
	
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    private String title;
    
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
    
    @Enumerated(EnumType.STRING)
    private String status;
    
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
