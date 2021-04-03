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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ITist iTist = (ITist) o;
        return Objects.equals(servere, iTist.servere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), servere);
    }

    @Override
    public String toString() {
        return super.toString() + "ITist{" +
                "servere=" + servere +
                '}';
    }
}
