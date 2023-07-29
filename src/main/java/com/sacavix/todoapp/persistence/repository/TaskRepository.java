package com.sacavix.todoapp.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sacavix.todoapp.persistence.entity.Task;
import com.sacavix.todoapp.persistence.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {
	
	//sprint data jpa
	public List<Task> findAllByTaskStatus(TaskStatus status);
	
	//consulta nativa
	//update task set finished =true where id=5
	//select * from task	
	@Modifying
	@Query(value = "update task set finished=true where ID=:id",nativeQuery = true)
	public void markTaskAsFinished(@Param("id") Long id);
	
	
	
}
