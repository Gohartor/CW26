package ir.maktab.contacts.entity;

import jakarta.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;

    @ManyToOne
    private User user;

    // Default constructor required by JPA
    public Contact() {
    }

    // Constructor with name and number
    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    // Constructor with all fields
    public Contact(String name, String number, User user) {
        this.name = name;
        this.number = number;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Contact{name='" + name + "', number='" + number + "'}";
    }
}
