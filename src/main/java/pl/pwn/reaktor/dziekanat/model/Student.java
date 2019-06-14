package pl.pwn.reaktor.dziekanat.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;
//adnotacje sa z lomboka
//spring boot ma tomcata- srodowisko do uruchomienia aplikacji webowych

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //unikalny indentyfikator
//    @Column(unique = true)
//    private UUID index;

    @Column (name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Embedded
    private Address address;
}
