package Singleton;


public class NotificationManager {
    private static NotificationManager instance;

    private NotificationManager() {}

    public static synchronized NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    public void sendNotification(String message) {
        System.out.println("Уведомление: " + message);
    }
}
