package membri.angajati;

import membri.Membru;

import java.util.Date;
import java.util.Objects;

public abstract class Angajat extends Membru {
    private Double salariu;
    private Double commission;
    private static int lastMembruId = 0;

    public Angajat(String nume, String prenume, String statut, Date dataInscriere, Double salariu) {
        super(nume, prenume, statut, dataInscriere, lastMembruId + 1);
        this.salariu = salariu;
        this.commission = 0.0;
        lastMembruId += 1;
    }

    public Angajat(String nume, String prenume, String statut, Date dataInscriere, Double salariu, Double commission) {
        super(nume, prenume, statut, dataInscriere, lastMembruId + 1);
        this.salariu = salariu;
        this.commission = commission;
        lastMembruId += 1;
    }

    public Double getSalariu() {
        return salariu;
    }

    public void setSalariu(Double salariu) {
        this.salariu = salariu;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double comision) {
        this.commission = comision;
    }

    @Override
    public String toString() {
        return super.toString() + "Angajat{" +
                "salariu=" + salariu +
                ", commission=" + commission +
                '}';
    }

    public void raiseSalaryPercent(double raisePercentage) {
        this.salariu += raisePercentage * this.salariu;
    }
    public void raiseSalary(double raise){
        this.salariu += raise;
    }

    public void changeCommission(double newCom){
        this.commission = newCom;
    }

    public Double totalIncome() {
        return this.salariu + this.salariu * this.commission;
    }
}
