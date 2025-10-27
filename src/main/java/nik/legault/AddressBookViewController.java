package nik.legault;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AddressBookViewController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private BuddyInfoRepository buddyInfoRepository;

    @GetMapping("/addressBooks/{id}")
    public String viewAddressBook(@PathVariable Long id, Model model, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);

        if(addressBookOptional.isPresent()) {
            model.addAttribute("addressBook", addressBookOptional.get());
            model.addAttribute("buddyInfo", new BuddyInfo());
            return "addressBookView";
        }

        return "error";
    }

    @PostMapping("/addressBooks")
    public String createAddressBook(Model model) {
        AddressBook newBook = new AddressBook();
        addressBookRepository.save(newBook);
        return "redirect:/addressBooks/" + newBook.getId().toString();
    }

    @GetMapping("/")
    public String mainView(Model model) {
        Iterable<AddressBook> addressBooksIterable = addressBookRepository.findAll();
        model.addAttribute("allAddressBooks", addressBooksIterable);
        model.addAttribute("addressBook", new AddressBook());
        return "mainAppView";
    }

    @PostMapping("/addressBooks/{id}/buddies")
    public String createAndAddBuddy(@PathVariable Long id, @ModelAttribute BuddyInfo buddyInfo, Model model) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);
        if(addressBookOptional.isPresent()) {
            AddressBook addressBook =  addressBookOptional.get();
            addressBook.addBuddy(buddyInfo.getName(), buddyInfo.getPhone(), buddyInfo.getAddress());
            addressBookRepository.save(addressBook);
            return "redirect:/addressBooks/" + id + "?buddyAdded=true&buddyId=" + buddyInfo.getId();
        }

        return "error";
    }

}
