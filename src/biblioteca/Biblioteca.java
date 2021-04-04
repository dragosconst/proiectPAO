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
    private HashSet<Aripa> aripi;
    private HashSet<Sectiune> sectiuni;
    private HashSet<Angajat> angajati;
    private TreeSet<Autor>  autori;
    private TreeSet<Carte>  carti;
    private TreeMap<String, Carte> cartiDenumiri;

    public Biblioteca(HashSet<Aripa> aripi, HashSet<Sectiune> sectiuni, HashSet<Angajat> angajati, TreeSet<Autor> autori, TreeSet<Carte> carti) {
        this.bibliotecaId = lastBibliotecaId + 1;
        lastBibliotecaId += 1;
        this.aripi = aripi;
        this.sectiuni = sectiuni;
        this.angajati = angajati;
        this.autori = autori;
        this.carti = carti;
        this.cartiDenumiri = new TreeMap<>();
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

    public HashSet<Aripa> getAripi() {
        return aripi;
    }

    public void setAripi(HashSet<Aripa> aripi) {
        this.aripi = aripi;
    }

    public TreeSet<Carte> getCarti() {
        return carti;
    }

    public void setCarti(TreeSet<Carte> carti) {
        this.carti = carti;
    }

    public HashSet<Sectiune> getSectiuni() {
        return sectiuni;
    }

    public void setSectiuni(HashSet<Sectiune> sectiuni) {
        this.sectiuni = sectiuni;
    }

    public HashSet<Angajat> getAngajati() {
        return angajati;
    }

    public void setAngajati(HashSet<Angajat> angajati) {
        this.angajati = angajati;
    }

    public TreeSet<Autor> getAutori() {
        return autori;
    }

    public void setAutori(TreeSet<Autor> autori) {
        this.autori = autori;
    }

    public Autor findAutor(int autorId) {
        for(Autor it: this.autori){
            if(it.getMembruId() == autorId)
                return it;
        }

        return null;
    }

    public Sectiune findSectiune(int sectiuneId) {
        for(Sectiune s: this.sectiuni) {
            if(s.getSectiuneId() == sectiuneId)
                return s;
        }
        return null;
    }

    public Aripa findAripa(int aripaId) {
        for(Aripa a: this.aripi) {
            if(a.getAripaId() == aripaId)
                return a;
        }
        return null;
    }

    public void addAripi(Aripa aripa){
        this.aripi.add(aripa);
    }


    public void addSectiune(Sectiune sectiune) {
        this.sectiuni.add(sectiune);
    }

    public void addAutor(Autor autor) {
        this.autori.add(autor);
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
        totalDiscount += carte.getAutor().getDiscount();

        // get section from set
        Integer aripaId = null;
        List<Integer> aripi = new ArrayList<>();
        for(Sectiune it: this.sectiuni){
            if(carte.getSectiuni().contains(it)){
                totalDiscount += it.getDiscount();
                aripi.add(it.getAripa().getAripaId());
            }
        }

        // get aripa from set
        for(Aripa it: this.aripi) {
            if(aripi.contains(it.getAripaId())){
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
