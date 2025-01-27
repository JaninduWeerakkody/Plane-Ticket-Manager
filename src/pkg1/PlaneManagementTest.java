package pkg1;

import static org.junit.jupiter.api.Assertions.*;

class PlaneManagementTest {

    @org.junit.jupiter.api.Test
    void rowCheck() {
        assertTrue(PlaneManagement.rowCheck("A"));
        assertTrue(PlaneManagement.rowCheck("B"));
        assertTrue(PlaneManagement.rowCheck("C"));
        assertTrue(PlaneManagement.rowCheck("D"));
        assertFalse(PlaneManagement.rowCheck("E"));
    }

    @org.junit.jupiter.api.Test
    void columnCheck() {
        assertTrue(PlaneManagement.seatNumCheck(1, "A"));
        assertTrue(PlaneManagement.seatNumCheck(14, "A"));
        assertTrue(PlaneManagement.seatNumCheck(1, "D"));
        assertTrue(PlaneManagement.seatNumCheck(13, "D"));
        assertTrue(PlaneManagement.seatNumCheck(1, "B"));
        assertTrue(PlaneManagement.seatNumCheck(12, "B"));
        assertTrue(PlaneManagement.seatNumCheck(1, "C"));
        assertTrue(PlaneManagement.seatNumCheck(12, "C"));
        assertFalse(PlaneManagement.seatNumCheck(0, "A"));
        assertFalse(PlaneManagement.seatNumCheck(15, "A"));
        assertFalse(PlaneManagement.seatNumCheck(0, "D"));
    }

    @org.junit.jupiter.api.Test
    void rowNumber() {
        assertEquals(0, PlaneManagement.rowNumber("A"));
        assertEquals(1, PlaneManagement.rowNumber("B"));
        assertEquals(2, PlaneManagement.rowNumber("C"));
        assertEquals(3, PlaneManagement.rowNumber("D"));

    }

    @org.junit.jupiter.api.Test
    void buy_seat() {
    }

    @org.junit.jupiter.api.Test
    void cancel_seat() {
    }

    @org.junit.jupiter.api.Test
    void find_first_available() {
    }

    @org.junit.jupiter.api.Test
    void show_seating_plan() {
    }

    @org.junit.jupiter.api.Test
    void print_tickets_info() {
    }

    @org.junit.jupiter.api.Test
    void search_ticket() {
    }
}