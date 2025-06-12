package ir.maktab.contacts.controller;


import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContactController {
    private ContactService contactService;
    @GetMapping
    @ResponseBody
    public Contact addOrEdit(Contact contact){
        return contactService.addOrEdit(contact);
    }
}
