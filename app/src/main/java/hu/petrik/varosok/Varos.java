package hu.petrik.varosok;

public class Varos {
    private int id;
    private String nev;
    private String orszag;
    private int lakossag;

    public Varos(int id, String varos, String orszag, int lakossag) {
        this.id = id;
        this.nev = varos;
        this.orszag = orszag;
        this.lakossag = lakossag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getOrszag() {
        return orszag;
    }

    public void setOrszag(String orszag) {
        this.orszag = orszag;
    }

    public int getLakossag() {
        return lakossag;
    }

    public void setLakossag(int lakossag) {
        this.lakossag = lakossag;
    }
}
