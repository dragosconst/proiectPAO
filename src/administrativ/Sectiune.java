package administrativ;

import carte.Carte;

import java.util.List;
import java.util.Objects;

public class Sectiune {
    private final int sectiuneId;
    private final int aripaId;
    private static int lastSectiuneId = 0;
    private String denumire;
    private List<String> genuri;
    private Double discount;

    public Sectiune(int aripaId, String denumire, List<String> genuri, Double discount) {
        this.sectiuneId = lastSectiuneId + 1;
        lastSectiuneId += 1;
        this.aripaId = aripaId;
        this.denumire = denumire;
        this.genuri = genuri;
        this.discount = discount;
    }

    public int getSectiuneId() {
        return sectiuneId;
    }

    public int getAripaId() {
        return aripaId;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<String> getGenuri() {
        return genuri;
    }

    public void setGenuri(List<String> genuri) {
        this.genuri = genuri;
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
        Sectiune sectiune = (Sectiune) o;
        return sectiuneId == sectiune.sectiuneId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectiuneId);
    }

    @Override
    public String toString() {
        return this.denumire;
    }


}
