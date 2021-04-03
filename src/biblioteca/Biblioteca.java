package biblioteca;

import administrativ.Aripa;
import administrativ.Sectiune;
import carte.Carte;
import membri.Autor;
import membri.Membru;
import membri.angajati.Angajat;

import java.util.*;

public class Biblioteca {
    private int bibliotecaId;
    private static int lastBibliotecaId;
    private Set<Aripa> aripi;
    private Set<Sectiune> sectiuni;
    private Set<Angajat> angajati;
    private SortedSet<Autor>  autori;
    private SortedSet<Carte>  carti;
    private TreeMap<String, Carte> cartiDenumiri;

    public Biblioteca(Set<Aripa> aripi, Set<Sectiune> sectiuni, Set<Angajat> angajati, SortedSet<Autor> autori, SortedSet<Carte> carti) {
        this.bibliotecaId = lastBibliotecaId + 1;
        lastBibliotecaId += 1;
        this.aripi = aripi;
        this.sectiuni = sectiuni;
        this.angajati = angajati;
        this.autori = autori;
        this.carti = carti;
        this.generateTreeSet();
    }

    public TreeMap<String, Carte> getCartiDenumiri() {
        return cartiDenumiri;
    }

    public void setCartiDenumiri(TreeMap<String, Carte> cartiDenumiri) {
        this.cartiDenumiri = cartiDenumiri;
    }

    public int getBibliotecaId() {
        return bibliotecaId;
    }

    public void setBibliotecaId(int bibliotecaId) {
        this.bibliotecaId = bibliotecaId;
    }

    public static int getLastBibliotecaId() {
        return lastBibliotecaId;
    }

    public static void setLastBibliotecaId(int lastBibliotecaId) {
        Biblioteca.lastBibliotecaId = lastBibliotecaId;
    }

    public Set<Aripa> getAripi() {
        return aripi;
    }

    public void setAripi(Set<Aripa> aripi) {
        this.aripi = aripi;
    }

    public void addAripi(Aripa aripa){
        this.aripi.add(aripa);
    }

    public Set<Sectiune> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(Set<Sectiune> sectiuni) {
        this.sectiuni = sectiuni;
    }

    public void addSectiune(Sectiune sectiune) {
        this.sectiuni.add(sectiune);
    }

    public Set<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(Set<Angajat> angajati) {
        this.angajati = angajati;
    }

    public void addAngajat(Angajat angajat){
        this.angajati.add(angajat);
    }

    public Set<Autor> getAutori() {
        return autori;
    }

    public void setAutori(SortedSet<Autor> autori) {
        this.autori = autori;
    }

    public void addAutor(Autor autor) {
        this.autori.add(autor);
    }

    public Set<Carte> getCarti() {
        return carti;
    }

    public void setCarti(SortedSet<Carte> carti) {
        this.carti = carti;
    }

    public void addCarte(Carte carte){
        this.carti.add(carte);
        this.cartiDenumiri.put(carte.getDenumire(), carte);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biblioteca that = (Biblioteca) o;
        return bibliotecaId == that.bibliotecaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bibliotecaId);
    }

    // aplica toate discount-urile posibile la carte
    public double finalPrice(int carteId){
        Double totalDiscount = 0.0;
        Carte carte = null;
        // get carte from set
        for(Carte it : this.carti){
            if(it.getCarteId() == carteId) {
                carte = new Carte(it);
                break;
            }
        }
        totalDiscount += carte.getDiscount();

        // get autor from set
        Autor autor = null;
        for(Autor it: this.autori){
            if(it.getMembruId() == carte.getAutorId()) {
                totalDiscount += it.getDiscount();
                break;
            }
        }

        // get section from set
        Integer aripaId = null;
        for(Sectiune it: this.sectiuni){
            if(it.getSectiuneId() == carte.getSectiuneId()){
                totalDiscount += it.getDiscount();
                aripaId = it.getAripaId();
                break;
            }
        }

        // get aripa from set
        for(Aripa it: this.aripi) {
            if(it.getAripaId() == aripaId){
                totalDiscount += it.getDiscount();
                break;
            }
        }

        return carte.getPret() - carte.getPret() * totalDiscount;
    }

    private void generateTreeSet() {
        for(Carte it: this.carti){
            this.cartiDenumiri.put(it.getDenumire(), it);
        }
    }


}
