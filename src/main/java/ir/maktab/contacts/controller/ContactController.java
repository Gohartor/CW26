package ir.maktab.contacts.controller;


import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
//@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

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
