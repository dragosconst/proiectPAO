package utils;

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
                if(dataLine[0].equals("autor")){
                    continue;
                }
                else{
                    Autor autor = biblioteca.getAutori().stream().filter(a -> a.getMembruId().toString().equals(dataLine[0])).findFirst().get();
                    List<Sectiune> sectiuni = biblioteca.getSectiuni().stream().filter(s -> dataLine[1].contains(Integer.toString(s.getSectiuneId()))).collect(Collectors.toList());
                    String denumire = dataLine[2];
                    List<String> genuri = Arrays.stream(dataLine[3].split(";")).collect(Collectors.toList());
                    Double pret = Double.parseDouble(dataLine[4]);
                    Double discount = Double.parseDouble(dataLine[5]);
                    int nrExemplare = Integer.parseInt(dataLine[6]);
                    Carte carte = new Carte(autor, sectiuni, denumire, genuri, pret, discount, nrExemplare);
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
                if(dataLine[0].equals("nume")){
                    continue;
                }
                else{
                    String nume = dataLine[0];
                    String prenume = dataLine[1];
                    String statut = dataLine[2];
                    Date dataInscriere = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[3]);
                    Double popularitate = Double.parseDouble(dataLine[4]);
                    Double discount = Double.parseDouble(dataLine[5]);
                    String descriere = dataLine[6];
                    Autor autor = new Autor(nume, prenume, statut, dataInscriere, popularitate, discount, descriere);
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
                if(dataLine[0].equals("nume")){
                    continue;
                }
                else{
                    String nume = dataLine[0];
                    String prenume = dataLine[1];
                    String statut = dataLine[2];
                    Date dataInscriere = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[3]);
                    Double salariu = Double.parseDouble(dataLine[4]);
                    Double commission = Double.parseDouble(dataLine[5]);
                    Integer type = Integer.parseInt(dataLine[6]);
                    if (type == 2) {
                        List<String> servere = Arrays.stream(dataLine[7].split(";")).collect(Collectors.toList());
                        ITist iTist = new ITist(nume, prenume, statut, dataInscriere, salariu, commission, servere);
                        angajati.add(iTist);
                    }
                    else{
                        List<Sectiune> sectiuni = biblioteca.getSectiuni().stream().filter(s -> dataLine[8].contains(Integer.toString(s.getSectiuneId()))).collect(Collectors.toList());
                        Date dataNasterii = new SimpleDateFormat("dd/MM/yyyy").parse(dataLine[9]);
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
}
