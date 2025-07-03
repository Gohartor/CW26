package ir.maktab.contacts.repository;


import ir.maktab.contacts.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {



    // @Modifying -> for delete and update
    @Query("select c from Contact c where c.name = :name")
    Contact findByName(@Param("name") String name);

    @Query("select c from Contact c where c.number = :number")
    Contact findByNumber(String number);
//    Contact create(Contact contact);
//    void  removeById(Long id);
//    Optional<Contact> findById(Long id);
//    Set<Contact> findAll();
}
