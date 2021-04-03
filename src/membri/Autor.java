package membri;

import java.util.Date;
import java.util.Objects;

/**
 * popularitate se refera la cate carti de-ale autorului au fost citite recent
 * discount se refera la posibile reduceri la toate cartile unui autor
 */
public class Autor extends Membru{
    private Double popularitate;
    private static Integer lastMembruId = 0;
    private Double discount;
    private String descriere;

    public Autor(String nume, String prenume, String statut, Date dataInscriere, String descriere)
    {
        super(nume, prenume, statut, dataInscriere, lastMembruId + 1);
        this.popularitate = 0.0;
        this.discount = 0.0;
        this.descriere = descriere;
        lastMembruId += 1;
    }

    public Autor(String nume, String prenume, String statut, Date dataInscriere, Double popularitate, Double discount, String descriere) {
        super(nume, prenume, statut, dataInscriere, lastMembruId + 1);
        this.popularitate = popularitate;
        this.discount = discount;
        this.descriere = descriere;
        lastMembruId += 1;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Double getPopularitate() {
        return popularitate;
    }

    public void setPopularitate(Double popularitate) {
        this.popularitate = popularitate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Autor autor = (Autor) o;
        return Objects.equals(popularitate, autor.popularitate) && Objects.equals(discount, autor.discount) && Objects.equals(descriere, autor.descriere);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), popularitate, discount, descriere);
    }

    @Override
    public String toString() {
        return "Autor{" +
                "popularitate=" + popularitate +
                ", discount=" + discount +
                ", descriere='" + descriere + '\'' +
                '}';
    }


    public void modifyPopularitate(double change) {
        this.setPopularitate(this.getPopularitate() + change);
    }
}
