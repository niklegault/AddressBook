package nik.legault;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AddressBookViewController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping("addressBooks/{id}")
    public String viewAddressBook(@PathVariable Long id, Model model) {
        Optional<AddressBook> addressBookOptional = addressBookRepository.findById(id);

        if(addressBookOptional.isPresent()) {
            model.addAttribute("addressBook", addressBookOptional.get());
            return "addressBookView";
        }

        return "error";
    }

    @PostMapping("addressBooks")
    public String createAddressBook(Model model) {
        AddressBook newBook = new AddressBook();
        addressBookRepository.save(newBook);
        return "redirect:/addressBooks/" + newBook.getId().toString();
    }

    @GetMapping("/")
    public String mainView(Model model) {
        Iterable<AddressBook> addressBooksIterable = addressBookRepository.findAll();
        model.addAttribute("allAddressBooks", addressBooksIterable);
        return "mainAppView";
    }

}
