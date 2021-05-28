package repo.impl;

import administrativ.Aripa;
import administrativ.Sectiune;
import carte.Carte;
import com.mysql.cj.interceptors.QueryInterceptor;
import com.mysql.cj.protocol.Resultset;
import db.DbConnection;
import db.Queries;
import membri.Autor;
import membri.angajati.Bibliotecar;
import membri.angajati.ITist;
import repo.actions.BiblRepo;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BiblRepoImpl implements BiblRepo {
    private DbConnection dbConnection = DbConnection.getInstance();
    private static BiblRepoImpl instance = null;

    private BiblRepoImpl() {
    }

    public static BiblRepoImpl getInstance() {
        if (instance == null)
            instance = new BiblRepoImpl();
        return instance;
    }

    @Override
    public TreeSet<Carte> getCarti() {
        TreeSet<Carte> cartiDinDb = new TreeSet<>();
        TreeSet<Autor> autoriDinDb = getAutori();
        HashSet<Sectiune> sectiuniDinDB = getSectiuni();
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                /*
                *  public Carte(Integer id, Autor autor, List<Sectiune> sectiuni, String denumire, List<String> genuri, Double pret, Double discount, int nrExemplare) {
                 */
                int carteId = resultSet.getInt("carteId");
                HashSet<Sectiune> relevantSectiuni = new HashSet<>();
                try {
                    PreparedStatement preparedStatement1 = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_SEC_OF_BOOK);
                    preparedStatement1.setInt(1, carteId);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while(resultSet1.next()) {
                        int secId = resultSet1.getInt(1);
                        relevantSectiuni.add(sectiuniDinDB.stream().filter(s -> s.getSectiuneId() == secId).findFirst().get());
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }

                Autor relevantAutor = autoriDinDb.stream().filter(a -> {
                    try {
                        return a.getMembruId() == resultSet.getInt("autorId");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    return false;
                }).findFirst().get();
                Carte carte = new Carte(resultSet.getInt("carteId"),  relevantAutor, relevantSectiuni.stream().collect(Collectors.toList()),
                                        resultSet.getString("denumire"), Arrays.asList(resultSet.getString("genuri").split("[,]", 0)),
                                        resultSet.getDouble("pret"), resultSet.getDouble("discount"),
                                        resultSet.getInt("nrExemplare"));
                cartiDinDb.add(carte);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartiDinDb;
    }


    @Override
    public HashSet<Sectiune> getSectiuni() {
        HashSet<Sectiune> sectiuni = new HashSet<>();
        HashSet<Aripa> aripiDinDb = getAripi();
        try{
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_SECTIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                //    public Sectiune(Integer id, Aripa aripa, String denumire, List<String> genuri, Double discount) {
                int aripaId = resultSet.getInt("aripaId");
                Aripa aripa = aripiDinDb.stream().filter(a -> a.getAripaId() == aripaId).findFirst().get();
                Sectiune sectiune = new Sectiune(resultSet.getInt("sectiuneId"), aripa,
                                resultSet.getString("denumire"), Arrays.asList(resultSet.getString("genuri").split("[,]", 0)),
                                resultSet.getDouble("discount"));
                sectiuni.add(sectiune);
            }
         }catch (SQLException e) {
            e.printStackTrace();
        }

        return sectiuni;
    }

    @Override
    public HashSet<Aripa> getAripi() {
        HashSet<Aripa> aripi = new HashSet<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_ARIPI);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                //    public Aripa(Integer id, String denumire, double discount)
                Aripa aripa = new Aripa(resultSet.getInt("aripaId"), resultSet.getString("denumire"),
                                        resultSet.getDouble("discount"));
                aripi.add(aripa);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return aripi;
    }

    @Override
    public TreeSet<Autor> getAutori() {
        TreeSet<Autor> autori = new TreeSet<>();

        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_AUTORI);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
//                    public Autor(Integer id, String nume, String prenume, String statut, Date dataInscriere, Double popularitate, Double discount, String descriere) {
                    Autor autor = new Autor(resultSet.getInt("membruId"), resultSet.getString("nume"),
                                    resultSet.getString("prenume"), resultSet.getString("statut"),
                                    resultSet.getDate("data_inscriere"), resultSet.getDouble("popularitate"),
                                    resultSet.getDouble("discount"), resultSet.getString("descriere"));
                    autori.add(autor);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return autori;
    }

    @Override
    public HashSet<Bibliotecar> getBibilotecari() {
        HashSet<Bibliotecar> bibliotecari = new HashSet<>();
        HashSet<Sectiune> sectiuniDinDb = getSectiuni();
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_BIBLIOTECARI);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int membruId = resultSet.getInt("membruId");
                HashSet<Sectiune> relevantSectiuni = new HashSet<>();
                try {
                    PreparedStatement preparedStatement1 = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_BIBL_SEC);
                    preparedStatement1.setInt(1, membruId);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while(resultSet1.next()) {
                        int secId = resultSet1.getInt(2);
                        relevantSectiuni.add(sectiuniDinDb.stream().filter(s -> s.getSectiuneId() == secId).findFirst().get());
                    }
                }catch (SQLException e) {
                    e.printStackTrace();
                }
                //   public Bibliotecar(Integer id, String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission, List<Sectiune> sectiuni, Date dataNasterii) {
                Bibliotecar bibliotecar = new Bibliotecar(resultSet.getInt("membruId"), resultSet.getString("nume"), resultSet.getString("prenume"),
                                        resultSet.getString("statut"), resultSet.getDate("data_inscriere"),
                                        resultSet.getDouble("salariu"), resultSet.getDouble("commission"),
                         relevantSectiuni.stream().collect(Collectors.toList()), resultSet.getDate("data_nasterii"));
                bibliotecari.add(bibliotecar);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return bibliotecari;
    }

    @Override
    public HashSet<ITist> getITisti() {
        HashSet<ITist> iTisti = new HashSet<>();

        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.RETRIEVE_ALL_ITISTI);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                //   public ITist(Integer id, String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission, List<String> servere) {
                ITist iTist = new ITist(resultSet.getInt("membruId"), resultSet.getString("nume"),
                                        resultSet.getString("prenume"), resultSet.getString("statut"),
                                        resultSet.getDate("data_inscriere"), resultSet.getDouble("salariu"),
                                        0.0,
                                        Arrays.asList(resultSet.getString("servere").split("[,]", 0)));
                iTisti.add(iTist);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return iTisti;
    }

    @Override
    public int addCarte(Carte carte) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, carte.getDenumire());
            preparedStatement.setString(2, carte.getGenuri().toString());
            preparedStatement.setDouble(3, carte.getPret());
            preparedStatement.setDouble(4, carte.getDiscount());
            preparedStatement.setInt(5, carte.getTotalImprumuturi());
            preparedStatement.setInt(6, carte.getNrExemplare());
            preparedStatement.setInt(7, carte.getAutor().getMembruId());
            preparedStatement.executeUpdate();
            int resId = 0;
            resultSet = preparedStatement.getGeneratedKeys();
            for(Sectiune sectiune: carte.getSectiuni())
            {
                PreparedStatement preparedStatement1 = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_BOOK_SEC, Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setInt(1, carte.getCarteId());
                preparedStatement1.setInt(2, sectiune.getSectiuneId());
                preparedStatement1.executeUpdate();
            }
            while(resultSet.next()) {
                resId = Integer.parseInt(resultSet.getString(1));
            }
            return resId;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int addSectiune(Sectiune sectiune) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_SECTIUNE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sectiune.getDenumire());
            preparedStatement.setString(2, sectiune.getGenuri().toString());
            preparedStatement.setDouble(3, sectiune.getDiscount());
            preparedStatement.setInt(4, sectiune.getAripa().getAripaId());
            int resId = 0;
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                resId = Integer.parseInt(resultSet.getString(1));
            }
            return resId;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int addAripa(Aripa aripa) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_ARIPA, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, aripa.getDenumire());
            preparedStatement.setDouble(2, aripa.getDiscount());
            preparedStatement.executeUpdate();
            int resId = 0;
            resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                resId = Integer.parseInt(resultSet.getString(1));
            }
            return resId;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int addAutor(Autor autor) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_AUTOR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, autor.getNume());
            preparedStatement.setString(2, autor.getPrenume());
            preparedStatement.setString(3, autor.getStatut());
            preparedStatement.setDate(4, new java.sql.Date(autor.getDataInscriere().getTime()));
            preparedStatement.setDouble(5, autor.getPopularitate());
            preparedStatement.setDouble(6, autor.getDiscount());
            preparedStatement.setString(7, autor.getDescriere());
            preparedStatement.executeUpdate();
            int resId = 0;
            resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                resId = Integer.parseInt(resultSet.getString(1));
            }
            return resId;
        }catch (SQLException e) {
            e.printStackTrace();
            return  0;
        }
    }

    @Override
    public int addBibliotecar(Bibliotecar bibliotecar) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_BIBLIOTECAR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, bibliotecar.getNume());
            preparedStatement.setString(2, bibliotecar.getPrenume());
            preparedStatement.setString(3, bibliotecar.getStatut());
            preparedStatement.setDate(4, new java.sql.Date(bibliotecar.getDataInscriere().getTime()));
            preparedStatement.setDouble(5, bibliotecar.getSalariu());
            preparedStatement.setDouble(6, bibliotecar.getCommission());
            preparedStatement.setDate(7, new java.sql.Date(bibliotecar.getDataNasterii().getTime()));
            preparedStatement.executeUpdate();
            for(Sectiune sectiune: bibliotecar.getSectiuni()) {
                PreparedStatement preparedStatement1 = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_BIBLIOTECAR_SEC, Statement.RETURN_GENERATED_KEYS);
                preparedStatement1.setInt(1, bibliotecar.getMembruId());
                preparedStatement1.setInt(2, sectiune.getSectiuneId());
                preparedStatement1.executeUpdate();
            }
            int resId = 0;
            resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                resId = Integer.parseInt(resultSet.getString(1));
            }
            return resId;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int addItist(ITist iTist) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.INSERT_NEW_ITIST, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, iTist.getNume());
            preparedStatement.setString(2, iTist.getPrenume());
            preparedStatement.setString(3, iTist.getStatut());
            preparedStatement.setDate(4, new java.sql.Date(iTist.getDataInscriere().getTime()));
            preparedStatement.setDouble(5, iTist.getSalariu());
            preparedStatement.setString(6, iTist.getServere().toString());
            preparedStatement.executeUpdate();
            int resId = 0;
            resultSet = preparedStatement.getGeneratedKeys();
            while(resultSet.next()) {
                resId = Integer.parseInt(resultSet.getString(1));
            }
            return resId;
        }catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void updateAripa(Aripa aripa) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.UPDATE_ARIPA);
            preparedStatement.setString(1, aripa.getDenumire());
            preparedStatement.setDouble(2, aripa.getDiscount());
            preparedStatement.setInt(3, aripa.getAripaId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSectiune(Sectiune sectiune) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.UPDATE_SECTIUNE);
            preparedStatement.setString(1, sectiune.getDenumire());
            preparedStatement.setString(2, sectiune.getGenuri().toString());
            preparedStatement.setDouble(3, sectiune.getDiscount());
            preparedStatement.setInt(4, sectiune.getSectiuneId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateItist(ITist iTist) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.UPDATE_ITIST);
            preparedStatement.setString(1, iTist.getNume());
            preparedStatement.setString(2, iTist.getPrenume());
            preparedStatement.setString(3, iTist.getStatut());
            preparedStatement.setDate(4, new java.sql.Date(iTist.getDataInscriere().getTime()));
            preparedStatement.setDouble(5, iTist.getSalariu());
            preparedStatement.setString(6, iTist.getServere().toString());
            preparedStatement.setInt(7, iTist.getMembruId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAutor(Autor autor) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.UPDATE_AUTOR);
            preparedStatement.setString(1, autor.getNume());
            preparedStatement.setString(2, autor.getPrenume());
            preparedStatement.setString(3, autor.getStatut());
            preparedStatement.setDate(4, new java.sql.Date(autor.getDataInscriere().getTime()));
            preparedStatement.setDouble(5, autor.getPopularitate());
            preparedStatement.setDouble(6, autor.getDiscount());
            preparedStatement.setString(7, autor.getDescriere());
            preparedStatement.setInt(8, autor.getMembruId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCarte(Carte carte) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.DELETE_BOOK);
            preparedStatement.setInt(1, carte.getCarteId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBibliotecar(Bibliotecar bibliotecar) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.DELETE_BIBLIOTECAR);
            preparedStatement.setInt(1, bibliotecar.getMembruId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItist(ITist iTist) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.DELETE_ITIST);
            preparedStatement.setInt(1, iTist.getMembruId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAripa(Aripa aripa) {
        try {
            PreparedStatement preparedStatement = dbConnection.getDbConnection().prepareStatement(Queries.DELETE_ARIPA);
            preparedStatement.setInt(1, aripa.getAripaId());
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
