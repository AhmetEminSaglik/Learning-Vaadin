package org.vaadin.aes.view.core.notificationn;

import com.vaadin.flow.component.notification.Notification;

public class CustomNotification {
    public static void show(String title) {
        Notification notification = new Notification(title, 1500);
        notification.open();
    }

    public static void show(String title, int ms) {
        Notification notification = new Notification(title, ms);
        notification.open();
    }
}
