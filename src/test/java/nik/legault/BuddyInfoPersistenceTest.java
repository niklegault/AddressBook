package nik.legault;

import jakarta.persistence.*;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;

/**
 * JUnit 4 tests for BuddyInfo JPA persistence.
 */
public class BuddyInfoPersistenceTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    /**
     * Creates the EntityManagerFactory once for the entire test class.
     */
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("contacts");
    }

    /**
     * Closes the EntityManagerFactory after all tests are complete.
     */
    @AfterClass
    public static void tearDownClass() {
        emf.close();
    }

    /**
     * Creates an EntityManager and starts a transaction before each test.
     */
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }

    /**
     * Commits the test's transaction, then starts a new one to delete all
     * BuddyInfo records, ensuring the database is empty for the next test.
     */
    @After
    public void tearDown() {
        if (tx.isActive()) {
            tx.commit();
        }

        tx.begin();
        em.createQuery("DELETE FROM BuddyInfo").executeUpdate();
        tx.commit();

        em.close();
    }

    /**
     * Tests if a single BuddyInfo entity can be persisted and then found by its ID.
     */
    @Test
    public void testPersistAndFindBuddy() {
        BuddyInfo buddy = new BuddyInfo("Jared", "123-456-7890");
        em.persist(buddy);

        em.flush();

        BuddyInfo foundBuddy = em.find(BuddyInfo.class, buddy.getId());

        assertNotNull("The found buddy should not be null.", foundBuddy);
        assertEquals("The name should match.", "Jared", foundBuddy.getName());
    }

    /**
     * Tests persisting multiple BuddyInfo entities and retrieving them with a query.
     */
    @Test
    public void testQueryMultipleBuddies() {
        em.persist(new BuddyInfo("Noah", "234-567-8901"));
        em.persist(new BuddyInfo("Liam", "345-678-9012"));

        em.flush();

        Query q = em.createQuery("SELECT b FROM BuddyInfo b");
        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        assertNotNull("The result list should not be null.", results);
        assertEquals("The list should contain two buddies.", 2, results.size());
    }
}