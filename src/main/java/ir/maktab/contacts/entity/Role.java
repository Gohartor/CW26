package ir.maktab.contacts.entity;

import ir.maktab.contacts.entity.Authority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


public class Role {

    public static final String TABLE_NAME = "tbl_role";

    public static final String NAME = "name";

    @Column(name = NAME)
    private String name;

    @ManyToMany
    private Set<Authority> authorities = new HashSet<>();

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
