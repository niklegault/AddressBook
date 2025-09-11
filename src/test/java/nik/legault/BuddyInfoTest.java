package nik.legault;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the BuddyInfo class.
 * Verifies that the constructor and getters work as expected.
 */
public class BuddyInfoTest {

    /**
     * Tests the constructor and getter methods with standard, non-empty values.
     * Ensures that the values provided during construction are correctly returned by getName() and getPhone().
     */
    @Test
    public void testConstructorAndGetters() {
        // Create a new BuddyInfo instance with test data
        BuddyInfo buddy = new BuddyInfo("John Doe", "555-1234");

        // Verify that the getName() method returns the correct name
        assertEquals("Name should match the value set in constructor.", "John Doe", buddy.getName());

        // Verify that the getPhone() method returns the correct phone number
        assertEquals("Phone number should match the value set in constructor.", "555-1234", buddy.getPhone());
    }

    /**
     * Tests the handling of empty strings by the constructor and getters.
     * The class should store and return empty strings if provided.
     */
    @Test
    public void testConstructorWithEmptyStrings() {
        BuddyInfo buddy = new BuddyInfo("", "");

        assertEquals("Name should be an empty string.", "", buddy.getName());
        assertEquals("Phone number should be an empty string.", "", buddy.getPhone());
    }

    /**
     * Tests the handling of null values by the constructor and getters.
     * Ensures that null values are stored and retrieved correctly without causing errors.
     */
    @Test
    public void testConstructorWithNullValues() {
        BuddyInfo buddy = new BuddyInfo(null, null);

        assertNull("Name should be null if set to null in constructor.", buddy.getName());
        assertNull("Phone number should be null if set to null in constructor.", buddy.getPhone());
    }

    /**
     * Tests a different set of data to ensure reliability with various inputs.
     */
    @Test
    public void testAnotherBuddyInstance() {
        BuddyInfo buddy = new BuddyInfo("Jane Smith", "987-654-3210");

        assertEquals("Name mismatch for second instance.", "Jane Smith", buddy.getName());
        assertEquals("Phone number mismatch for second instance.", "987-654-3210", buddy.getPhone());
    }
}