package nik.legault;

/**
 * This represents the contact info of a buddy (name and phone #)
 *
 * @author Nik Legault 101229919
 */
public class BuddyInfo {
    private String name, phone;

    public BuddyInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * @return The buddy's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The buddy's phone number
     */
    public String getPhone() {
        return this.phone;
    }
}
