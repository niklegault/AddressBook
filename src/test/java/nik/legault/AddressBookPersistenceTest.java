package nik.legault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Spring Boot integration test for the AddressBookRepository using JUnit 4.
 * Verifies that the repository correctly persists and queries AddressBook entities.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressBookPersistenceTest {

    @Autowired
    private AddressBookRepository addressBookRepository;

    /**
     * Tests if an AddressBook and its BuddyInfo list can be saved and found by ID.
     */
    @Test
    public void testSaveAndFindById() {
        // --- Setup ---
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Jared", "123-456-7890", "985 road");
        BuddyInfo buddy2 = new BuddyInfo("Noah", "234-567-8901", "rideau");

        addressBook.getBuddies().add(buddy1);
        addressBook.getBuddies().add(buddy2);

        // --- Action ---
        AddressBook savedAddressBook = addressBookRepository.save(addressBook);
        Optional<AddressBook> foundOptional = addressBookRepository.findById(savedAddressBook.getId());

        // --- Asserts ---
        assertTrue("An address book should be found.", foundOptional.isPresent());
        AddressBook foundAddressBook = foundOptional.get();

        assertNotNull("The buddies list should not be null.", foundAddressBook.getBuddies());
        assertEquals("The address book should contain two buddies.", 2, foundAddressBook.getBuddies().size());

        String firstBuddyName = foundAddressBook.getBuddies().get(0).getName();
        assertEquals("The first buddy's name should be Jared.", "Jared", firstBuddyName);
    }

    /**
     * Tests the custom repository method to find an AddressBook by a buddy's name.
     */
    @Test
    public void testFindByBuddiesName() {
        // --- Setup ---
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Liam", "345-678-9012", "dunrobin");
        addressBook.getBuddies().add(buddy);

        addressBookRepository.save(addressBook);

        // --- Action ---
        List<AddressBook> results = addressBookRepository.findByBuddiesName("Liam");

        // --- Asserts ---
        assertNotNull("The query result should not be null.", results);
        assertEquals("The query should find one address book.", 1, results.size());

        AddressBook foundBook = results.get(0);
        assertEquals("The result should have one buddy.", 1, foundBook.getBuddies().size());
        assertEquals("The buddy's name should be Liam.", "Liam", foundBook.getBuddies().get(0).getName());
    }
}