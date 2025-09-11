package nik.legault;

import java.util.ArrayList;

/**
 * This class models an address book that contains information about
 * your buddies
 *
 * @author Nik Legault 101229919
 */
public class AddressBook {
    private ArrayList<BuddyInfo> buddies;

    public  AddressBook() {
        this.buddies = new ArrayList<>();
    }

    /**
     * Add a new buddy to the address book
     *
     * @param name The name of your buddy
     * @param phone The phone number of your buddy
     */
    public void addBuddy(String name, String phone) {
        BuddyInfo newBuddy = new BuddyInfo(name, phone);
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
