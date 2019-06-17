package pl.pge.di.bazaBenchamrk.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private int id;

    private String login;


    private boolean active;

    private String firstName;

    private String lastName;

    private String street;

    private String city;

}
