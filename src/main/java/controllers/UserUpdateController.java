package controllers;

import services.UserUpdateService;
import utils.AppStarter;
import utils.Constants;
import views.UserUpdateView;


public class UserUpdateController {
    UserUpdateService service;
    UserUpdateView view;

    public UserUpdateController(UserUpdateService service, UserUpdateView view) {
        this.service = service;
        this.view = view;
    }

    public void updateUser() {
        // Получаем данные.
        // Передаем данные на обработку и получаем результат.
        String str = service.updateUser(view.getData());
        // Проверяем результат.
        // Если БД отсутствует, выводим сообщение об этом
        // и закрываем приложение.
        // Иначе выводим сообщение и перезапускаем приложение.
        if (str.equals(Constants.DB_ABSENT_MSG)) {
            // Выводим уведомление.
            view.getOutput(str);
            // Закрываем приложение.
            System.exit(0);
        } else {
            // Выводим уведомление.
            view.getOutput(str);
            // Перезапускаем приложение.
            AppStarter.startApp();
        }
    }
}
