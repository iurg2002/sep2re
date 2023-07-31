package dao;

import model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarDAOTest {
    private CarDAO carDAO;

    @BeforeEach
    void setUp() throws SQLException {
        carDAO = CarDAOImpl.getInstance();
    }

    @Test
    void create() throws SQLException {
        // Perform the test
        Car createdCar = carDAO.create("TestModel", 2023, 100.0, true);

        // Assertions to check if the created car is not null and has the correct values
        assertNotNull(createdCar);
        assertEquals("TestModel", createdCar.getModel());
        assertEquals(2023, createdCar.getYear());
        assertEquals(100.0, createdCar.getRentalPrice(), 0.001);
        assertTrue(createdCar.isAvailability());

        carDAO.delete(createdCar);
    }

    @Test
    void readById() throws SQLException {
        // Create a test car in the database
        Car testCar = carDAO.create("TestModel", 2023, 100.0, true);

        // Perform the test
        Car retrievedCar = carDAO.readById(testCar.getCarId());

        // Assertions to check if the retrieved car is not null and has the correct values
        assertNotNull(retrievedCar);
        assertEquals(testCar.getCarId(), retrievedCar.getCarId());
        assertEquals(testCar.getModel(), retrievedCar.getModel());
        assertEquals(testCar.getYear(), retrievedCar.getYear());
        assertEquals(testCar.getRentalPrice(), retrievedCar.getRentalPrice(), 0.001);
        assertEquals(testCar.isAvailability(), retrievedCar.isAvailability());

        carDAO.delete(testCar);
    }


    @Test
    void update() throws SQLException {
        // Create a test car in the database
        Car testCar = carDAO.create("TestModel", 2023, 100.0, true);

        // Update the test car
        testCar.setModel("UpdatedModel");
        testCar.setYear(2022);
        testCar.setRentalPrice(90.0);
        testCar.setAvailability(false);

        // Perform the test
        carDAO.update(testCar);

        // Retrieve the updated car from the database
        Car updatedCar = carDAO.readById(testCar.getCarId());

        // Assertions to check if the retrieved car has the updated values
        assertNotNull(updatedCar);
        assertEquals("UpdatedModel", updatedCar.getModel());
        assertEquals(2022, updatedCar.getYear());
        assertEquals(90.0, updatedCar.getRentalPrice(), 0.001);
        assertFalse(updatedCar.isAvailability());

        carDAO.delete(testCar);
    }

    @Test
    void delete() throws SQLException {
        // Create a test car in the database
        Car testCar = carDAO.create("TestModel", 2023, 100.0, true);

        // Perform the test
        carDAO.delete(testCar);

        // Try to retrieve the deleted car from the database
        Car deletedCar = carDAO.readById(testCar.getCarId());

        // Assertions to check if the retrieved car is null (deleted)
        assertNull(deletedCar);
    }
}
