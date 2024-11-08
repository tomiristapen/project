package Singleton;


public class NotificationManager {
    // Поле для хранения единственного экземпляра NotificationManager
    private static NotificationManager instance;

    // Приватный конструктор для предотвращения создания новых экземпляров
    private NotificationManager() {}

    // Метод для получения единственного экземпляра NotificationManager
    public static synchronized NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    // Метод для отправки уведомлений
    public void sendNotification(String message) {
        System.out.println("Уведомление: " + message);
    }
}
