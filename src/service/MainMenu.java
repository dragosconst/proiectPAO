package service;

import administrativ.Aripa;
import administrativ.Sectiune;
import biblioteca.Biblioteca;
import carte.Carte;
import com.sun.source.tree.Tree;
import com.sun.tools.javac.Main;
import membri.Autor;
import membri.Membru;
import membri.angajati.Angajat;
import membri.angajati.Bibliotecar;
import membri.angajati.ITist;
import repo.impl.BiblRepoImpl;
import utils.CsvReader;
import utils.CsvWriter;

import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainMenu {
    private Biblioteca biblioteca;
    private static MainMenu instance = null;

    private MainMenu() {
        this.biblioteca = new Biblioteca(null, null, null, null, null);
    }

    private MainMenu(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public static MainMenu getInstance(Biblioteca biblioteca)
    {
        if(instance == null)
            instance = new MainMenu(biblioteca);
        return instance;
    }

    public static MainMenu getInstance()
    {
        if(instance == null)
            instance = new MainMenu();
        return instance;
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
        System.out.println("11. Genereaza csv cu toata biblioteca");
        System.out.println("12. Incarca biblioteca salvata");
        System.out.println("13. Incarca biblioteca din baza de date");
    }

    public void handleResponse(int resp) {
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
            CsvWriter.writeBiblioteca(this.biblioteca, true);
        }
        else if(resp == 6) {
            addAutor();
            CsvWriter.writeBiblioteca(this.biblioteca, true);
        }
        else if(resp == 7) {
            addAngajat();
            CsvWriter.writeBiblioteca(this.biblioteca, true);
        }
        else if(resp == 8) {
            addSectiune();
            CsvWriter.writeBiblioteca(this.biblioteca, true);
        }
        else if(resp == 9) {
            addAripa();
            CsvWriter.writeBiblioteca(this.biblioteca, true);
        }
        else if(resp == 10) {
            orderStuff();
        }
        else if(resp == 11) {
            CsvWriter.writeBiblioteca(this.biblioteca, false);
        }
        else if(resp == 12){
            this.biblioteca = CsvReader.readBiblioteca();
        }
        else if(resp == 13) {
            this.biblioteca = this.loadFromDb();
        }
        else {
            System.out.println("Ai introdus o comanda care nu exista");
        }
    }

    private void showAllBooks() {
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
            System.out.println("Departament: " + (a instanceof Bibliotecar ? "Bibliotecar" : "ITist"));
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
            BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
            dbInstance.addCarte(newCarte);
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
            BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
            dbInstance.addAutor(autor);
            this.biblioteca.setAutori(crAutori);
        }
        else {
            this.addAutor();
        }
    }

    private void addAngajat() {
        Scanner lsc = new Scanner(System.in);
        System.out.println("Introdu numele angajatului, separat cu spatii albe");
        String[] name = lsc.nextLine().split("\\s+", 0);

        Date date = new Date(System.currentTimeMillis());

        System.out.println("Introdu salariul angajatului");
        Double salary = parseInputDouble(lsc);

        System.out.println("Introdu comisionul angajatului");
        Double comm = parseInputDouble(lsc);
        lsc.nextLine();

        System.out.println("Anagajatul introdus este un bibliotecar?");
        String resp = lsc.nextLine();
        if(resp.toLowerCase(Locale.ROOT).contains("da")) {
            System.out.println("Introdu data nasterii, pentru bonus-uri, in format dd-MM-yyyy");
            String dateString = lsc.next();
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date nastere = null;
            try {
                nastere = formatter.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

            System.out.println("Selecteaza, dupa id, sectiunile in care lucreaza");
            List<Integer> possibleIds = new ArrayList<>();
            for(Sectiune sectiune: this.biblioteca.getSectiuni()) {
                System.out.println("-------------------------------");
                System.out.println(sectiune.getDenumire() + ",id=" +sectiune.getSectiuneId() +", genuri: " + sectiune.getGenuri());
                possibleIds.add(sectiune.getSectiuneId());
            }
            List<Sectiune> sectiuni = parseInputSectiuni(lsc, possibleIds);

            Bibliotecar bibliotecar = new Bibliotecar(name[0], String.join("-",Arrays.copyOfRange(name, 1, name.length)),
                    Membru.getWAITING(), date, salary, comm, sectiuni, nastere);
            System.out.println("Asta e bibliotecarul pe care voiai sa il adaugi? " + bibliotecar.toString());
            resp = lsc.nextLine();
            if(resp.toLowerCase(Locale.ROOT).contains("da")) {
                HashSet<Angajat> crAng = this.biblioteca.getAngajati();
                crAng.add(bibliotecar);
                BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
                dbInstance.addBibliotecar(bibliotecar);
                this.biblioteca.setAngajati(crAng);
            }
            else {
                this.addAngajat();
            }

        }
        else {
            System.out.println("Introdu serverele in care lucreaza, separand prin virgula");
            String[] servers = lsc.nextLine().split("[,]",0);

            ITist iTist = new ITist(name[0], String.join("-",Arrays.copyOfRange(name, 1, name.length)),
                    Membru.getWAITING(), date, salary, comm, Arrays.asList(servers));
            System.out.println("Asta e itistul pe care voiai sa il adaugi? " + iTist.toString());
            resp = lsc.nextLine();
            if(resp.toLowerCase(Locale.ROOT).contains("da")) {
                HashSet<Angajat> crAng = this.biblioteca.getAngajati();
                crAng.add(iTist);
                BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
                dbInstance.addItist(iTist);
                this.biblioteca.setAngajati(crAng);
            }
            else {
                this.addAngajat();
            }
        }
    }

    private void addSectiune() {
        Scanner lsc = new Scanner(System.in);

        System.out.println("In ce aripa vrei sa adaugi sectiunea? introdu id-ul");
        List<Integer> possibleIds = new ArrayList<>();
        for(Aripa aripa: this.biblioteca.getAripi()){
            System.out.println("-------------------------------");
            System.out.println("Aripa " + aripa.getDenumire() + ", id: " + aripa.getAripaId());
            possibleIds.add(aripa.getAripaId());
        }
        Integer aripaId = parseInput(lsc, possibleIds);
        lsc.nextLine();
        Aripa aripa = this.biblioteca.findAripa(aripaId);

        System.out.println("Introdu denumirea sectiunii");
        String name = lsc.nextLine();

        System.out.println("Introdu genurile, separate prin virgula");
        String[] gens = lsc.nextLine().split("[,]", 0);

        Sectiune sectiune = new Sectiune(aripa, name, Arrays.asList(gens), 0.0);
        System.out.println("Asta e sectiunea pe care o vrei? " + sectiune);
        String resp = lsc.nextLine();
        if(resp.toLowerCase(Locale.ROOT).contains("da")) {
            HashSet<Sectiune> crSec = this.biblioteca.getSectiuni();
            crSec.add(sectiune);
            BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
            dbInstance.addSectiune(sectiune);
            this.biblioteca.setSectiuni(crSec);
        }
        else {
            this.addSectiune();
        }
    }

    private void addAripa() {
        Scanner lsc = new Scanner(System.in);

        System.out.println("Introduce denumirea aripii pe care vrei sa o adaugi");
        String name = lsc.nextLine();

        Aripa aripa = new Aripa(name, 0.0);
        System.out.println("Asta e aripa pe care vrei sa o inserezi? " + aripa.toString());
        String resp = lsc.nextLine();
        if(resp.toLowerCase(Locale.ROOT).contains("da")) {
            HashSet<Aripa> crAripa = this.biblioteca.getAripi();
            crAripa.add(aripa);
            BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
            dbInstance.addAripa(aripa);
            this.biblioteca.setAripi(crAripa);
        }
        else {
            this.addSectiune();
        }
    }

    private void orderStuff() {
        Scanner lsc = new Scanner(System.in);
        boolean finished = true;

        double fullPrice = 0.0;
        List<Carte> cartiComandate = new ArrayList<>();
        do{
            finished = false;
            System.out.println("Alege ce carte vrei sa comanzi, introducand id-ul");
            for(Carte c: this.biblioteca.getCarti()) {
                System.out.println(c);
                System.out.println("-------------------------");
            }

            List<Integer> possibleIds = new ArrayList<>();
            for(Carte carte: this.biblioteca.getCarti()) {
                possibleIds.add(carte.getCarteId());
            }
            Integer cId = parseInput(lsc, possibleIds);
            lsc.nextLine();
            Carte comanda = this.biblioteca.findCarte(cId);

            fullPrice += this.biblioteca.finalPrice(cId);
            cartiComandate.add(comanda);
            System.out.println("Comanda ta pana acum este:");
            for(Carte c: cartiComandate) {
                System.out.println("-------------------------");
                System.out.println(c);
                System.out.println("-------------------------");
            }
            System.out.println("Pret total: " + fullPrice);
            System.out.println("Mai vrei sa comanzi ceva?");
            String resp = lsc.nextLine();

            if(!resp.toLowerCase(Locale.ROOT).contains("da")) {
                finished = true;
            }
        }while(!finished);
    }

    private Biblioteca loadFromDb() {
        BiblRepoImpl dbInstance = BiblRepoImpl.getInstance();
        HashSet<Aripa> aripi = dbInstance.getAripi();
        HashSet<Sectiune> sectiuni = dbInstance.getSectiuni();
        TreeSet<Autor> autori = dbInstance.getAutori();
        TreeSet<Carte> carti = dbInstance.getCarti();
        HashSet<Bibliotecar> bibliotecari = dbInstance.getBibilotecari();
        HashSet<ITist> iTisti = dbInstance.getITisti();

        Set<Angajat> angajati_set = Stream.concat(bibliotecari.stream(), iTisti.stream()).collect(Collectors.toSet());
        HashSet<Angajat> angajati = (HashSet<Angajat>) angajati_set;

        //  public Biblioteca(HashSet<Aripa> aripi, HashSet<Sectiune> sectiuni, HashSet<Angajat> angajati, TreeSet<Autor> autori, TreeSet<Carte> carti) {
        //
        return new Biblioteca(aripi, sectiuni, angajati, autori, carti);
    }
}
