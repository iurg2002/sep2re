package dao;

import model.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDAOTest {
    private ReservationDAO reservationDAO;

    @BeforeEach
    void setUp() throws SQLException {

        // Initialize the ReservationDAOImpl instance
        reservationDAO = ReservationDAOImpl.getInstance();
    }

    @Test
    void create() throws SQLException {
        // Perform the test
        Reservation createdReservation = reservationDAO.create(1, 1, LocalDate.now(), LocalDate.now().plusDays(5), 1);

        // Assertions to check if the created reservation is not null and has the correct values
        assertNotNull(createdReservation);
        assertEquals(1, createdReservation.getUserId());
        assertEquals(1, createdReservation.getCarId());
        assertEquals(LocalDate.now(), createdReservation.getStartDate());
        assertEquals(LocalDate.now().plusDays(5), createdReservation.getEndDate());
        assertEquals(1, createdReservation.getStatus());


        reservationDAO.delete(createdReservation);
    }

    @Test
    void readById() throws SQLException {
        // Create a test reservation in the database
        Reservation testReservation = reservationDAO.create(1, 1, LocalDate.now(), LocalDate.now().plusDays(5), 1);

        // Perform the test
        Reservation retrievedReservation = reservationDAO.readById(testReservation.getReservationId());

        // Assertions to check if the retrieved reservation is not null and has the correct values
        assertNotNull(retrievedReservation);
        assertEquals(testReservation.getReservationId(), retrievedReservation.getReservationId());
        assertEquals(testReservation.getUserId(), retrievedReservation.getUserId());
        assertEquals(testReservation.getCarId(), retrievedReservation.getCarId());
        assertEquals(testReservation.getStartDate(), retrievedReservation.getStartDate());
        assertEquals(testReservation.getEndDate(), retrievedReservation.getEndDate());
        assertEquals(testReservation.getStatus(), retrievedReservation.getStatus());

        reservationDAO.delete(testReservation);
    }

    @Test
    void readByUserId() throws SQLException {
        // Create test reservations in the database
        reservationDAO.create(1, 1, LocalDate.now(), LocalDate.now().plusDays(5), 1);
        reservationDAO.create(1, 2, LocalDate.now().plusDays(7), LocalDate.now().plusDays(10), 2);

        // Perform the test
        List<Reservation> reservationList = reservationDAO.readByUserId(1);

        // Assertions to check if the reservation list is not empty and contains the expected number of reservations
        assertFalse(reservationList.isEmpty());

        reservationDAO.delete(reservationList.get(reservationList.size() - 1));
        reservationDAO.delete(reservationList.get(reservationList.size() - 2));
    }

    @Test
    void update() throws SQLException {
        // Create a test reservation in the database
        Reservation testReservation = reservationDAO.create(1, 1, LocalDate.now(), LocalDate.now().plusDays(5), 1);

        // Update the test reservation
        testReservation.setUserId(2);
        testReservation.setCarId(2);
        testReservation.setStartDate(LocalDate.now().plusDays(1));
        testReservation.setEndDate(LocalDate.now().plusDays(6));
        testReservation.setStatus(2);

        // Perform the test
        reservationDAO.update(testReservation);

        // Retrieve the updated reservation from the database
        Reservation updatedReservation = reservationDAO.readById(testReservation.getReservationId());

        // Assertions to check if the retrieved reservation has the updated values
        assertNotNull(updatedReservation);
        assertEquals(2, updatedReservation.getUserId());
        assertEquals(2, updatedReservation.getCarId());
        assertEquals(LocalDate.now().plusDays(1), updatedReservation.getStartDate());
        assertEquals(LocalDate.now().plusDays(6), updatedReservation.getEndDate());
        assertEquals(2, updatedReservation.getStatus());

        reservationDAO.delete(updatedReservation);
    }

    @Test
    void delete() throws SQLException {
        // Create a test reservation in the database
        Reservation testReservation = reservationDAO.create(1, 1, LocalDate.now(), LocalDate.now().plusDays(5), 1);

        // Perform the test
        reservationDAO.delete(testReservation);

        // Try to retrieve the deleted reservation from the database
        Reservation deletedReservation = reservationDAO.readById(testReservation.getReservationId());

        // Assertions to check if the retrieved reservation is null (deleted)
        assertNull(deletedReservation);
    }
}
