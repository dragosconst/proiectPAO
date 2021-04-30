package utils;

import administrativ.Sectiune;
import biblioteca.Biblioteca;
import membri.angajati.Bibliotecar;
import membri.angajati.ITist;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CsvWriter {
    public static void writeCarti(Biblioteca biblioteca) {
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\carti.csv";
        try(PrintWriter printWriter = new PrintWriter(filePath)) {
            StringBuilder stringBuilder = new StringBuilder();

            // csv header
            reportRecord(stringBuilder, "carteId", "autor", "sectiuni", "titlu", "gen", "pret", "discount", "nrExemplare");

            biblioteca.getCarti().forEach(carte -> {
                reportRecord(stringBuilder, Integer.toString(carte.getCarteId()),
                        carte.getAutor().getMembruId().toString(),
                        String.join(";", carte.getSectiuni().stream().map(Sectiune::getSectiuneId).collect(Collectors.toList()).toString()),
                        carte.getDenumire(),
                        String.join(";",carte.getGenuri()),
                        carte.getPret().toString(),
                        carte.getDiscount().toString(),
                        Integer.toString(carte.getNrExemplare()));
            });

            printWriter.write(stringBuilder.toString());
            System.out.println("csv generated!");
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeAutori(Biblioteca biblioteca) {
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\autori.csv";
        try(PrintWriter printWriter = new PrintWriter(filePath)) {
            StringBuilder stringBuilder = new StringBuilder();

            // csv header
            reportRecord(stringBuilder, "autorId", "nume", "prenume", "statut", "dataInscriere", "popularitate", "discount", "descriere");

            biblioteca.getAutori().forEach(autor -> {
                reportRecord(stringBuilder, autor.getMembruId().toString(),
                        autor.getNume(),
                        autor.getPrenume(),
                        autor.getStatut(),
                        new SimpleDateFormat("dd/MM/yyyy").format(autor.getDataInscriere()),
                        autor.getPopularitate().toString(),
                        autor.getDiscount().toString(),
                        autor.getDescriere());
            });

            printWriter.write(stringBuilder.toString());
            System.out.println("csv generated!");
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeAngajati(Biblioteca biblioteca) {
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\angajati.csv";
        try(PrintWriter printWriter = new PrintWriter(filePath)) {
            StringBuilder stringBuilder = new StringBuilder();

            // csv header
            reportRecord(stringBuilder, "angajatId", "nume", "prenume", "statut", "dataInscriere", "salariu", "comision", "tip", "servere", "sectiuni", "dataNasterii");

            biblioteca.getAngajati().forEach(angajat -> {
                reportRecord(stringBuilder, angajat.getMembruId().toString(),
                        angajat.getNume(),
                        angajat.getPrenume(),
                        angajat.getStatut(),
                        new SimpleDateFormat("dd/MM/yyyy").format(angajat.getDataInscriere()),
                        angajat.getSalariu().toString(),
                        angajat.getCommission().toString(),
                        angajat.getClass() == Bibliotecar.class ? "1" : "2",
                        angajat.getClass() == ITist.class ? String.join(";", ((ITist) angajat).getServere()) : " ",
                        angajat.getClass() == Bibliotecar.class ?
                                String.join(";", ((Bibliotecar) angajat).getSectiuni().stream().map(Sectiune::getSectiuneId).collect(Collectors.toList()).toString())
                                : " ",
                        angajat.getClass() == Bibliotecar.class ? new SimpleDateFormat("dd/MM/yyyy").format(((Bibliotecar) angajat).getDataNasterii()) : " "
                );
            });

            printWriter.write(stringBuilder.toString());
            System.out.println("csv generated!");
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeAripi(Biblioteca biblioteca) {
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\aripi.csv";
        try(PrintWriter printWriter = new PrintWriter(filePath)) {
            StringBuilder stringBuilder = new StringBuilder();

            // csv header
            reportRecord(stringBuilder, "aripaid", "denumire", "discount");

            biblioteca.getAripi().forEach(aripa -> {
                reportRecord(stringBuilder, Integer.toString(aripa.getAripaId()),
                        aripa.getDenumire(),
                        Double.toString(aripa.getDiscount())
                        );
            });

            printWriter.write(stringBuilder.toString());
            System.out.println("csv generated!");
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void writeSectiuni(Biblioteca biblioteca) {
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\sectiuni.csv";
        try(PrintWriter printWriter = new PrintWriter(filePath)) {
            StringBuilder stringBuilder = new StringBuilder();

            // csv header
            reportRecord(stringBuilder, "sectiuneid", "aripaid", "denumire", "genuri", "discount");

            biblioteca.getSectiuni().forEach(sectiune -> {
                reportRecord(stringBuilder, Integer.toString(sectiune.getSectiuneId()),
                        Integer.toString(sectiune.getAripa().getAripaId()),
                        sectiune.getDenumire(),
                        String.join(";", sectiune.getGenuri()),
                        sectiune.getDiscount().toString()
                );
            });

            printWriter.write(stringBuilder.toString());
            System.out.println("csv generated!");
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    // wrapper function that calls all the other write functions
    public static void writeBiblioteca(Biblioteca biblioteca) {
        writeAripi(biblioteca);
        writeSectiuni(biblioteca);
        writeAutori(biblioteca);
        writeAngajati(biblioteca);
        writeCarti(biblioteca);
    }

    public static StringBuilder reportRecord(StringBuilder sb, String... parameters) {
        for(String param: parameters)
        {
            sb.append(param);
            sb.append(",");
        }
        sb.append("\n");
        return sb;
    }
}
