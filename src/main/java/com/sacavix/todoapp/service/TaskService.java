package com.sacavix.todoapp.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.tomcat.util.net.openssl.ciphers.MessageDigest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sacavix.todoapp.exceptions.ToDoExceptions;
import com.sacavix.todoapp.mapper.TaskInDTOToTask;
import com.sacavix.todoapp.persistence.entity.Task;
import com.sacavix.todoapp.persistence.entity.TaskStatus;
import com.sacavix.todoapp.persistence.repository.TaskRepository;
import com.sacavix.todoapp.service.dto.TaskInDTO;

@Service
public class TaskService {

	private final TaskRepository repository;
	private final TaskInDTOToTask mapper;

	public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	public Task createTask(TaskInDTO taskInDTO) {
		Task task = mapper.map(taskInDTO);
		return this.repository.save(task);
	}

	public List<Task> findAll() {
		return this.repository.findAll();
	}

	public List<Task> findAllByTaskStatus(TaskStatus status) {
		return this.repository.findAllByTaskStatus(status);
	}

	@Transactional
	public void updateTaskAsFinished(Long id) {
		Optional<Task> optionalTask = this.repository.findById(id);
		if (optionalTask.isEmpty()) {
			throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
		}
		this.repository.markTaskAsFinished(id);
	}
	
	public void deleteByid(Long id) {
		Optional<Task> optionalTask= this.repository.findById(id);
		if(optionalTask.isEmpty()) {
			throw new ToDoExceptions("no se pudo borrar el task", HttpStatus.NOT_FOUND);
		}
		this.repository.deleteById(id);
	}

}
