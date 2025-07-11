package ir.maktab.contacts.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UpdateContactDTO {

    private Long id;
    private String name;
    private String number;
}
