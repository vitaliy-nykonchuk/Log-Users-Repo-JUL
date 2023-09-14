package services;

import database.DBCheck;
import entities.User;
import exceptions.DBException;
import repositories.UserReadRepository;
import utils.Constants;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserReadService {
    UserReadRepository repository;

    private static final Logger LOGGER =
            Logger.getLogger(UserReadService.class.getName());

    public UserReadService(UserReadRepository repository) {
        this.repository = repository;
    }

    public String readUsers() {
        // Проверяем на наличие файла БД.
        // ДА - работаем с данными. НЕТ - уведомление об отсутствии БД.
        if (DBCheck.isDBExists()) {
            try {
                throw new DBException(Constants.DB_ABSENT_MSG);
            } catch (DBException e) {
                LOGGER.log(Level.SEVERE, Constants.LOG_DB_ABSENT_MSG);
                return e.getMessage();
            }
        }

        // Получаем данные в коллекцию.
        List<User> users = repository.readUsers();

        // Если коллекция не null, формируем вывод.
        // Иначе уведомление об отсутствии данных.
        if (users != null) {
            // Если коллекция не пуста, формируем вывод.
            // Иначе уведомление об отсутствии данных.
            if (!users.isEmpty()) {
                AtomicInteger count = new AtomicInteger(0);
                StringBuilder stringBuilder = new StringBuilder();
                users.forEach((user) ->
                        stringBuilder.append(count.incrementAndGet())
                                .append(") id - ")
                                .append(user.getId())
                                .append(", ")
                                .append(user.getName())
                                .append(", ")
                                .append(user.getPhone())
                                .append(", ")
                                .append(user.getEmail())
                                .append("\n")
                );
                return stringBuilder.toString();
            } else {
                LOGGER.log(Level.WARNING, Constants.LOG_DATA_ABSENT_MSG);
                return Constants.DATA_ABSENT_MSG;
            }
        } else {
            LOGGER.log(Level.WARNING, Constants.LOG_DATA_ABSENT_MSG);
            return Constants.DATA_ABSENT_MSG;
        }
    }
}
