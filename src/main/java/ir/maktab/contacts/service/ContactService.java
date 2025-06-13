package ir.maktab.contacts.service;



import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;


   public Contact addOrEdit(Contact contact){
     return contactRepository.create(contact);
    }

    public void deleteById(Long id){
        contactRepository.removeById(id);
    }
    public Contact findById(Long id){
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent())
            return optionalContact.get();
        throw new RuntimeException();
    }

    public Set<Contact> findAll(){
        Set<Contact> all = contactRepository.findAll();
        if (all.isEmpty())
            throw new RuntimeException();
        return all;
    }
}
