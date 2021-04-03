package membri.angajati;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Bibliotecar extends Angajat{
    private List<Integer> sectiuni;
    private Date dataNasterii;

    public Bibliotecar(String nume, String prenume, String statut, Date dataInscriere, Double salariu) {
        super(nume, prenume, statut, dataInscriere, salariu);
        this.sectiuni = new ArrayList<>();
        this.dataNasterii = new Date(0);
    }

    public Bibliotecar(String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission) {
        super(nume, prenume, statut, dataInscriere, salariu, commission);
        this.sectiuni = new ArrayList<>();
        this.dataNasterii = new Date(0);
    }

    public Bibliotecar(String nume, String prenume, String statut, Date dataInscriere, Double salariu, List<Integer> sectiuni, Date dataNasterii) {
        super(nume, prenume, statut, dataInscriere, salariu);
        this.sectiuni = sectiuni;
        this.dataNasterii = dataNasterii;
    }

    public Bibliotecar(String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission, List<Integer> sectiuni, Date dataNasterii) {
        super(nume, prenume, statut, dataInscriere, salariu, commission);
        this.sectiuni = sectiuni;
        this.dataNasterii = dataNasterii;
    }

    public List<Integer> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(List<Integer> sectiuni) {
        this.sectiuni = sectiuni;
    }

    public Date getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(Date dataNasterii) {
        this.dataNasterii = dataNasterii;
    }


    @Override
    public String toString() {
        return super.toString() + "Bibliotecar{" +
                "sectiuni=" + sectiuni +
                ", dataNasterii=" + dataNasterii +
                '}';
    }

    public void moveToSectiuni(List<Integer> newSec) {
        this.sectiuni = newSec;
    }

    public void addSectiuni(List<Integer> addSec) {
        this.sectiuni.addAll(addSec);
    }
    public void addSectiuni(Integer addSec) {
        this.sectiuni.add(addSec);
    }


    public void removeFromSectiuni(List<Integer> rmSec) {
        this.sectiuni.removeAll(rmSec);
    }
    public void removeFromSectiuni(Integer rmSec) {
        this.sectiuni.remove(rmSec);
    }
}
