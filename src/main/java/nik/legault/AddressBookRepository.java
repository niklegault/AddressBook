package nik.legault;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Spring CRUD repository for the AddressBook class
 *
 * @author Nik Legault 101229919
 */
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

    /**
     * Returns a list of buddies with a given name
     *
     * @param name name of the buddy(ies)
     * @return List of the buddies with that name
     */
    List<AddressBook> findByBuddiesName(String name);

}