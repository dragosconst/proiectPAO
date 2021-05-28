package utils;

interface CsvUtils {
    public static final String folderPath = "C:\\Users\\Dragos\\IdeaProjects\\proiectPAO\\csv\\";
    public static final String fileExtension = ".csv";
    public static final String carti = "carti";
    public static final String autori = "autori";
    public static final String angajati = "angajati";
    public static final String aripi = "aripi";
    public static final String sectiuni = "sectiuni";

    public static class carteData {
        public static final Integer carteId = 0;
        public static final Integer autor = 1;
        public static final Integer sectiuni = 2;
        public static final Integer denumire = 3;
        public static final Integer genuri = 4;
        public static final Integer pret = 5;
        public static final Integer discount = 6;
        public static final Integer nrExemplare = 7;
    }

    public static class autorData {
        public static final Integer autorId = 0;
        public static final Integer nume = 1;
        public static final Integer prenume = 2;
        public static final Integer statut = 3;
        public static final Integer dataInscriere = 4;
        public static final Integer popularitate = 5;
        public static final Integer discount = 6;
        public static final Integer descriere = 7;
    }

    public static class angajatData {
        public static final Integer angajatId = 0;
        public static final Integer nume = 1;
        public static final Integer prenume = 2;
        public static final Integer statut = 3;
        public static final Integer dataInscriere = 4;
        public static final Integer salariu = 5;
        public static final Integer commission = 6;
        public static final Integer type = 7;
        public static final Integer servere = 8;
        public static final Integer sectiuni = 9;
        public static final Integer dataNasterii = 10;
    }

    public static class aripaData {
        public static final Integer aripaId = 0;
        public static final Integer denumire = 1;
        public static final Integer discount = 2;
    }

    public static class sectiuneData {
        public static final Integer sectiuneId = 0;
        public static final Integer aripaId = 1;
        public static final Integer denumire = 2;
        public static final Integer genuri = 3;
        public static final Integer discount = 4;
    }
}