package org.taskManager.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.taskManager.constants.IssueType;
import org.taskManager.constants.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Issue {

    private String id;
    private String description;
    @NonNull
    private String title;
    @NonNull
    private IssueType issueType;
    private Status status;
    private List<Issue> childTasks = new ArrayList<>();
    private User assignedUser;
    private User reportingUser;
    private String taggedSprint;
    private Integer storyPoints;
    private LocalDate dueDate;

}
