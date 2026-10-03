package edu.gmu.cs321;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This test class checks the functionality of the Immigrant class for creating,
 * updating with valid and invalid values.
 *
 * Contributors: Mostafa Mahtab, Shane Birckhead, Kavon Barr
 */
class ImmigrantTest {

    @BeforeEach
    void reset() {
        Immigrant.resetRegistryForTests();
    }

    // ---------------------------------------------------------
    // CREATE IMMIGRANT TESTS
    // ---------------------------------------------------------
    @Test
    void testCreateImmigrant_Valid_AllFields() {

        Immigrant.CreateResult res = Immigrant.createImmigrant(
                "Maria", "Lopez",
                "123 Main St", "Arlington", "VA", "22201",
                "03/15/1995", "Green Card",
                "703-555-1234", "maria.lopez@email.com"
        );

        assertEquals(Immigrant.CODE_SUCCESS, res.code);
        assertNotNull(res.alienNumber);
        assertTrue(res.alienNumber.startsWith("A-"));

        Immigrant im = Immigrant.getImmigrant(res.alienNumber);
        assertNotNull(im);

        assertEquals("Maria", im.getFirstName());
        assertEquals("Lopez", im.getLastName());
        assertEquals("03/15/1995", im.getDOB());
        assertEquals("Green Card", im.getImmigrationStatus());
        assertEquals("703-555-1234", im.getPhone());
        assertEquals("maria.lopez@email.com", im.getEmail());
        
        assertEquals("VA", im.getState());
        assertEquals("22201", im.getZip());
    }

    // ---------------------------------------------------------
    // PHONE TESTS
    // ---------------------------------------------------------
    @Test
    void testValidPhone_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        im.updatePhone("703-999-8888");
        assertEquals("703-999-8888", im.getPhone());
    }

    @Test
    void testInvalidPhone_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        assertThrows(IllegalArgumentException.class, () -> im.updatePhone("7039998888"));
        assertThrows(IllegalArgumentException.class, () -> im.updatePhone(""));
    }

    // ---------------------------------------------------------
    // EMAIL TESTS
    // ---------------------------------------------------------
    @Test
    void testValidEmail_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        im.updateEmail("new.email@example.com");
        assertEquals("new.email@example.com", im.getEmail());
    }

    @Test
    void testInvalidEmail_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        assertThrows(IllegalArgumentException.class, () -> im.updateEmail("notanemail"));
        assertThrows(IllegalArgumentException.class, () -> im.updateEmail(""));
    }

    // ---------------------------------------------------------
    // ADDRESS / CITY / STATE TESTS
    // ---------------------------------------------------------
    @Test
    void testValidAddressCityState_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        im.updateAddressCityState("123 New St", "Springfield", "VA");

        assertEquals("Springfield", im.getCity());
        assertEquals("VA", im.getState());
    }

    @Test
    void testInvalidAddressCityState_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        assertThrows(IllegalArgumentException.class,
                () -> im.updateAddressCityState("", "Springfield", "VA"));

        assertThrows(IllegalArgumentException.class,
                () -> im.updateAddressCityState("123 New St", "Town", "Virginia"));
    }

    // ---------------------------------------------------------
    // ZIP TESTS
    // ---------------------------------------------------------
    @Test
    void testValidZip_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        im.setZip("12345");
        assertEquals("12345", im.getZip());
    }

    @Test
    void testInvalidZip_Update() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        assertThrows(IllegalArgumentException.class, () -> im.setZip("ABCDE"));
        assertThrows(IllegalArgumentException.class, () -> im.setZip(""));
    }

    // ---------------------------------------------------------
    // RELATIVE TESTS
    // ---------------------------------------------------------
    @Test
    void testValid_GetAlienRelative() {
        Immigrant relative = new Immigrant(
                "Jane Doe", "02/02/1992", "VA", "22031",
                "Green Card", "703-555-9999", "jane@example.com"
        );

        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        im.setAlienRelative(relative);

        assertNotNull(im.getAlienRelative());
        assertEquals("Jane Doe", im.getAlienRelative().getName());
    }

    @Test
    void testInvalid_GetAlienRelative() {
        Immigrant im = new Immigrant(
                "John Doe", "01/01/1990", "VA", "22030",
                "Visitor", "703-111-2222", "john@example.com"
        );

        assertThrows(IllegalArgumentException.class, im::getAlienRelative);
    }
}