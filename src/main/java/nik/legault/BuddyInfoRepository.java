package nik.legault;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Spring CRUD repository for the BuddyInfo class
 *
 * @author Nik Legault 101229919
 */
@RepositoryRestResource(collectionResourceRel = "buddies", path = "buddies")
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    /**
     * Finds all the buddies with a given name
     *
     * @param name name of the buddy
     * @return List of buddies with that name
     */
    List<BuddyInfo> findByName(String name);

    /**
     * Finds a buddy based on their phone number
     *
     * @param phone phone number of the buddy
     * @return The buddy object
     */
    BuddyInfo findByPhone(String phone);

}