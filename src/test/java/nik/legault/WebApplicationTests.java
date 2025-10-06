package nik.legault;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

/**
 * Tests for HTTP interactions
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AddressBookViewController.class)
public class WebApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookRepository addressBookRepository;

    /**
     * Tests if the controller returns the address book view when a valid ID is provided.
     */
    @Test
    public void testViewAddressBook() throws Exception {
        AddressBook addressBook = new AddressBook();
        addressBook.setId(1L);
        BuddyInfo buddy = new BuddyInfo("John Doe", "555-1234", "road");
        addressBook.addBuddy(buddy.getName(), buddy.getPhone(), buddy.getAddress());

        when(addressBookRepository.findById(1L)).thenReturn(Optional.of(addressBook));

        this.mockMvc.perform(get("/addressBook/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("addressBookView"))
                .andExpect(model().attribute("addressBook", addressBook))
                .andExpect(content().string(containsString("John Doe")))
                .andExpect(content().string(containsString("555-1234")));
    }

    /**
     * Tests if the controller returns the error view when an invalid ID is provided.
     */
    @Test
    public void testViewAddressBookNotFound() throws Exception {
        when(addressBookRepository.findById(2L)).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/addressBook/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }
}