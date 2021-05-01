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
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\carti.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            TreeSet<Carte> carti = new TreeSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                if(dataLine[CsvUtils.carteData.carteId].equals("carteId")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[CsvUtils.carteData.carteId]);
                    Autor autor = biblioteca.getAutori().stream().filter(a -> a.getMembruId().toString().equals(dataLine[CsvUtils.carteData.autor])).findFirst().get();
                    List<Sectiune> sectiuni = biblioteca.getSectiuni().stream().filter(s -> dataLine[CsvUtils.carteData.sectiuni].contains(Integer.toString(s.getSectiuneId()))).collect(Collectors.toList());
                    String denumire = dataLine[CsvUtils.carteData.denumire];
                    List<String> genuri = Arrays.stream(dataLine[CsvUtils.carteData.genuri].split(";")).collect(Collectors.toList());
                    Double pret = Double.parseDouble(dataLine[CsvUtils.carteData.pret]);
                    Double discount = Double.parseDouble(dataLine[CsvUtils.carteData.discount]);
                    int nrExemplare = Integer.parseInt(dataLine[CsvUtils.carteData.nrExemplare]);
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
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\autori.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            TreeSet<Autor> autori = new TreeSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                if(dataLine[CsvUtils.autorData.autorId].equals("autorId")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[CsvUtils.autorData.autorId]);
                    String nume = dataLine[CsvUtils.autorData.nume];
                    String prenume = dataLine[CsvUtils.autorData.prenume];
                    String statut = dataLine[CsvUtils.autorData.statut];
                    Date dataInscriere = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[CsvUtils.autorData.dataInscriere]);
                    Double popularitate = Double.parseDouble(dataLine[CsvUtils.autorData.popularitate]);
                    Double discount = Double.parseDouble(dataLine[CsvUtils.autorData.discount]);
                    String descriere = dataLine[CsvUtils.autorData.descriere];
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
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\angajati.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            HashSet<Angajat> angajati = new HashSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                if(dataLine[CsvUtils.angajatData.angajatId].equals("angajatId")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[CsvUtils.angajatData.angajatId]);
                    String nume = dataLine[CsvUtils.angajatData.nume];
                    String prenume = dataLine[CsvUtils.angajatData.prenume];
                    String statut = dataLine[CsvUtils.angajatData.statut];
                    Date dataInscriere = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[CsvUtils.angajatData.dataInscriere]);
                    Double salariu = Double.parseDouble(dataLine[CsvUtils.angajatData.salariu]);
                    Double commission = Double.parseDouble(dataLine[CsvUtils.angajatData.commission]);
                    Integer type = Integer.parseInt(dataLine[CsvUtils.angajatData.type]);
                    if (type == 2) {
                        List<String> servere = Arrays.stream(dataLine[CsvUtils.angajatData.servere].split(";")).collect(Collectors.toList());
                        ITist iTist = new ITist(nume, prenume, statut, dataInscriere, salariu, commission, servere);
                        angajati.add(iTist);
                    }
                    else{
                        List<Sectiune> sectiuni = biblioteca.getSectiuni().stream().filter(s -> dataLine[CsvUtils.angajatData.sectiuni].contains(Integer.toString(s.getSectiuneId()))).collect(Collectors.toList());
                        Date dataNasterii = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[CsvUtils.angajatData.dataNasterii]);
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
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\aripi.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            HashSet<Aripa> aripi = new HashSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                if(dataLine[CsvUtils.aripaData.aripaId].equals("aripaid")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[CsvUtils.aripaData.aripaId]);
                    String denumire = dataLine[CsvUtils.aripaData.denumire];
                    Double discount = Double.parseDouble(dataLine[CsvUtils.aripaData.discount]);
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
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\sectiuni.csv";
        try(Scanner sc = new Scanner(new File(filePath))){
            HashSet<Sectiune> sectiuni = new HashSet<>();
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] dataLine = line.split(",");
                if(dataLine[CsvUtils.sectiuneData.sectiuneId].equals("sectiuneid")){
                    continue;
                }
                else{
                    Integer id = Integer.parseInt(dataLine[CsvUtils.sectiuneData.sectiuneId]);
                    Integer aripaId = Integer.parseInt(dataLine[CsvUtils.sectiuneData.aripaId]);
                    Aripa aripa = biblioteca.getAripi().stream().filter(a -> a.getAripaId() == aripaId).findFirst().get();
                    String denumire = dataLine[CsvUtils.sectiuneData.denumire];
                    List<String> genuri = Arrays.stream(dataLine[CsvUtils.sectiuneData.genuri].split(";")).collect(Collectors.toList());
                    Double discount = Double.parseDouble(dataLine[CsvUtils.sectiuneData.discount]);
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
