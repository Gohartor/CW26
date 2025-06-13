package ir.maktab.contacts.controller;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.service.ContactService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping
//@Controller
//@RequiredArgsConstructor
public class ContactController {

    private /*final*/ ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/by-param")
    @ResponseBody
    public ResponseEntity<Contact> addOrEdit(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.addOrEdit(contact));
    }


//    @GetMapping("/{id}")
//    public void deleteById(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(contactService.deleteById(id));
//
//    }

}
