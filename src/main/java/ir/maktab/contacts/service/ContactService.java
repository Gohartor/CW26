package ir.maktab.contacts.service;



import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
//@RequiredArgsConstructor
public class ContactService {
    private /*final*/ ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


   public Contact addOrEdit(NewContactDTO contact){
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setNumber(contact.getNumber());
     return contactRepository.save(newContact);
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
