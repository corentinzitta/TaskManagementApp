package fr.cococode.taskmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cococode.taskmanagement.model.Task;

// JpaRepository<T, ID> hérite de CrudRepository<T, ID>  (héritant lui même de Repository<T,ID>) 
// fournit un ensemble de méthodes plus spécifiquement adaptées pour interagir avec une base de données relationnelle.

public interface TaskRepository extends JpaRepository<Task,Long> {

}
