import administrativ.Aripa;
import administrativ.Sectiune;
import biblioteca.Biblioteca;
import carte.Carte;
import com.sun.source.tree.Tree;
import membri.Autor;
import membri.Membru;
import membri.angajati.Angajat;
import membri.angajati.Bibliotecar;
import membri.angajati.ITist;
import service.MainMenu;

import javax.accessibility.AccessibleRole;
import java.text.ParseException;
import java.util.*;

public class Main {

    public static void initValues(HashSet<Aripa> aripi, HashSet<Sectiune> sectiuni, HashSet<Angajat> angajati, TreeSet<Autor> autori, TreeSet<Carte> carti){
        Autor a1 = new Autor("Asimov", "Isaac", Membru.getACCEPTED(), new GregorianCalendar(2012, Calendar.JULY, 6).getTime(), "Autor de SF");
        Autor a2 = new Autor("Liviu", "Rebreanu", Membru.getACCEPTED(), new GregorianCalendar(2012, Calendar.FEBRUARY, 6).getTime(), "Autor de romane");
        Autor a3 = new Autor("J.R.R.", "Tolkien", Membru.getACCEPTED(), new GregorianCalendar(2012, Calendar.MARCH, 6).getTime(), "Autor de fantasy");
        autori.add(a1);
        autori.add(a2);
        autori.add(a3);

        Aripa ar1 = new Aripa("aripa 1", 0.0);
        Aripa ar2 = new Aripa("aripa 2", 0.01);
        aripi.add(ar1);
        aripi.add(ar2);

        Sectiune s1 = new Sectiune(ar1, "sectiunea s1", Arrays.asList("SF", "roman istoric"), 0.0);
        Sectiune s2 = new Sectiune(ar1, "sectiunea s2", Arrays.asList("roman"), 0.02);
        Sectiune s3 = new Sectiune(ar2, "sectiunea d1", Arrays.asList("fantasy", "beletristica"), 0.0);
        sectiuni.add(s1);
        sectiuni.add(s2);
        sectiuni.add(s3);

        Carte c1 = new Carte(a1, Arrays.asList(s1), "Fundatia", Arrays.asList("SF"), 12.0, 0.1, 200);
        Carte c2 = new Carte(a1, Arrays.asList(s1), "Eu robotul", Arrays.asList("SF"), 16.0, 0.0, 30);
        Carte c3 = new Carte(a2, Arrays.asList(s2), "Ion", Arrays.asList("roman"), 5.0, 0.25, 10);
        Carte c4 = new Carte(a3, Arrays.asList(s3),"Stapanul Inelelor 1", Arrays.asList("fantasy"), 10.0, 0.0, 100);
        Carte c5 = new Carte(a3, Arrays.asList(s3), "Stapanul Inelelor 2", Arrays.asList("fantasy"), 10.0, 0.0, 120);
        Carte c6 = new Carte(a3, Arrays.asList(s3), "Stapanul Inelelor 3", Arrays.asList("fantasy"), 10.0, 0.0, 110);
        carti.addAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

        Angajat b1 = new Bibliotecar("Ion", "Ion", Membru.getACCEPTED(), new GregorianCalendar(2000, 1, 3).getTime(), 1900.0);
        Angajat b2 = new Bibliotecar("Mihai", "Andrei", Membru.getACCEPTED(), new GregorianCalendar(2006, 12, 30).getTime(), 3000.0);
        Angajat b3 = new Bibliotecar("Alex", "Petre", Membru.getACCEPTED(), new GregorianCalendar(2003, 6, 6).getTime(), 3100.0);
        Angajat i1 = new ITist("Dan", "Dan", Membru.getACCEPTED(), new GregorianCalendar(2005, 7, 10).getTime(), 2000.0, Arrays.asList("server aripa 1"));
        Angajat i2 = new ITist("Daniel", "Pop", Membru.getACCEPTED(), new GregorianCalendar(2005, 9, 10).getTime(), 4000.0, Arrays.asList("server aripa 1", "server aripa 2"));
        angajati.add(b1);
        angajati.add(b2);
        angajati.add(b3);
        angajati.add(i1);
        angajati.add(i2);
    }

    public static void main(String[] args) throws InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);

        HashSet<Aripa> aripi = new HashSet<>();
        HashSet<Sectiune> sectiuni = new HashSet<>();
        HashSet<Angajat> angajati = new HashSet<>();
        TreeSet<Carte> carti = new TreeSet<>();
        TreeSet<Autor> autori = new TreeSet<>();

        initValues(aripi, sectiuni, angajati, autori, carti);

        Biblioteca biblioteca = new Biblioteca(aripi, sectiuni, angajati, autori, carti);
        MainMenu menu = MainMenu.getInstance(biblioteca);
        int response = -1;
        while(response != 0) {
            Thread.sleep(1000); // for some flavor
            menu.printMainMenu();
            response = sc.nextInt();
            menu.handleResponse(response);
        }
    }
}
