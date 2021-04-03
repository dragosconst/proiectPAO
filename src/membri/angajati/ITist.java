package membri.angajati;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ITist extends Angajat{
    public List<String> servere;

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
}
