package nik.legault;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class models an address book that contains information about
 * your buddies
 *
 * @author Nik Legault 101229919
 */
@Entity
public class AddressBook {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany (cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<BuddyInfo> buddies;

    public  AddressBook() {
        this.buddies = new ArrayList<>();
    }

    /**
     * @return The address book ID
     */
    public Long getId() { return this.id; }

    /**
     * @param id The id to give the address book
     */
    public void setId(Long id) { this.id = id; }

    /**
     * @return List of all buddies
     */
    public List<BuddyInfo> getBuddies() { return this.buddies; }

    /**
     * @param buddies The list of buddies to set for this address book
     */
    public void setBuddies(List<BuddyInfo> buddies) { this.buddies = buddies; }

    /**
     * Add a new buddy to the address book
     *
     * @param name The name of your buddy
     * @param phone The phone number of your buddy
     * @param address The address of the buddy
     */
    public void addBuddy(String name, String phone, String address) {
        BuddyInfo newBuddy = new BuddyInfo(name, phone, address);
        this.buddies.add(newBuddy);
    }


    /**
     * Retrieve the contact of a buddy based on their name
     *
     * @param name The name of the buddy
     * @return The buddies contact info
     */
    public BuddyInfo getBuddy(String name) {
        for(BuddyInfo buddy : this.buddies) {
            if(buddy.getName().equals(name)) {
                return buddy;
            }
        }
        System.out.println("No buddy found");
        return null;
    }

    /**
     * This removes a buddy from the address book
     * @param buddy The buddy to remove
     */
    public void removeBuddy(BuddyInfo buddy) {
        this.buddies.remove(buddy);
    }

    /**
     * @return a string representation of the address book
     */
    @Override
    public String toString() {
        String string = "Address Book:\nName  |  Phone Number";
        for(BuddyInfo buddy : this.buddies) {
            string += "\n" + buddy.getName() + " | " + buddy.getPhone();
        }
        return string;
    }
}
