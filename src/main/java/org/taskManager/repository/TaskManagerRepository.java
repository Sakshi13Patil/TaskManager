package org.taskManager.repository;

import org.taskManager.constants.Status;
import org.taskManager.exceptions.TaskManagerExceptions;
import org.taskManager.model.Issue;
import org.taskManager.model.Sprint;
import org.taskManager.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskManagerRepository {
    List<Issue> issues = new ArrayList<>();
    List<Sprint> sprints = new ArrayList<>();
    List<User> users = new ArrayList<>();

    public Optional<User> getUserById(String userId) {
        return users.stream().filter(user -> user.getId().equals(userId)).findFirst();
    }

    public Optional<Issue> getIssueById(String issueId) {
        return issues.stream().filter(issue -> issue.getId().equals(issueId)).findFirst();
    }

    public Issue saveIssue(Issue issue) {
        issues.add(issue);
        return issue;
    }

    public Issue updateStatusOfIssue(Status status, String issueId) {
        Optional<Issue> currentIssue = getIssueById(issueId);
        currentIssue.ifPresent(issue -> issue.setStatus(status));
        return getIssueById(issueId).get();
    }

    public Optional<Issue> tagSprint(String sprintId, String issueId) {
        Optional<Sprint> sprint = getSprintById(sprintId);
        if (sprint.isPresent()) {
            Optional<Issue> currentIssue = getIssueById(issueId);
            if (currentIssue.isPresent()) {
                currentIssue.get().setTaggedSprint(sprintId);
                sprint.get().getIssues().add(currentIssue.get());
            }
            return getIssueById(issueId);
        } else
            throw new TaskManagerExceptions("Issue occured while tagging sprint");
    }

    public Optional<Issue> unTagSprint(String issueId) {
        Optional<Issue> currentIssue = getIssueById(issueId);
        currentIssue.ifPresent(issue -> issue.setTaggedSprint(null));
        return getIssueById(issueId);
    }

    public Optional<Sprint> getSprintById(String sprintId) {
        return sprints.stream().filter(sprint -> sprint.getId().equals(sprintId)).findFirst();
    }

    public Optional<Sprint> saveSprint(Sprint sprint) {
        sprints.add(sprint);
        return getSprintById(sprint.getId());

    }

    public void removeSprint(String sprintId) {
        sprints = sprints.stream().filter(sprint -> !sprint.getId().equals(sprintId)).collect(Collectors.toList());
    }

    public Optional<User> createUser(User user) {
        users.add(user);
        return getUserById(user.getId());
    }

    public Issue assignUser(String userId, String issueId) {
        Optional<User> toBeAssigned = getUserById(userId);
        Optional<Issue> issue = getIssueById(issueId);
        if (toBeAssigned.isPresent() && issue.isPresent()) {
            issue.get().setAssignedUser(toBeAssigned.get());
            toBeAssigned.get().getTaskIds().add(issue.get().getId());
            return issue.get();
        } else
            throw new TaskManagerExceptions("issue occured");
    }
}
