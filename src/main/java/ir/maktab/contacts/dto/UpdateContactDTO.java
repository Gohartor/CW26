package ir.maktab.contacts.dto;


import lombok.experimental.FieldDefaults;


public class UpdateContactDTO {

    private Long id;
    private String name;
    private String number;

    public UpdateContactDTO(Long id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public UpdateContactDTO() {
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

    @Override
    public String toString() {
        return "UpdateContactDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
