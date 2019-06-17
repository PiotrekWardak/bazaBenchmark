package pl.pge.di.bazaBenchamrk.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SzczegoloweDane")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "technology")
    private String technology;
    private String overnight;
    private String capexNaRok;
    private String capexRaportNaMW;
    private String rokRaportu;
    private String waluta;
    private String capexWybranyRokkEurNaMW;
    private String fixedOmNaMW;
    private String fixedOmWybranyRokKeurNaMWyr;
    private String variableOmInMWh;
    private String variableOmWybranyRokEurNaMWh;
    private String lcoe;
    private String lcoeWybranyRokEurNaMWh;
    private String planowanieBudowyLata;
    private String budowaLata;
    private String okresEksploatacjiLata;
    private String raportID;
    private String realEscalatorsPercent;
    private String czasPracyPercent;
    private String stopaDyskontaPercent;

    public Survey(String technology , String overnight, String capexNaRok, String capexRaportNaMW,  String rokRaportu, String waluta, String capexWybranyRokkEurNaMW,
                  String fixedOmNaMW,String fixedOmWybranyRokKeurNaMWyr,String variableOmInMWh,String variableOmWybranyRokEurNaMWh,String lcoe, String lcoeWybranyRokEurNaMWh,
                  String planowanieBudowyLata,String budowaLata,String okresEksploatacjiLata,String raportID,String realEscalatorsPercent,String czasPracyPercent, String stopaDyskontaPercent ) {
        this.technology = technology;
        this.overnight = overnight;
        this.capexNaRok = capexNaRok;
        this.capexRaportNaMW = capexRaportNaMW;
        this.rokRaportu = rokRaportu;
        this.waluta = waluta;
        this.capexWybranyRokkEurNaMW = capexWybranyRokkEurNaMW;
        this.fixedOmNaMW = fixedOmNaMW;
        this.fixedOmWybranyRokKeurNaMWyr = fixedOmWybranyRokKeurNaMWyr;
        this.variableOmInMWh = variableOmInMWh;
        this.variableOmWybranyRokEurNaMWh = variableOmWybranyRokEurNaMWh;
        this.lcoe = lcoe;
        this.lcoeWybranyRokEurNaMWh = lcoeWybranyRokEurNaMWh;
        this.planowanieBudowyLata = planowanieBudowyLata;
        this.budowaLata = budowaLata;
        this.okresEksploatacjiLata = okresEksploatacjiLata;
        this.raportID = raportID;
        this.realEscalatorsPercent = realEscalatorsPercent;
        this.czasPracyPercent = czasPracyPercent;
        this.stopaDyskontaPercent = stopaDyskontaPercent;
    }
}
