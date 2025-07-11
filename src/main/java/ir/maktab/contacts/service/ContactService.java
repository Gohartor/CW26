package ir.maktab.contacts.service;



import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.dto.UpdateContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.repository.ContactRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class ContactService {
    private /*final*/ ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


   public Contact addContact(NewContactDTO contact){
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setNumber(contact.getNumber());
     return contactRepository.save(newContact);
    }


    public Contact editContact(UpdateContactDTO contact){
        Optional<Contact> byId = contactRepository.findById(contact.getId());
        if (byId.isPresent()) {
            Contact contact1 = byId.get();
            contact1.setName(contact.getName());
            contact1.setNumber(contact.getNumber());
            return contactRepository.save(contact1);
        }
        throw new RuntimeException();
    }

    public void deleteById(Long id){
        contactRepository.deleteById(id);
    }


    public Contact findById(Long id){
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent())
            return optionalContact.get();
        throw new RuntimeException();
    }

    public List<Contact> findAll(){
        List<Contact> all = contactRepository.findAll();
        if (all.isEmpty())
            throw new RuntimeException();
        return all;
    }


}
