package ir.maktab.contacts.repository;


import ir.maktab.contacts.entity.Contact;

import java.util.Optional;
import java.util.Set;

public interface ContactRepository {
    Contact create(Contact contact);
    void  removeById(Long id);
    Optional<Contact> findById(Long id);
    Set<Contact> findAll();
}
