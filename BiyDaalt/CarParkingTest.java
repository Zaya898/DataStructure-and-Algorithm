package BiyDaalt;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarParkingTest {

    CarParking carPark;

    @BeforeEach
    void setUp() {
        carPark = new CarParking(3); // Бага багтаамжтай гараж
    }

    @Test
    void testArrivalWhenNotFull() {
        carPark.arrival("AR01");
        assertEquals(1, carPark.garage.size());
        assertTrue(carPark.garage.contains("AR01"));
    }

    @Test
    void testArrivalWhenFull() {
        carPark.arrival("AR01");
        carPark.arrival("AR02");
        carPark.arrival("AR03");
        // Гараж дүүрсэн
        assertTrue(carPark.isFull());

        // Дараагийн машин орохыг оролдох
        carPark.arrival("AR04");
        assertEquals(3, carPark.garage.size()); // Тоо өөрчлөгдөхгүй
        assertFalse(carPark.garage.contains("AR04"));
    }

    @Test
    void testDepartureCarExists() {
        carPark.arrival("AR01");
        carPark.arrival("AR02");
        carPark.departure("AR01"); // гарах машин

        assertFalse(carPark.garage.contains("AR01"));
        assertEquals(1, carPark.garage.size());
        assertTrue(carPark.garage.contains("AR02"));
    }

    @Test
    void testDepartureCarNotExists() {
        carPark.arrival("AR01");
        carPark.departure("AR02"); // гарахгүй машин

        assertEquals(1, carPark.garage.size());
        assertTrue(carPark.garage.contains("AR01"));
    }

    @Test
    void testMultipleDepartures() {
        carPark.arrival("AR01");
        carPark.arrival("AR02");
        carPark.arrival("AR03");

        carPark.departure("AR02"); // Дундах машин гарах
        assertEquals(2, carPark.garage.size());
        assertFalse(carPark.garage.contains("AR02"));
        assertTrue(carPark.garage.contains("AR01"));
        assertTrue(carPark.garage.contains("AR03"));
    }
}
