package org.taskManager.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Sprint {

    private String id;
    @NonNull
    private String name;
    private List<Issue> issues = new ArrayList<>();
}
