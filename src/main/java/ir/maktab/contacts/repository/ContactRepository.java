package ir.maktab.contacts.repository;


import ir.maktab.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface ContactRepository extends JpaRepository<Contact, Long> {



//    Contact create(Contact contact);
//    void  removeById(Long id);
//    Optional<Contact> findById(Long id);
//    Set<Contact> findAll();
}
