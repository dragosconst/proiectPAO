package membri.angajati;

import administrativ.Sectiune;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Bibliotecar extends Angajat{
    private List<Sectiune> sectiuni;
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

    public Bibliotecar(String nume, String prenume, String statut, Date dataInscriere, Double salariu, List<Sectiune> sectiuni, Date dataNasterii) {
        super(nume, prenume, statut, dataInscriere, salariu);
        this.sectiuni = sectiuni;
        this.dataNasterii = dataNasterii;
    }

    public Bibliotecar(String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission, List<Sectiune> sectiuni, Date dataNasterii) {
        super(nume, prenume, statut, dataInscriere, salariu, commission);
        this.sectiuni = sectiuni;
        this.dataNasterii = dataNasterii;
    }

    public Bibliotecar(Integer id, String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission, List<Sectiune> sectiuni, Date dataNasterii) {
        super(id, nume, prenume, statut, dataInscriere, salariu, commission);
        this.sectiuni = sectiuni;
        this.dataNasterii = dataNasterii;
    }

    public List<Sectiune> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(List<Sectiune> sectiuni) {
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
        return "Nume: " + this.getNume() + " " + this.getPrenume() +
                "\nStatut: " + this.getStatut() +
                "\nSalariu: " + this.getSalariu() +
                "\nSectiuni: " + this.getSectiuni();
    }

    public void moveToSectiuni(List<Sectiune> newSec) {
        this.sectiuni = newSec;
    }

    public void addSectiuni(List<Sectiune> addSec) {
        this.sectiuni.addAll(addSec);
    }
    public void addSectiuni(Sectiune addSec) {
        this.sectiuni.add(addSec);
    }


    public void removeFromSectiuni(List<Sectiune> rmSec) {
        this.sectiuni.removeAll(rmSec);
    }
    public void removeFromSectiuni(Sectiune rmSec) {
        this.sectiuni.remove(rmSec);
    }
}
