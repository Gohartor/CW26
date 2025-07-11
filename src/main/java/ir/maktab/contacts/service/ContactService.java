package ir.maktab.contacts.service;

import ir.maktab.contacts.dto.NewContactDTO;
import ir.maktab.contacts.dto.UpdateContactDTO;
import ir.maktab.contacts.entity.Contact;
import ir.maktab.contacts.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact addContact(NewContactDTO contact) {
        Contact newContact = new Contact();
        newContact.setName(contact.getName());
        newContact.setNumber(contact.getNumber());
        return contactRepository.save(newContact);
    }

    public Contact editContact(UpdateContactDTO contact) {
        Optional<Contact> byId = contactRepository.findById(contact.getId());
        if (byId.isPresent()) {
            Contact contact1 = byId.get();
            contact1.setName(contact.getName());
            contact1.setNumber(contact.getNumber());
            return contactRepository.save(contact1);
        }
        throw new RuntimeException("Contact not found with id: " + contact.getId());
    }

    // Method that was referenced in the error
    public Contact addOrEdit(Contact contact) {
        if (contact.getId() == null) {
            // New contact
            return contactRepository.save(contact);
        } else {
            // Existing contact - update
            Optional<Contact> existingContact = contactRepository.findById(contact.getId());
            if (existingContact.isPresent()) {
                Contact updatedContact = existingContact.get();
                updatedContact.setName(contact.getName());
                updatedContact.setNumber(contact.getNumber());
                return contactRepository.save(updatedContact);
            } else {
                throw new RuntimeException("Contact not found with id: " + contact.getId());
            }
        }
    }

    public void deleteById(Long id) {
        if (!contactRepository.existsById(id)) {
            throw new RuntimeException("Contact not found with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    public Contact findById(Long id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent())
            return optionalContact.get();
        throw new RuntimeException("Contact not found with id: " + id);
    }

    public List<Contact> findAll() {
        List<Contact> all = contactRepository.findAll();
        if (all.isEmpty())
            throw new RuntimeException("No contacts found");
        return all;
    }
}
