package nik.legault;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * This represents the contact info of a buddy (name and phone #)
 *
 * @author Nik Legault 101229919
 */
@Entity
public class BuddyInfo {
    private Long id;
    private String name, phone;

    public BuddyInfo(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public BuddyInfo() {}

    /**
     * @return the buddy's id
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return this.id;
    }

    /**
     * @param id The id to give the buddy
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The buddy's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param name The buddy's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The buddy's phone number
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * @param phone phone number to set for buddy
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
