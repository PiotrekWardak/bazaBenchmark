package pl.pge.di.bazaBenchamrk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "report")
@AllArgsConstructor
@NoArgsConstructor
public class Report_1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "tytul")
    private String tytulRaportu;

    @Column(name = "autor")
    private String autorRaportu;

    @Column(name = "raport")
    private String dataRaportu;

    public Report_1(String tytulRaportu, String autorRaportu, String dataRaportu) {
        this.tytulRaportu = tytulRaportu;
        this.autorRaportu = autorRaportu;
        this.dataRaportu = dataRaportu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}