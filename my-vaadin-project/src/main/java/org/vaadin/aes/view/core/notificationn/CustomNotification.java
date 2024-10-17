package org.vaadin.aes.view.core.notificationn;

import com.vaadin.flow.component.notification.Notification;
import org.vaadin.aes.view.core.CustomSleepUtil;

public class CustomNotification {
    public static void showShort(String title) {
        Notification notification = new Notification(title, 1500);
        notification.open();
    }

    public static void showLong(String title) {
        openNotification(title,300);
    }

    public static void show(String title, int ms) {
        openNotification(title,ms);
    }

    private static void openNotification(String title, int ms) {
        Thread thread = new Thread(() -> {
            Notification notification = new Notification(title, ms);
            notification.open();
 });
        thread.start();
    }
}
