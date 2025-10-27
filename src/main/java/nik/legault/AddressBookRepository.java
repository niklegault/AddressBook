package nik.legault;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Spring CRUD repository for the AddressBook class
 *
 * @author Nik Legault 101229919
 */
@RepositoryRestResource(collectionResourceRel = "addressBooks", path = "addressBooks")
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {

    /**
     * Returns a list of buddies with a given name
     *
     * @param name name of the buddy(ies)
     * @return List of the buddies with that name
     */
    List<AddressBook> findByBuddiesName(String name);

}