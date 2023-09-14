package repositories;

import database.DBConn;
import entities.User;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDeleteRepository {
    private static final Logger LOGGER =
            Logger.getLogger(UserDeleteRepository.class.getName());

    public String deleteUser(User user) {

        String sql = "DELETE FROM " + Constants.TABLE_CONTACTS + " WHERE id = ?";

        try (PreparedStatement stmt = DBConn.connect().prepareStatement(sql)) {
            // установка соответствующих параметров
            stmt.setInt(1, user.getId());
            // выполнение запроса в БД
            stmt.executeUpdate();
            return Constants.DATA_DELETE_MSG;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constants.LOG_DB_ERROR_MSG);
            return e.getMessage();
        }
    }
}
