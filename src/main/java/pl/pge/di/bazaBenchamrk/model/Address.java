package pl.pge.di.bazaBenchamrk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Embeddable
// jesli bysmy chcieli żeby klasa address była używana w kilku tabelach
// wiekszosc w standardzie w java.persistance
//hibernate i spring to szczegoly
public class Address {


    private String street;

    private String city;

}
