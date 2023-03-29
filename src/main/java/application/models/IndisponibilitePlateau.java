package application.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "indisponibilite_plateau")
public class IndisponibilitePlateau {
    @Id
    @Column(name = "id_plateau", nullable = false)
    private int idPlateau;

    @Column(name = "motif")
    private String motif;

    @Column(name = "date_debut")
    private Timestamp dateDebut;

    @Column(name = "date_fin")
    private Timestamp dateFin;

    public int getIdPlateau() {
        return idPlateau;
    }

    public void setIdPlateau(int idPlateau) {
        this.idPlateau = idPlateau;
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
