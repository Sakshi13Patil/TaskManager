package org.taskManager.service;

import org.taskManager.exceptions.TaskManagerExceptions;
import org.taskManager.model.User;
import org.taskManager.repository.TaskManagerRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class UserManager {


    private final TaskManagerRepository taskManagerRepository;

    public UserManager(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }

    public User createUser(Map<String, String> userDetails) {
        User user = User.builder()
                .id(userDetails.get("id"))
                .email(userDetails.get("email"))
                .name(userDetails.get("name")).taskIds(new ArrayList<>()).build();
        Optional<User> userCreated = taskManagerRepository.createUser(user);
        if (userCreated.isPresent())
            return user;

        else
            throw new TaskManagerExceptions("error occured in creatiing user");

    }


}
