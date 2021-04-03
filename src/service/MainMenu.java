package service;

import biblioteca.Biblioteca;
import carte.Carte;

import java.util.TreeSet;

public class MainMenu {
    private final Biblioteca biblioteca;

    public MainMenu() {
        this.biblioteca = new Biblioteca(null, null, null, null, null);
    }

    public MainMenu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void printMainMenu() {
        System.out.println("Bun venit in meniul principal al bibliotecii.");
        System.out.println("Alege una din optiunile de mai jos");
        System.out.println("0. Inchide aplicatia");
        System.out.println("1. Afiseaza toate cartile din biblioteca");
        System.out.println("2. Afiseaza toate aripile bibliotecii");
        System.out.println("3. Afiseaza toate sectiunile bibliotecii");
        System.out.println("4. Afiseaza toti angajatii bibliotecii");
        System.out.println("5. Adauga o carte in biblioteca");
        System.out.println("6. Adauga un autor in biblioteca");
        System.out.println("7. Adauga un angajat in biblioteca");
        System.out.println("8. Adauga o sectiune in biblioteca");
        System.out.println("9. Adauga o aripa in biblioteca");
        System.out.println("10. Plaseaza o comanda");
    }

    public void handleResponse(int resp){
        if(resp == 0) return;
        if(resp == 1){
            showAllBooks();
        }
    }

    public void showAllBooks() {
        TreeSet<Carte> carti = this.biblioteca.getCarti();
        for(Carte it: carti){
            System.out.println("-------------------------------");
            System.out.println(it.getDenumire());
            System.out.println("de " + this.biblioteca.findAutor(it.getAutorId()).getNume() + " " + this.biblioteca.findAutor(it.getAutorId()).getPrenume());
            System.out.println("Gen: " + it.getGenuri());
            System.out.println("Pret: " + it.getPret());
            System.out.println("Discount: " + it.getDiscount());
            System.out.println("Exemplare disponibile: " + it.getNrExemplare());
            System.out.println("Numar total de imprumuturi: " + it.getTotalImprumuturi());
            System.out.println("-------------------------------");
        }
    }
}
