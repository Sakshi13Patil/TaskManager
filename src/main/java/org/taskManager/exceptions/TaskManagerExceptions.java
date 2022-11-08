package org.taskManager.exceptions;

public class TaskManagerExceptions extends RuntimeException{
    public TaskManagerExceptions() {
        super();
    }

    public TaskManagerExceptions(String message) {
        super(message);
    }

    protected TaskManagerExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
