package carte;

import administrativ.Sectiune;
import membri.Autor;

import java.util.List;
import java.util.Objects;

public class Carte implements Comparable<Carte>{
    private final int carteId;
    private final Autor autor;
    private List<Sectiune> sectiuni;
    private static int lastCarteId = 0;
    private String denumire;
    private List<String> genuri;
    private Double pret;
    private Double discount;
    private int totalImprumuturi;
    private int nrExemplare;

    public Carte(Autor autor, List<Sectiune> sectiuni, String denumire, List<String> genuri, Double pret, Double discount, int nrExemplare) {
        this.carteId = lastCarteId + 1;
        lastCarteId += 1;
        this.autor = autor;
        this.sectiuni = sectiuni;
        this.denumire = denumire;
        this.genuri = genuri;
        this.pret = pret;
        this.discount = discount;
        this.nrExemplare = nrExemplare;
    }

    public Carte(Carte other){
        this.carteId = other.carteId;
        this.autor = other.autor;
        this.sectiuni = other.sectiuni;
        this.denumire = other.denumire;
        this.genuri = other.genuri;
        this.pret = other.pret;
        this.discount = other.discount;
        this.totalImprumuturi = other.totalImprumuturi;
        this.nrExemplare = other.nrExemplare;
    }

    public int getCarteId() {
        return carteId;
    }

    public Autor getAutor() {
        return autor;
    }

    public int getTotalImprumuturi() {
        return totalImprumuturi;
    }

    public int getNrExemplare() {
        return nrExemplare;
    }

    public void setNrExemplare(int nrExemplare) {
        this.nrExemplare = nrExemplare;
    }

    public List<Sectiune> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(List<Sectiune> sectiuni) {
        this.sectiuni = sectiuni;
    }

    public void addSectiune(Sectiune sectiune){
        this.sectiuni.add(sectiune);
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<String> getGenuri() {
        return genuri;
    }

    public void setGenuri(List<String> genuri) {
        this.genuri = genuri;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return carteId == carte.carteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(carteId);
    }

    @Override
    public String toString() {
        return "Titlu: " + this.denumire + "\nAutor: " + this.getAutor().getNume() + " " + this.getAutor().getPrenume() +
                "\nGenuri: " + this.getGenuri() + "\nSectiuni: " + this.getSectiuni() + "\nPret: " + this.getPret() +
                "\nNr exemplare: " + this.getNrExemplare();
    }

    public void imprumuta() {
        this.totalImprumuturi += 1;
    }

    @Override
    public int compareTo(Carte o) {
        if (this == o) return 0;
        return this.getDenumire().compareTo(o.getDenumire());
    }
}
