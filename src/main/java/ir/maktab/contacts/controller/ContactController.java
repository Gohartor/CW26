package ir.maktab.contacts.controller;



import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.dto.UpdateContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.entity.User;
import ir.maktab.contacts.service.ContactService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/contact")
//@Controller
//@RequiredArgsConstructor
public class ContactController {

    private /*final*/ ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/add")
//    @ResponseBody
    public ResponseEntity<Contact> addContact(@RequestBody NewContactDTO contact) {
        System.out.println(contact);
        return ResponseEntity.ok(contactService.addContact(contact));
    }


    @PutMapping("/update")
    public ResponseEntity<Contact> editContact(@RequestBody UpdateContactDTO contact) {
        return ResponseEntity.ok(contactService.editContact(contact));
    }


    //@PathVariable -> single
    //@RequestParam -> list, single
    //@RequestBody -> instance, list, single

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable("id") Long id) {
        contactService.deleteById(id);
        return ResponseEntity.ok("Contact has been deleted");
    }


    @GetMapping("/show-all-contatcs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Contact>> showAllContacts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(contactService.findAll());
    }


    @GetMapping("/show-contact/{id}")
    public ResponseEntity<Contact> showContact(@PathVariable("id") Long id) {
        return ResponseEntity.ok(contactService.findById(id));
    }



//    @GetMapping("/{id}")
//    public void deleteById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(contactService.deleteById(id));
//
//    }

}
