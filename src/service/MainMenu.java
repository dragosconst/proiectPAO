package service;

import biblioteca.Biblioteca;

public class MainMenu {
    private Biblioteca biblioteca;

    public MainMenu() {
        this.biblioteca = new Biblioteca(null, null, null, null, null);
    }

    public MainMenu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public void mainMenu() {
        System.out.println("Bun venit in meniul principal al bibliotecii.\n");
        System.out.println("Alege una din optiunile de mai jos");
        System.out.println("0. Inchide aplicatia");
        System.out.println("1. ");
    }
}
