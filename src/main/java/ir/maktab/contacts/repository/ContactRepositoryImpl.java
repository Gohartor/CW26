package ir.maktab.contacts.repository;

import ir.maktab.contacts.entity.Contact;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor

public class ContactRepositoryImpl implements ContactRepository {

   private final EntityManager entityManager;


    @Override
    public Contact create(Contact contact) {
        return entityManager.merge(contact);
    }

    @Override
    public void removeById(Long id) {
       String query="delete from Contact c where c.id=: id";
       entityManager.createQuery(query).setParameter("id",id);
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String query="select c from Contact c where c.id=: id";
        Contact result = entityManager.createQuery(query, Contact.class).setParameter("id", id).getSingleResult();
        return Optional.of(result);
    }

    @Override
    public Set<Contact> findAll() {
        String query="select c from Contact c ";
        List<Contact> resultList = entityManager.createQuery(query, Contact.class).getResultList();
        return new HashSet<>(resultList);
    }
}
