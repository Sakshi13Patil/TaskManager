package org.taskManager.service;

import org.taskManager.exceptions.TaskManagerExceptions;
import org.taskManager.model.Sprint;
import org.taskManager.repository.TaskManagerRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class SprintManager {
    /*
     1. Sprint creation
     2. Remove Sprint*/
    private final TaskManagerRepository taskManagerRepository;

    public SprintManager(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }

    public Sprint createSprint(Map<String, String> sprintDetails) {
        Sprint sprint = Sprint.builder().id(UUID.randomUUID().toString())
                .name(sprintDetails.get("name"))
                .issues(new ArrayList<>())
                .build();
        Optional<Sprint> savedSprint = taskManagerRepository.saveSprint(sprint);
        if (savedSprint.isPresent())
            return savedSprint.get();
        else
            throw new TaskManagerExceptions("Sprint cannot be created");
    }

    public void removeSprint(String sprintId) {
        taskManagerRepository.removeSprint(sprintId);
    }
}
