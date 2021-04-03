package carte;

import administrativ.Sectiune;
import membri.Autor;

import java.util.List;
import java.util.Objects;

public class Carte implements Comparable<Carte>{
    private final int carteId;
    private final int autorId;
    private List<Integer> sectiuneId;
    private static int lastCarteId = 0;
    private String denumire;
    private List<String> genuri;
    private Double pret;
    private Double discount;
    private int totalImprumuturi;
    private int nrExemplare;

    public Carte(int autorId, List<Integer> sectiuneId, String denumire, List<String> genuri, Double pret, Double discount, int nrExemplare) {
        this.carteId = lastCarteId + 1;
        lastCarteId += 1;
        this.autorId = autorId;
        this.sectiuneId = sectiuneId;
        this.denumire = denumire;
        this.genuri = genuri;
        this.pret = pret;
        this.discount = discount;
        this.nrExemplare = nrExemplare;
    }

    public Carte(Carte other){
        this.carteId = other.carteId;
        this.autorId = other.autorId;
        this.sectiuneId = other.sectiuneId;
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

    public int getAutorId() {
        return autorId;
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

    public void setSectiuneId(List<Integer> sectiuneId) {
        this.sectiuneId = sectiuneId;
    }

    public List<Integer> getSectiuneId() {
        return sectiuneId;
    }

    public void addSectiune(int sectiuneId){
        this.sectiuneId.add(sectiuneId);
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
        return "Carte{" +
                "autorId=" + autorId +
                ", sectiuneId=" + sectiuneId +
                ", denumire='" + denumire + '\'' +
                ", genuri=" + genuri +
                ", pret=" + pret +
                ", discount=" + discount +
                '}';
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