package db;

public class Queries {
    public static final String RETRIEVE_ALL_BOOKS = "SELECT * FROM proiect_pao.carte;";
    public static final String RETRIEVE_ALL_SEC_OF_BOOK = "\tSELECT s.sectiuneId FROM proiect_pao.sectiune s, proiect_pao.carte_sectiune cs\n" +
            "    WHERE s.sectiuneId = cs.sectiuneId and cs.carteId = ?;";
//            "SELECT * FROM proiect_pao.carte carte,\n" +
//            "proiect_pao.carte_sectiune cs, proiect_pao.sectiune sectiune\n" +
//            "WHERE carte.carteId = cs.carteId AND sectiune.sectiuneId = cs.sectiuneId;"; // il fac sa selecteze special si sectiunile
    public static final String RETRIEVE_ALL_SECTIONS = "SELECT * FROM proiect_pao.sectiune s, proiect_pao.aripa a\n" +
            "WHERE s.aripaId = a.aripaId;";
    public static final String RETRIEVE_ALL_BIBLIOTECARI = "SELECT * FROM proiect_pao.bibliotecar\n" +
            ";";
    public static final String RETRIEVE_ALL_BIBL_SEC = "SELECT * FROM proiect_pao.bibliotecar_sectiuni bs\n" +
                                                        "WHERE bs.bibliotecarId = ?;";
    public static final String RETRIEVE_ALL_ITISTI = "SELECT * FROM itist;";
    public static final String RETRIEVE_ALL_ARIPI = "SELECT * FROM aripa";
    public static final String RETRIEVE_ALL_AUTORI = "SELECT * FROM proiect_pao.autor;";

    public static final String INSERT_NEW_BOOK = "INSERT INTO carte(carteId, denumire, genuri, pret, discount, totalImprumuturi, nrExemplare, autorId)\n" +
                                                "VALUES(null, ?, ?, ?, ?, ?, ?, ?);\n";
    public static final String INSERT_NEW_BOOK_SEC = "INSERT INTO carte_sectiune(carteId, sectiuneId) VALUES(?,?);";
    public static final String INSERT_NEW_ARIPA = "INSERT INTO aripa(aripaId, denumire, discount)\n" +
                                                    "VALUES (null, ?,?);";
    public static final String INSERT_NEW_AUTOR = "INSERT INTO autor(membruId, nume, prenume, statut, data_inscriere, popularitate, discount, descriere)\n" +
                                                    "VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
    public static final String INSERT_NEW_BIBLIOTECAR = "INSERT INTO bibliotecar(membruId, nume, prenume, statut, data_inscriere, salariu, commission, data_nasterii)\n" +
                                                        "VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
    public static final String INSERT_NEW_BIBLIOTECAR_SEC = "INSERT INTO bibliotecar_sectiuni(bibliotecarId, sectiuneId)\n" +
                                                            "VALUES(?, ?);";
    public static final String INSERT_NEW_ITIST = "INSERT INTO itist(membruId, nume, prenume, statut, data_inscriere, salariu, servere)\n" +
                                                   "VALUES(null, ?, ?, ?, ?, ?, ?);";
    public static final String INSERT_NEW_SECTIUNE = "INSERT INTO sectiune(sectiuneId, denumire, genuri, discount, aripaId)\n" +
                                                    "VALUES(null, ?, ?, ?, ?)";
}
