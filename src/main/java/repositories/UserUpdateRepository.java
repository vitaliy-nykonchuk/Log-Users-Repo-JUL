package repositories;

import database.DBConn;
import entities.User;
import utils.Constants;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserUpdateRepository {
    private static final Logger LOGGER =
            Logger.getLogger(UserUpdateRepository.class.getName());

    public String updateUser(User user) {

        String sql = "UPDATE " + Constants.TABLE_CONTACTS + " SET phone = ? WHERE id = ?";
        // PreparedStatement - подготовленное выражение, чтобы избежать SQL-инъекций
        try (PreparedStatement pstmt = DBConn.connect().prepareStatement(sql)) {
            pstmt.setString(1, user.getPhone());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate();
            return Constants.DATA_UPDATE_MSG;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Constants.LOG_DB_ERROR_MSG);
            return e.getMessage();
        }
    }
}
