package utils;

import administrativ.Aripa;
import administrativ.Sectiune;
import biblioteca.Biblioteca;
import carte.Carte;
import membri.Autor;
import membri.angajati.Angajat;
import membri.angajati.Bibliotecar;
import membri.angajati.ITist;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.util.*;
import java.util.stream.Collectors;

public class CsvReader {
    public static TreeSet<Carte> readCarti(Biblioteca biblioteca) {
        // TODO: encapsulate the filepath in another util class or something
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\carti.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            TreeSet<Carte> carti = new TreeSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                // TODO: encapsulate this too
                if(dataLine[0].equals("carteId")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[0]);
                    Autor autor = biblioteca.getAutori().stream().filter(a -> a.getMembruId().toString().equals(dataLine[1])).findFirst().get();
                    List<Sectiune> sectiuni = biblioteca.getSectiuni().stream().filter(s -> dataLine[2].contains(Integer.toString(s.getSectiuneId()))).collect(Collectors.toList());
                    String denumire = dataLine[3];
                    List<String> genuri = Arrays.stream(dataLine[4].split(";")).collect(Collectors.toList());
                    Double pret = Double.parseDouble(dataLine[5]);
                    Double discount = Double.parseDouble(dataLine[6]);
                    int nrExemplare = Integer.parseInt(dataLine[7]);
                    Carte carte = new Carte(id, autor, sectiuni, denumire, genuri, pret, discount, nrExemplare);
                    carti.add(carte);
                }
            }
            return carti;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static TreeSet<Autor> readAutori(Biblioteca biblioteca) {
        // TODO: encapsulate the filepath in another util class or something
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\autori.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            TreeSet<Autor> autori = new TreeSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                // TODO: encapsulate this too
                if(dataLine[0].equals("autorId")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[0]);
                    String nume = dataLine[1];
                    String prenume = dataLine[2];
                    String statut = dataLine[3];
                    Date dataInscriere = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[4]);
                    Double popularitate = Double.parseDouble(dataLine[5]);
                    Double discount = Double.parseDouble(dataLine[6]);
                    String descriere = dataLine[7];
                    Autor autor = new Autor(id, nume, prenume, statut, dataInscriere, popularitate, discount, descriere);
                    autori.add(autor);
                }
            }
            return autori;
        }catch(IOException | ParseException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static HashSet<Angajat> readAngajati(Biblioteca biblioteca) {
        // TODO: encapsulate the filepath in another util class or something
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\angajati.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            HashSet<Angajat> angajati = new HashSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                // TODO: encapsulate this too
                if(dataLine[0].equals("angajatId")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[0]);
                    String nume = dataLine[1];
                    String prenume = dataLine[2];
                    String statut = dataLine[3];
                    Date dataInscriere = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[4]);
                    Double salariu = Double.parseDouble(dataLine[5]);
                    Double commission = Double.parseDouble(dataLine[6]);
                    Integer type = Integer.parseInt(dataLine[7]);
                    if (type == 2) {
                        List<String> servere = Arrays.stream(dataLine[8].split(";")).collect(Collectors.toList());
                        ITist iTist = new ITist(nume, prenume, statut, dataInscriere, salariu, commission, servere);
                        angajati.add(iTist);
                    }
                    else{
                        List<Sectiune> sectiuni = biblioteca.getSectiuni().stream().filter(s -> dataLine[9].contains(Integer.toString(s.getSectiuneId()))).collect(Collectors.toList());
                        Date dataNasterii = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[10]);
                        Bibliotecar bibliotecar = new Bibliotecar(nume, prenume, statut, dataInscriere, salariu, commission, sectiuni,dataNasterii);
                        angajati.add(bibliotecar);
                    }
                }
            }
            System.out.println(angajati);
            return angajati;
        }catch(IOException | ParseException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static HashSet<Aripa> readAripi(Biblioteca biblioteca) {
        // TODO: encapsulate the filepath in another util class or something
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\aripi.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            HashSet<Aripa> aripi = new HashSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                // TODO: encapsulate this too
                if(dataLine[0].equals("aripaid")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[0]);
                    String denumire = dataLine[1];
                    Double discount = Double.parseDouble(dataLine[2]);
                    Aripa aripa = new Aripa(id, denumire, discount);
                    aripi.add(aripa);
                }
            }
            System.out.println(aripi);
            return aripi;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static HashSet<Sectiune> readSectiuni(Biblioteca biblioteca) {
        // TODO: encapsulate the filepath in another util class or something
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\sectiuni.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            HashSet<Sectiune> sectiuni = new HashSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                // TODO: encapsulate this too
                if(dataLine[0].equals("sectiuneid")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[0]);
                    Integer aripaId = Integer.parseInt(dataLine[1]);
                    Aripa aripa = biblioteca.getAripi().stream().filter(a -> a.getAripaId() == aripaId).findFirst().get();
                    String denumire = dataLine[2];
                    List<String> genuri = Arrays.stream(dataLine[3].split(";")).collect(Collectors.toList());
                    Double discount = Double.parseDouble(dataLine[4]);
                    Sectiune sectiune = new Sectiune(id, aripa, denumire, genuri, discount);
                    sectiuni.add(sectiune);
                }
            }
            System.out.println(sectiuni);
            return sectiuni;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static Biblioteca readBiblioteca() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setAripi(readAripi(biblioteca));
        biblioteca.setSectiuni(readSectiuni(biblioteca));
        biblioteca.setAutori(readAutori(biblioteca));
        biblioteca.setAngajati(readAngajati(biblioteca));
        biblioteca.setCarti(readCarti(biblioteca));
        System.out.println(biblioteca);
        return biblioteca;
    }
}
