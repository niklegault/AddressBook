package nik.legault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

/**
 * Spring Boot integration test for the BuddyInfoRepository using JUnit 4.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BuddyInfoPersistenceTest {

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    /**
     * Tests if a single BuddyInfo entity can be saved and then found by its ID.
     */
    @Test
    public void testSaveAndFindBuddy() {
        // --- Action ---
        BuddyInfo buddy = new BuddyInfo("Jared", "123-456-7890");
        BuddyInfo savedBuddy = buddyInfoRepository.save(buddy);

        // Use the repository to find the buddy by its generated ID
        Optional<BuddyInfo> foundOptional = buddyInfoRepository.findById(savedBuddy.getId());

        // --- Asserts ---
        assertTrue("The found buddy should not be empty.", foundOptional.isPresent());
        assertEquals("The name should match.", "Jared", foundOptional.get().getName());
    }

    /**
     * Tests saving multiple BuddyInfo entities and retrieving them all.
     */
    @Test
    public void testFindAllMultipleBuddies() {
        // --- Action ---
        buddyInfoRepository.saveAll(Arrays.asList(
                new BuddyInfo("Noah", "234-567-8901"),
                new BuddyInfo("Liam", "345-678-9012")
        ));

        // Use the repository's built-in findAll method
        Iterable<BuddyInfo> resultsIterable = buddyInfoRepository.findAll();

        // Convert the Iterable to a List for easier assertions
        List<BuddyInfo> results = StreamSupport.stream(resultsIterable.spliterator(), false)
                .collect(Collectors.toList());

        // --- Asserts ---
        assertNotNull("The result list should not be null.", results);
        assertEquals("The list should contain two buddies.", 2, results.size());
    }
}