package membri;

import java.util.Date;
import java.util.Objects;


/**
 * Statut se refera la statutul acceptarii ca membru. Are urm valori posibile:
 *                                                              - in asteptare
 *                                                              - membru deplin
 *                                                              - respins
 */
public abstract class Membru {
    private String nume;
    private final Integer membruId;
    private String prenume;
    private String statut;
    private Date dataInscriere;
    private final static String WAITING = "in asteptare";
    private final static String ACCEPTED = "membru deplin";
    private final static String REJECTED = "respins";

    public Membru(String nume, String prenume, String statut, Date dataInscriere, Integer membruId) {
        this.nume = nume;
        this.prenume = prenume;
        this.statut = statut;
        this.dataInscriere = dataInscriere;
        this.statut = WAITING;
        this.membruId = membruId;
    }

    public static String getWAITING() {
        return WAITING;
    }

    public static String getACCEPTED() {
        return ACCEPTED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Membru membru = (Membru) o;
        return membruId.equals(membru.membruId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(membruId);
    }

    public static String getREJECTED() {
        return REJECTED;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getStatut() {
        return statut;
    }

    public Date getDataInscriere() {
        return dataInscriere;
    }

    public void setDataInscriere(Date dataInscriere) {
        this.dataInscriere = dataInscriere;
    }

    public Integer getMembruId() {
        return membruId;
    }


    @Override
    public String toString() {
        return "Membru{" +
                "Nume='" + nume + '\'' +
                ", Prenume='" + prenume + '\'' +
                ", Statut='" + statut + '\'' +
                ", DataInscriere=" + dataInscriere +
                '}';
    }

    protected void acceptMember() {
        this.statut = ACCEPTED;
    }
    protected  void rejectMember(){
        this.statut = REJECTED;
    }

}
