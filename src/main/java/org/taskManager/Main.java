package org.taskManager;

import org.taskManager.model.Issue;
import org.taskManager.model.Sprint;
import org.taskManager.model.User;
import org.taskManager.repository.TaskManagerRepository;
import org.taskManager.service.IssueManager;
import org.taskManager.service.SprintManager;
import org.taskManager.service.UserManager;

import java.util.HashMap;
import java.util.Map;

public class Main {
    static TaskManagerRepository taskManagerRepository = new TaskManagerRepository();
    static UserManager userManager = new UserManager(taskManagerRepository);
    static SprintManager sprintManager = new SprintManager(taskManagerRepository);
    static IssueManager issueManager = new IssueManager(taskManagerRepository);

    public static void main(String[] args) {
        System.out.println("Hello world!");
        createTestUsers();
        //Create Issue

        Issue issue = issueManager.createIssue(createSampleIssue("check abc", "STORY"), "user1");
        System.out.println("Issue Created " + issue);

        issueManager.changeStatus(issue.getId(), "TRIAGED");
        System.out.println("Status changed " + issue);

        issueManager.assignUser("user2", issue.getId());
        System.out.println("User Assigned " + issue);

        Sprint sprint = sprintManager.createSprint(createSampleSprint("October2021"));
        System.out.println("Sprint Created " + sprint);

        issueManager.tagSprint(sprint.getId(), issue.getId());
        System.out.println("After tagging " + issue);

        issueManager.unTagSprint(issue.getId());
        System.out.println("After untagging " + issue);
    }


    private static void createTestUsers() {
        Map<String, String> usrDetails = new HashMap<>();
        usrDetails.put("email", "sakshi@gmail.com");
        usrDetails.put("id", "user1");
        User user1 = userManager.createUser(usrDetails);
        System.out.println("user created " + user1);
        usrDetails.put("email", "abc@gmail.com");
        usrDetails.put("id", "user2");
        User user2 = userManager.createUser(usrDetails);
        System.out.println("user created " + user2);
        usrDetails.put("email", "mnp@gmail.com");
        usrDetails.put("id", "user3");
        User user3 = userManager.createUser(usrDetails);
        System.out.println("user created " + user3);
    }

    private static Map<String, String> createSampleIssue(String title, String issueType) {
        Map<String, String> issue = new HashMap<>();
        issue.put("title", title);
        issue.put("issueType", issueType);
        return issue;
    }

    private static Map<String, String> createSampleSprint(String name) {
        Map<String, String> sprint = new HashMap<>();
        sprint.put("name", name);
        return sprint;
    }
}