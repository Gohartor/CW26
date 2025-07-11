package ir.maktab.contacts.controller;

import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.dto.UpdateContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> addContact(@RequestBody NewContactDTO contact) {
        System.out.println(contact);
        return ResponseEntity.ok(contactService.addContact(contact));
    }

    @PutMapping("/update")
    public ResponseEntity<Contact> editContact(@RequestBody UpdateContactDTO contact) {
        return ResponseEntity.ok(contactService.editContact(contact));
    }

    // Endpoint that was referenced in the error
    @PostMapping("/by-param")
    public ResponseEntity<Contact> addOrEdit(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.addOrEdit(contact));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteById(id);
        return ResponseEntity.ok("Contact has been deleted");
    }

    @GetMapping("/show-all-contacts")
    public ResponseEntity<List<Contact>> showAllContacts() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/show-contact/{id}")
    public ResponseEntity<Contact> showContact(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }
}
