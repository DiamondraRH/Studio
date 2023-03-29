package application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "indisponibilite_acteur")

public class IndisponibiliteActeur {
    @Id
    @Column(name = "id_acteur", nullable = false)
    private int idActeur;

    @Column(name = "motif")
    private String motif;

    @Column(name = "date_debut")
    private Timestamp dateDebut;

    @Column(name = "date_fin")
    private Timestamp dateFin;

    public int getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(int idActeur) {
        this.idActeur = idActeur;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Timestamp getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Timestamp getDateFin() {
        return dateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        this.dateFin = dateFin;
    }
}
