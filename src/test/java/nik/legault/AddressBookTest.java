package nik.legault;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the AddressBook entity class.
 */
public class AddressBookTest {

    private AddressBook addressBook;

    @Before
    public void setUp() {
        addressBook = new AddressBook();
    }

    /**
     * Test adding a single buddy and retrieving it by name.
     */
    @Test
    public void testAddAndGetBuddyByName() {
        addressBook.addBuddy("Alice", "123-456-7890", "road");
        BuddyInfo retrievedBuddy = addressBook.getBuddy("Alice");

        assertNotNull("Retrieved buddy should not be null.", retrievedBuddy);
        assertEquals("Name should be 'Alice'.", "Alice", retrievedBuddy.getName());
    }

    /**
     * Test removing a buddy from the address book.
     */
    @Test
    public void testRemoveBuddy() {
        addressBook.addBuddy("Charlie", "333-3333", "street");
        BuddyInfo charlie = addressBook.getBuddy("Charlie");
        assertNotNull("Charlie should exist before removal.", charlie);

        addressBook.removeBuddy(charlie);
        assertNull("Charlie should be null after removal.", addressBook.getBuddy("Charlie"));
    }

    /**
     * Tests the setter and getter for the 'id' property.
     */
    @Test
    public void testSetAndGetId() {
        addressBook.setId(1L);
        assertEquals("ID should match the value set.", Long.valueOf(1), addressBook.getId());
    }

    /**
     * Tests replacing the entire list of buddies using setBuddies.
     */
    @Test
    public void testSetAndGetBuddies() {
        // Create a new list of buddies
        ArrayList<BuddyInfo> newBuddies = new ArrayList<>();
        newBuddies.add(new BuddyInfo("Dave", "444-4444", "ktown"));
        newBuddies.add(new BuddyInfo("Eve", "555-5555", "ave"));

        // Set this new list in the address book
        addressBook.setBuddies(newBuddies);

        // Verify the list was replaced
        assertEquals("The size of the buddies list should be 2.", 2, addressBook.getBuddies().size());
        assertEquals("The first buddy in the list should be Dave.", "Dave", addressBook.getBuddies().get(0).getName());
        assertNull("Alice should not exist in the new list.", addressBook.getBuddy("Alice"));
    }

    /**
     * Test the string representation of an address book with buddies.
     */
    @Test
    public void testToStringWithBuddies() {
        addressBook.addBuddy("Frank", "666-6666", "road");
        String result = addressBook.toString();
        assertTrue("toString() should contain Frank's info.", result.contains("Frank | 666-6666 |  road"));
    }
}