import administrativ.Aripa;
import administrativ.Sectiune;
import biblioteca.Biblioteca;
import carte.Carte;
import com.sun.source.tree.Tree;
import membri.Autor;
import membri.Membru;
import membri.angajati.Angajat;
import service.MainMenu;

import javax.accessibility.AccessibleRole;
import java.util.*;

public class Main {

    public static void initValues(HashSet<Aripa> aripi, HashSet<Sectiune> sectiuni, TreeSet<Autor> autori, TreeSet<Carte> carti){
        Autor a1 = new Autor("Asimov", "Isaac", Membru.getACCEPTED(), new Date(2012, 5, 6), "Autor de SF");
        Autor a2 = new Autor("Liviu", "Rebreanu", Membru.getACCEPTED(), new Date(2012, 5, 6), "Autor de romane");
        Autor a3 = new Autor("J.R.R.", "Tolkien", Membru.getACCEPTED(), new Date(2012, 5, 6), "Autor de fantasy");
        autori.add(a1);
        autori.add(a2);
        autori.add(a3);

        Aripa ar1 = new Aripa("aripa 1", 0.0);
        Aripa ar2 = new Aripa("aripa 2", 0.01);
        aripi.add(ar1);
        aripi.add(ar2);

        Sectiune s1 = new Sectiune(ar1.getAripaId(), "sectiunea s1", Arrays.asList("SF", "roman istoric"), 0.0);
        Sectiune s2 = new Sectiune(ar1.getAripaId(), "sectiunea s2", Arrays.asList("roman"), 0.02);
        Sectiune s3 = new Sectiune(ar2.getAripaId(), "sectiunea d1", Arrays.asList("fantasy", "beletristica"), 0.0);
        sectiuni.add(s1);
        sectiuni.add(s2);
        sectiuni.add(s3);

        Carte c1 = new Carte(a1.getMembruId(), Arrays.asList(s1.getSectiuneId()), "Fundatia", Arrays.asList("SF"), 12.0, 0.1, 200);
        Carte c2 = new Carte(a1.getMembruId(), Arrays.asList(s1.getSectiuneId()), "Eu, robotul", Arrays.asList("SF"), 16.0, 0.0, 30);
        Carte c3 = new Carte(a2.getMembruId(), Arrays.asList(s2.getSectiuneId()), "Ion", Arrays.asList("roman"), 5.0, 0.25, 10);
        Carte c4 = new Carte(a3.getMembruId(), Arrays.asList(s3.getSectiuneId()), "Stapanul Inelelor 1", Arrays.asList("fantasy"), 10.0, 0.0, 100);
        Carte c5 = new Carte(a3.getMembruId(), Arrays.asList(s3.getSectiuneId()), "Stapanul Inelelor 2", Arrays.asList("fantasy"), 10.0, 0.0, 120);
        Carte c6 = new Carte(a3.getMembruId(), Arrays.asList(s3.getSectiuneId()), "Stapanul Inelelor 3", Arrays.asList("fantasy"), 10.0, 0.0, 110);
        carti.addAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);

        HashSet<Aripa> aripi = new HashSet<>();
        HashSet<Sectiune> sectiuni = new HashSet<>();
        HashSet<Angajat> angajati = new HashSet<>();
        TreeSet<Carte> carti = new TreeSet<>();
        TreeSet<Autor> autori = new TreeSet<>();

        initValues(aripi, sectiuni, autori, carti);

        Biblioteca biblioteca = new Biblioteca(aripi, sectiuni, angajati, autori, carti);
        MainMenu menu = new MainMenu(biblioteca);
        int response = -1;
        while(response != 0) {
            Thread.sleep(1000);
            menu.printMainMenu();
            response = sc.nextInt();
            menu.handleResponse(response);
        }
    }
}
