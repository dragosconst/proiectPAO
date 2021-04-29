package utils;

import biblioteca.Biblioteca;

import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriter {
    public static void writeCarti(Biblioteca biblioteca) {
        String filePath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\carti.csv";
        try(PrintWriter printWriter = new PrintWriter(filePath))
        {
            StringBuilder stringBuilder = new StringBuilder();

            // csv header
            reportRecord(stringBuilder, "titlu", "autor", "gen");

            biblioteca.getCarti().forEach(carte -> {
                reportRecord(stringBuilder, carte.getDenumire(), carte.getAutor().getNume() + " " + carte.getAutor().getPrenume(),
                        String.join("",carte.getGenuri()));
            });

            printWriter.write(stringBuilder.toString());
            System.out.println("csv generated!");
        }catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static StringBuilder reportRecord(StringBuilder sb, String... parameters)
    {
        for(String param: parameters)
        {
            sb.append(param);
            sb.append(",");
        }
        sb.append("\n");
        return sb;
    }
}
