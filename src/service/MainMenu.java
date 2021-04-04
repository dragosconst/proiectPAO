package service;

import administrativ.Aripa;
import administrativ.Sectiune;
import biblioteca.Biblioteca;
import carte.Carte;
import com.sun.source.tree.Tree;
import membri.Autor;
import membri.Membru;
import membri.angajati.Angajat;
import membri.angajati.Bibliotecar;

import java.text.SimpleDateFormat;
import java.util.*;

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
        else if(resp == 2){
            showAllAripi();
        }
        else if(resp == 3){
            showAllSectiuni();
        }
        else if(resp == 4){
            showAllAngajati();
        }
        else if(resp == 5) {
            addBook();
        }
        else if(resp == 6) {
            addAutor();
        }
    }

    public void showAllBooks() {
        TreeSet<Carte> carti = this.biblioteca.getCarti();
        for(Carte it: carti){
            System.out.println("-------------------------------");
            System.out.println(it.getDenumire());
            System.out.println("de " + it.getAutor().getNume() + " " + it.getAutor().getPrenume());
            System.out.println("Gen: " + it.getGenuri());
            System.out.println("Pret: " + it.getPret());
            System.out.println("Discount: " + it.getDiscount());
            System.out.println("Exemplare disponibile: " + it.getNrExemplare());
            System.out.println("Numar total de imprumuturi: " + it.getTotalImprumuturi());
            System.out.println("Se gasesc in sectiunile " + it.getSectiuni());
            System.out.println("-------------------------------");
        }
    }

    private void showAllAripi() {
        HashSet<Aripa> aripi = this.biblioteca.getAripi();
        for(Aripa ar: aripi){
            System.out.println("-------------------------------");
            System.out.println("Aripa " + ar.getDenumire());
            System.out.println("Discount: " + ar.getDiscount());

            List<Sectiune> sectiunes = new ArrayList<>();
            for(Sectiune sectiune: this.biblioteca.getSectiuni()) {
                if(sectiune.getAripa().getAripaId() == ar.getAripaId()) {
                    sectiunes.add(sectiune);
                }
            }
            System.out.println("Sectiuni: " + sectiunes);
            System.out.println("-------------------------------");
        }
    }

    private void showAllSectiuni() {
        HashSet<Sectiune> sectiuni = this.biblioteca.getSectiuni();
        for(Sectiune s: sectiuni){
            System.out.println("-------------------------------");
            System.out.println(s.getDenumire());
            System.out.println("Aripa:" + s.getAripa().getDenumire());
            System.out.println("Genuri: " + s.getGenuri());
            System.out.println("Discount: " + s.getDiscount());

            List<Carte> carti = new ArrayList<>();
            for(Carte carte: this.biblioteca.getCarti()) {
                if(carte.getSectiuni().contains(s)) {
                    carti.add(carte);
                }
            }
            System.out.println("Carti: " + carti);
            System.out.println("-------------------------------");
        }
    }

    private void showAllAngajati(){
        HashSet<Angajat> angajati = this.biblioteca.getAngajati();
        for(Angajat a: angajati) {
            System.out.println("-------------------------------");
            System.out.println("Angajat: " + a.getNume() + " " + a.getPrenume());
            System.out.println("Departament" + (a instanceof Bibliotecar ? "Bibliotecar" : "ITist"));
            System.out.println("Salariu: " + a.getSalariu());
            System.out.println("Venit total: " + a.totalIncome());
            System.out.println("-------------------------------");
        }
    }

    private Integer parseInput(Scanner lsc, List<Integer> posInputs) {
        boolean correctInput = true;
        Integer response = null;
        // check input format
        do {
            correctInput = true;
            try {
                response = lsc.nextInt();
                if (!posInputs.contains(response)) {
                    correctInput = false;
                    System.out.println("introdu un input valid!");
                }
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("introdu un input valid!");
                correctInput = false;
                lsc.nextLine();
            }
        }while(!correctInput);

        return response;
    }
    private Double parseInputDouble(Scanner lsc) {
        boolean correctInput = true;
        Double response = null;
        // check input format
        do {
            correctInput = true;
            try {
                response = lsc.nextDouble();
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("introdu un input valid!");
                correctInput = false;
                lsc.nextLine();
            }
        }while(!correctInput);

        return response;
    }
    private Integer parseInputInteger(Scanner lsc) {
        boolean correctInput = true;
        Integer response = null;
        // check input format
        do {
            correctInput = true;
            try {
                response = lsc.nextInt();
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("introdu un input valid!");
                correctInput = false;
                lsc.nextLine();
            }
        }while(!correctInput);

        return response;
    }
    private List<Sectiune> parseInputSectiuni(Scanner lsc, List<Integer> posInputs) {
        boolean correctInput = true;
        List<Sectiune> sections = new ArrayList<>();
        do {
            String[] inputIds = lsc.nextLine().split("[,]", 0);
            sections = new ArrayList<>();
            correctInput = true;
            for (String id : inputIds) {
                try {
                    int intId = Integer.parseInt(id);
                    if (!posInputs.contains(intId)) {
                        System.out.println("introdu un input valid");
                        correctInput = false;
                        break;
                    } else {
                        sections.add(this.biblioteca.findSectiune(intId));
                    }
                } catch (NumberFormatException | InputMismatchException e) {
                    System.out.println("introdu un input valid");
                    correctInput = false;
                    break;
                }
            }
        }while(!correctInput);
        return new ArrayList<>(new HashSet<>(sections)); // remove duplicates
    }

    private void addBook() {
        Scanner lsc = new Scanner(System.in);
        System.out.println("Alege unul dintre autori, introducand id-ul:");
        List<Integer> possibleIds = new ArrayList<>();
        for(Autor autor: this.biblioteca.getAutori()){
            System.out.println("-------------------------------");
            System.out.println("Autor " + autor.getNume() + " " + autor.getPrenume() + ", id: " + autor.getMembruId());
            possibleIds.add(autor.getMembruId());
        }
        Integer autorId = parseInput(lsc, possibleIds);
        Autor autor = this.biblioteca.findAutor(autorId);

        System.out.println("Introdu titlul cartii pe o linie: ");
        lsc.nextLine(); // pt int
        String title = lsc.nextLine();

        System.out.println("Alege sectiunile in care se gaseste, introducand id-urile, separate prin virgule:");
        possibleIds = new ArrayList<>();
        for(Sectiune sectiune: this.biblioteca.getSectiuni()) {
            System.out.println("-------------------------------");
            System.out.println(sectiune.getDenumire() + ",id=" +sectiune.getSectiuneId() +", genuri: " + sectiune.getGenuri());
            possibleIds.add(sectiune.getSectiuneId());
        }
        List<Sectiune> sectiuni = parseInputSectiuni(lsc, possibleIds);

        System.out.println("Introdu genurile din care face parte cartea, separate prin virgule--fara spatii!");
        List<String> genuri = Arrays.asList(lsc.nextLine().split("[,]", 0));
        genuri = new ArrayList<String>(new HashSet<String>(genuri));

        System.out.println("Introdu pretul cartii in format double (cu virgula)");
        Double price = parseInputDouble(lsc);

        System.out.println("Introdu cate exemplare sa aiba cartea");
        Integer nrExemplare = parseInputInteger(lsc);
        lsc.nextLine();

        Carte newCarte = new Carte(autor, sectiuni, title, genuri, price, 0.0, nrExemplare);
        System.out.println("Aceasta este cartea dorita?\n" + newCarte.toString());
        String resp = lsc.nextLine();
        if(resp.toLowerCase(Locale.ROOT).contains("da")) {
            TreeSet<Carte> crCarti = this.biblioteca.getCarti();
            crCarti.add(newCarte);
            this.biblioteca.setCarti(crCarti);
        }
        else {
            this.addBook();
        }
    }

    private void addAutor() {
        Scanner lsc = new Scanner(System.in);
        System.out.println("Introdu numele autorului, separat cu spatii albe");
        String[] name = lsc.nextLine().split("\\s+", 0);

        Date date = new Date(System.currentTimeMillis());

        System.out.println("Introdu o scurta descriere a autorului");
        String desc = lsc.nextLine();

        Autor autor = new Autor(name[0], String.join("-",Arrays.copyOfRange(name, 1, name.length)), Membru.getWAITING(), date, 0.0, 0.0, desc);
        System.out.println("Acesta este autorul dorit? " + autor.toString());
        String resp = lsc.nextLine();
        if(resp.toLowerCase(Locale.ROOT).contains("da")) {
            TreeSet<Autor> crAutori = this.biblioteca.getAutori();
            crAutori.add(autor);
            this.biblioteca.setAutori(crAutori);
        }
        else {
            this.addAutor();
        }
    }
}
