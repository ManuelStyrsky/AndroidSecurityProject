package com.snakesonwheels.tabely;

import com.snakesonwheels.tabely.model.Reservation;
import com.snakesonwheels.tabely.model.Table;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;



import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ReservationTest {

    private Table table;

    @Before
    public void setUp() throws Exception {
        table = new Table("Possidon Table 1");
        assertTrue("Table is reserved, but should not be", !table.isReserved());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testReservationIsWorking() {
        Reservation reservation = table.reserveTable(new Date());
        assertNotNull("Reservation could not be created", reservation);
        assertTrue("Table is not reserved, but should be", table.isReserved());
    }

    @Test
    public void testMultipleTableReservationsNotPossible() {
        Reservation reservation = table.reserveTable(new Date());
        assertNotNull("Reservation could not be created", reservation);
        assertTrue("Table is not reserved, but should be", table.isReserved());
        Reservation reservation2 = table.reserveTable(new Date());
        assertNotNull("Table could be reserved, but should not be possible to reserve multiple times", reservation2);
    }

    @Test
    public void testCancelTableReservationPossible() {
        Reservation reservation = table.reserveTable(new Date());
        assertNotNull("Reservation could not be created", reservation);
        assertTrue("Table is not reserved, but should be", table.isReserved());
        table.cancelReservation(reservation);
        assertFalse("Table is reserved, but should not be", table.isReserved());
    }
}