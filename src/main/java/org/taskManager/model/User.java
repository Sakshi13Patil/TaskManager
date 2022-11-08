package org.taskManager.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class User {

    private String id;
    private String name;
    @NonNull
    private String email;
    private List<String> taskIds = new ArrayList<>();
}
