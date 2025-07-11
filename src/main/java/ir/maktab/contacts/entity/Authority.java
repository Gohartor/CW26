package ir.maktab.contacts.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {

    public static final String TABLE_NAME = "tbl_authorities";
    public static final String NAME = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = NAME, unique = true)
    private String name;

    public Authority() {
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
}
