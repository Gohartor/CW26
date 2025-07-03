package ir.maktab.contacts.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewContactDTO {

    String name;

    String number;

    public NewContactDTO(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public NewContactDTO() {
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
        return "NewContactDTO{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
