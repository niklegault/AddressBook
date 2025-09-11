package nik.legault;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for the AddressBook class.
 */
public class AddressBookTest {

    private AddressBook addressBook;

    /**
     * Set up a new, empty AddressBook instance before each test method runs.
     * This ensures test independence.
     */
    @Before
    public void setUp() {
        addressBook = new AddressBook();
    }

    /**
     * Test that a newly created AddressBook is empty.
     * It should not contain any buddies, and getBuddy should return null.
     */
    @Test
    public void testInitialStateIsEmpty() {
        assertNull("Getting a buddy from an empty address book should return null.", addressBook.getBuddy("NonExistentBuddy"));
    }

    /**
     * Test adding a single buddy to the address book and then retrieving them.
     * Verifies that the retrieved buddy's details match the added details.
     */
    @Test
    public void testAddAndGetBuddy() {
        // Add a new buddy
        addressBook.addBuddy("Alice", "123-456-7890");

        // Retrieve the added buddy
        BuddyInfo retrievedBuddy = addressBook.getBuddy("Alice");

        // Assertions
        assertNotNull("Retrieved buddy should not be null.", retrievedBuddy);
        assertEquals("Name of retrieved buddy should match.", "Alice", retrievedBuddy.getName());
        assertEquals("Phone number of retrieved buddy should match.", "123-456-7890", retrievedBuddy.getPhone());
    }

    /**
     * Test attempting to retrieve a buddy that does not exist in the address book.
     * Ensures getBuddy correctly returns null for non-existent names.
     */
    @Test
    public void testGetBuddyNotFound() {
        // Add a different buddy to ensure the book isn't empty
        addressBook.addBuddy("Bob", "987-654-3210");

        // Attempt to retrieve a buddy who hasn't been added
        BuddyInfo retrievedBuddy = addressBook.getBuddy("Charlie");

        // Assertion
        assertNull("Searching for a non-existent buddy should return null.", retrievedBuddy);
    }

    /**
     * Test adding multiple buddies and verifying their retrieval.
     */
    @Test
    public void testAddMultipleBuddies() {
        addressBook.addBuddy("Alice", "111-1111");
        addressBook.addBuddy("Bob", "222-2222");

        BuddyInfo alice = addressBook.getBuddy("Alice");
        BuddyInfo bob = addressBook.getBuddy("Bob");

        assertNotNull("Alice should be found.", alice);
        assertEquals("Bob's phone number should be correct.", "222-2222", bob.getPhone());
    }

    /**
     * Test removing a buddy from the address book.
     * After removal, getBuddy should return null for the removed buddy.
     */
    @Test
    public void testRemoveBuddy() {
        // Setup: Add a buddy to remove
        addressBook.addBuddy("Charlie", "333-333-3333");
        BuddyInfo charlie = addressBook.getBuddy("Charlie");

        // Pre-condition check: ensure buddy exists before removal
        assertNotNull("Charlie should exist before removal.", charlie);

        // Action: Remove the buddy
        addressBook.removeBuddy(charlie);

        // Post-condition check: ensure buddy no longer exists
        assertNull("Charlie should be null after removal.", addressBook.getBuddy("Charlie"));
    }

    /**
     * Test the string representation of an empty address book.
     */
    @Test
    public void testToStringEmptyBook() {
        String expected = "Address Book:\nName  |  Phone Number";
        assertEquals("toString() output for empty book is incorrect.", expected, addressBook.toString());
    }

    /**
     * Test the string representation of an address book with multiple buddies.
     * Checks if the output contains the correct information for each buddy.
     */
    @Test
    public void testToStringWithBuddies() {
        addressBook.addBuddy("Dave", "444-4444");
        addressBook.addBuddy("Eve", "555-5555");

        String result = addressBook.toString();

        // Use contains for flexibility, as exact string matching with newlines can be brittle.
        assertTrue("toString() should contain Dave's information.", result.contains("Dave | 444-4444"));
        assertTrue("toString() should contain Eve's information.", result.contains("Eve | 555-5555"));
        assertTrue("toString() should start with the header.", result.startsWith("Address Book:"));
    }
}