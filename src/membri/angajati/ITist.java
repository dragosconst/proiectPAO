package membri.angajati;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ITist extends Angajat{
    public List<String> servere;
    public static final double IT_com = 0.2;

    public ITist(String nume, String prenume, String statut, Date dataInscriere, Double salariu, List<String> servere) {
        super(nume, prenume, statut, dataInscriere, salariu);
        this.servere = servere;
    }

    public ITist(String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission, List<String> servere) {
        super(nume, prenume, statut, dataInscriere, salariu, commission);
        this.servere = servere;
    }

    public List<String> getServere() {
        return servere;
    }

    public void setServere(List<String> servere) {
        this.servere = servere;
    }

    @Override
    public String toString() {
        return super.toString() + "ITist{" +
                "servere=" + servere +
                '}';
    }

    // ITistii au un boost de salariu separat
    @Override
    public Double totalIncome() {
        Double regIncome = super.totalIncome();
        return regIncome + regIncome * IT_com;
    }
}
