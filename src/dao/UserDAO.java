package dao;


import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    User create(String username, String password, String email, String phoneNumber, int role) throws SQLException;
    User readById(int userId) throws SQLException;
    User readByUsername(String username) throws SQLException;
    List<User> readAll() throws SQLException;
    void update(User user) throws SQLException;
    void delete(User user) throws SQLException;
}

