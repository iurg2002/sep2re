package dao;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() throws SQLException {

        // Initialize the UserDAOImpl instance
        userDAO = UserDAOImpl.getInstance();
    }

    @Test
    void create() throws SQLException {
        // Perform the test
        User createdUser = userDAO.create("testUser", "testPassword", "test@example.com", "1234567890", 1);

        // Assertions to check if the created user is not null and has the correct values
        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUsername());
        assertEquals("testPassword", createdUser.getPassword());
        assertEquals("test@example.com", createdUser.getEmail());
        assertEquals("1234567890", createdUser.getPhoneNumber());
        assertEquals(1, createdUser.getRole());

        userDAO.delete(createdUser);
    }

    @Test
    void readById() throws SQLException {
        // Create a test user in the database
        User testUser = userDAO.create("testUser", "testPassword", "test@example.com", "1234567890", 1);

        // Perform the test
        User retrievedUser = userDAO.readById(testUser.getUserId());

        // Assertions to check if the retrieved user is not null and has the correct values
        assertNotNull(retrievedUser);
        assertEquals(testUser.getUserId(), retrievedUser.getUserId());
        assertEquals(testUser.getUsername(), retrievedUser.getUsername());
        assertEquals(testUser.getPassword(), retrievedUser.getPassword());
        assertEquals(testUser.getEmail(), retrievedUser.getEmail());
        assertEquals(testUser.getPhoneNumber(), retrievedUser.getPhoneNumber());
        assertEquals(testUser.getRole(), retrievedUser.getRole());

        userDAO.delete(testUser);

    }

    @Test
    void readByUsername() throws SQLException {
        // Create a test user in the database
        userDAO.create("testUser", "testPassword", "test@example.com", "1234567890", 1);

        // Perform the test
        User retrievedUser = userDAO.readByUsername("testUser");

        // Assertions to check if the retrieved user is not null and has the correct values
        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());

        userDAO.delete(retrievedUser);
    }

    @Test
    void update() throws SQLException {
        // Create a test user in the database
        User testUser = userDAO.create("testUser", "testPassword", "test@example.com", "1234567890", 1);

        // Update the test user
        testUser.setUsername("updatedUser");
        testUser.setPassword("updatedPassword");
        testUser.setEmail("updated@example.com");
        testUser.setPhoneNumber("9876543210");
        testUser.setRole(2);

        // Perform the test
        userDAO.update(testUser);

        // Retrieve the updated user from the database
        User updatedUser = userDAO.readById(testUser.getUserId());

        // Assertions to check if the retrieved user has the updated values
        assertNotNull(updatedUser);
        assertEquals("updatedUser", updatedUser.getUsername());
        assertEquals("updatedPassword", updatedUser.getPassword());
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals("9876543210", updatedUser.getPhoneNumber());
        assertEquals(2, updatedUser.getRole());

        userDAO.delete(updatedUser);
    }

    @Test
    void delete() throws SQLException {
        // Create a test user in the database
        User testUser = userDAO.create("testUser", "testPassword", "test@example.com", "1234567890", 1);

        // Perform the test
        userDAO.delete(testUser);

        // Try to retrieve the deleted user from the database
        User deletedUser = userDAO.readById(testUser.getUserId());

        // Assertions to check if the retrieved user is null (deleted)
        assertNull(deletedUser);
    }
}
