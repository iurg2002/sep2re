package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static UserDAOImpl instance;

    private UserDAOImpl() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized UserDAOImpl getInstance() throws SQLException {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=sep2", "postgres", "1234");
    }

    @Override
    public User create(String username, String password, String email, String phoneNumber, int role) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO user_table(username, password, email, phoneNumber, role) VALUES (?, ?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setString(4, phoneNumber);
            statement.setInt(5, role);
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                return new User(userId, username, password, email, phoneNumber, role);
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
    }

    @Override
    public User readById(int userId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table WHERE userId = ?");
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                int role = resultSet.getInt("role");
                return new User(userId, username, password, email, phoneNumber, role);
            } else {
                return null;
            }
        }
    }

    @Override
    public User readByUsername(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                int role = resultSet.getInt("role");
                return new User(userId, username, password, email, phoneNumber, role);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<User> readAll() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_table");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<User> result = new ArrayList<>();
            while (resultSet.next()) {
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                int role = resultSet.getInt("role");
                User user = new User(userId, username, password, email, phoneNumber, role);
                result.add(user);
            }
            return result;
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE user_table SET username = ?, password = ?, email = ?, phoneNumber = ?, role = ? WHERE userId = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhoneNumber());
            statement.setInt(5, user.getRole());
            statement.setInt(6, user.getUserId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user_table WHERE userId = ?");
            statement.setInt(1, user.getUserId());
            statement.executeUpdate();
        }
    }
}
