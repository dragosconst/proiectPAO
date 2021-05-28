package repo.actions;

import administrativ.Aripa;
import administrativ.Sectiune;
import carte.Carte;
import membri.Autor;
import membri.angajati.Bibliotecar;
import membri.angajati.ITist;

import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public interface BiblRepo {

    // METODE DE SELECT
    TreeSet<Carte> getCarti();
    HashSet<Sectiune> getSectiuni();
    HashSet<Aripa> getAripi();
    TreeSet<Autor> getAutori();
    HashSet<Bibliotecar> getBibilotecari();
    HashSet<ITist> getITisti();

    // METODE DE INSERT
    int addCarte(Carte carte);
    int addSectiune(Sectiune sectiune);
    int addAripa(Aripa aripa);
    int addAutor(Autor autor);
    int addBibliotecar(Bibliotecar bibliotecar);
    int addItist(ITist iTist);

    void updateAripa(Aripa aripa);
    void updateSectiune(Sectiune sectiune);
    void updateItist(ITist iTist);
    void updateAutor(Autor autor);

    void deleteCarte(Carte carte);
    void deleteBibliotecar(Bibliotecar bibliotecar);
    void deleteItist(ITist iTist);
    void deleteAripa(Aripa aripa);
}
