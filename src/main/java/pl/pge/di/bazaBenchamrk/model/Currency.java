package pl.pge.di.bazaBenchamrk.model;

public enum Currency {

    EUR("Euro",1,2019),
    USD("Dolar",1.23,2019),
    PLN("Złoty",4.34,2019);

    private String nazwa;
    private double wartosc;
    private int dataKursuWaluty;


    Currency(String nazwa, double wartosc, int dataKursuWaluty) {
        this.nazwa = nazwa;
        this.wartosc = wartosc;
        this.dataKursuWaluty = dataKursuWaluty;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getWartosc() {
        return wartosc;
    }

    public int getDataKursuWaluty() {
        return dataKursuWaluty;
    }
}
