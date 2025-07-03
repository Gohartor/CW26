package ir.maktab.contacts.controller;



import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.service.ContactService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/contact")
//@Controller
//@RequiredArgsConstructor
public class ContactController {

    private /*final*/ ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/by-param")
//    @ResponseBody
    public ResponseEntity<Contact> addOrEdit(@RequestBody NewContactDTO contact) {
        System.out.println(contact);
        return ResponseEntity.ok(contactService.addOrEdit(contact));
    }


//    @GetMapping("/{id}")
//    public void deleteById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(contactService.deleteById(id));
//
//    }

}
