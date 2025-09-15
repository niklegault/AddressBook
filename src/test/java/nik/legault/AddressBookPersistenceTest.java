package nik.legault;

import jakarta.persistence.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for AddressBook JPA persistence.
 * Verifies that AddressBook entities and their relationships are correctly managed.
 */
public class AddressBookPersistenceTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    /**
     * Set up the EntityManagerFactory before any tests run.
     */
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("contacts");
    }

    /**
     * Tear down the EntityManagerFactory after all tests have finished.
     */
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    /**
     * Set up the EntityManager and begin a transaction before each test.
     * This ensures each test runs in isolation.
     */
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }

    /**
     * Rollback the transaction and close the EntityManager after each test.
     * Rolling back ensures the database is clean for the next test.
     */
    @After
    public void tearDown() {
        if (tx.isActive()) {
            tx.rollback();
        }
        em.close();
    }

    /**
     * Tests if an AddressBook with its BuddyInfo list can be persisted and found.
     */
    @Test
    public void testPersistAndFindAddressBook() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Jared", "123-456-7890");
        BuddyInfo buddy2 = new BuddyInfo("Noah", "234-567-8901");

        addressBook.addBuddy(buddy1.getName(), buddy1.getPhone());
        addressBook.addBuddy(buddy2.getName(), buddy2.getPhone());

        em.persist(addressBook);
        tx.commit();

        tx.begin();
        AddressBook foundAddressBook = em.find(AddressBook.class, addressBook.getId());

        assertNotNull("The found address book should not be null.", foundAddressBook);
        assertNotNull("The buddies list should not be null.", foundAddressBook.getBuddies());
        assertEquals("The address book should contain two buddies.", 2, foundAddressBook.getBuddies().size());

        String firstBuddyName = foundAddressBook.getBuddies().get(0).getName();
        assertEquals("The first buddy's name should be Jared.", "Jared", firstBuddyName);
    }

    /**
     * Tests querying for a single AddressBook from the database.
     */
    @Test
    public void testQuerySingleAddressBook() {
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy("Liam", "345-678-9012");
        em.persist(addressBook);
        tx.commit();

        tx.begin();
        Query q = em.createQuery("SELECT a FROM AddressBook a WHERE a.id = :id");
        q.setParameter("id", addressBook.getId());

        AddressBook result = (AddressBook) q.getSingleResult();

        assertNotNull("The query result should not be null.", result);
        assertEquals("The result should have one buddy.", 1, result.getBuddies().size());
        assertEquals("The buddy's name should be Liam.", "Liam", result.getBuddies().get(0).getName());
    }
}