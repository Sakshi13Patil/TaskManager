package org.taskManager.service;

import org.taskManager.constants.IssueType;
import org.taskManager.constants.Status;
import org.taskManager.exceptions.TaskManagerExceptions;
import org.taskManager.model.Issue;
import org.taskManager.model.Sprint;
import org.taskManager.model.User;
import org.taskManager.repository.TaskManagerRepository;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class IssueManager {
    private final TaskManagerRepository taskManagerRepository;

    public IssueManager(TaskManagerRepository taskManagerRepository) {
        this.taskManagerRepository = taskManagerRepository;
    }

    /* 1. Create Issue
    2. update status
    3. tag sprint
    4. untag Sprint
    * */

    public Issue createIssue(Map<String, String> issueDetails, String userId) {
        Optional<User> user = taskManagerRepository.getUserById(userId);
        if (user.isPresent()) {
            Issue issue = Issue.builder()
                    .id(UUID.randomUUID().toString())
                    .title(issueDetails.get("title"))
                    .issueType(IssueType.valueOf(issueDetails.get("issueType")))
                    .description(issueDetails.get("description"))
                    .status(Status.CREATED)
                    .assignedUser(user.get())
                    .childTasks(new ArrayList<>())
                    .storyPoints(Integer.parseInt(issueDetails.getOrDefault("storyPoints","0"))).build();
            return taskManagerRepository.saveIssue(issue);
        } else
            throw new TaskManagerExceptions("User " + userId + " is not present in the System");

    }

    public Issue changeStatus(String issueId, String status) {
        return taskManagerRepository.updateStatusOfIssue(Status.valueOf(status), issueId);
    }


    public Issue assignUser(String userId, String issueId) {
        return taskManagerRepository.assignUser(userId, issueId);
    }

    public Issue tagSprint(String sprintId, String issueId) {
        Optional<Issue> updateIssue = taskManagerRepository.tagSprint(sprintId, issueId);
        if (updateIssue.isPresent())
            return updateIssue.get();
        else
            throw new TaskManagerExceptions("Issue Occurred while tagging sprint to issue" + issueId);
    }

    public Issue unTagSprint(String issueId) {
        Optional<Issue> updateIssue = taskManagerRepository.unTagSprint(issueId);
        if (updateIssue.isPresent())
            return updateIssue.get();
        else
            throw new TaskManagerExceptions("Issue Occurred while Untagging sprint to issue" + issueId);
    }

}
