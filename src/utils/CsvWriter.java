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
            reportRecord(stringBuilder, "autor", "sectiuni", "titlu", "gen", "pret", "discount", "nrExemplare");

            biblioteca.getCarti().forEach(carte -> {
                reportRecord(stringBuilder, carte.getAutor().getMembruId().toString(),
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
            reportRecord(stringBuilder, "nume", "prenume", "statut", "dataInscriere", "popularitate", "discount", "descriere");

            biblioteca.getAutori().forEach(autor -> {
                reportRecord(stringBuilder, autor.getNume(),
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
            reportRecord(stringBuilder, "nume", "prenume", "statut", "dataInscriere", "salariu", "comision", "tip", "servere", "sectiuni", "dataNasterii");

            biblioteca.getAngajati().forEach(angajat -> {
                reportRecord(stringBuilder, angajat.getNume(),
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
