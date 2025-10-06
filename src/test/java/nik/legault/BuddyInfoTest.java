package nik.legault;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the BuddyInfo entity class.
 */
public class BuddyInfoTest {

    /**
     * Tests the constructor and initial getter methods with standard values.
     */
    @Test
    public void testConstructorAndGetters() {
        BuddyInfo buddy = new BuddyInfo("John Doe", "555-1234", "road");
        assertEquals("Name should match the value set in constructor.", "John Doe", buddy.getName());
        assertEquals("Phone should match the value set in constructor.", "555-1234", buddy.getPhone());
        assertEquals("Address should match the value set in constructor.", "road", buddy.getAddress());
    }

    /**
     * Tests the no-argument constructor required by JPA.
     * Ensures an object is created with null fields.
     */
    @Test
    public void testNoArgsConstructor() {
        BuddyInfo buddy = new BuddyInfo();
        assertNotNull("Buddy object should not be null.", buddy);
        assertNull("Name should be null initially.", buddy.getName());
        assertNull("Phone should be null initially.", buddy.getPhone());
        assertNull("Address should be null initially.", buddy.getAddress());
    }

    /**
     * Tests the setter and getter for the 'name' property.
     */
    @Test
    public void testSetName() {
        BuddyInfo buddy = new BuddyInfo();
        buddy.setName("Jane Smith");
        assertEquals("Name should be updated by the setter.", "Jane Smith", buddy.getName());
    }

    /**
     * Tests the setter and getter for the 'phone' property.
     */
    @Test
    public void testSetPhone() {
        BuddyInfo buddy = new BuddyInfo();
        buddy.setPhone("987-654-3210");
        assertEquals("Phone should be updated by the setter.", "987-654-3210", buddy.getPhone());
    }

    /**
     * Tests the setter and getter for the 'address' property
     */
    @Test
    public void testSetAddress() {
        BuddyInfo buddy = new BuddyInfo();
        buddy.setAddress("road");
        assertEquals("Address should be updated by the setter.", "road", buddy.getAddress());
    }

    /**
     * Tests the setter and getter for the 'id' property.
     */
    @Test
    public void testSetAndGetId() {
        BuddyInfo buddy = new BuddyInfo();
        buddy.setId(101L); // 'L' denotes a Long literal
        assertEquals("ID should match the value set.", Long.valueOf(101), buddy.getId());
    }
}