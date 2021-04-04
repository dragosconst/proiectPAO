package administrativ;

import java.util.List;
import java.util.Objects;

public class Aripa {
    private final int aripaId;
    private static int lastAripaId = 0;
    private String denumire;
    private double discount;

    public Aripa(String denumire, double discount) {
        this.aripaId = lastAripaId + 1;
        lastAripaId += 1;
        this.denumire = denumire;
        this.discount = discount;
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

    public double getDiscount() {
        return discount;
    }


    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aripa aripa = (Aripa) o;
        return aripaId == aripa.aripaId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(aripaId);
    }

    @Override
    public String toString() {
        return "Aripa " + this.getDenumire();
    }
}
