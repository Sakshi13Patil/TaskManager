package org.taskManager.service.impl;

import org.taskManager.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyAll(String email, String message) {
        System.out.println("Notified for overdue " +message + " email " +email);
    }
}
