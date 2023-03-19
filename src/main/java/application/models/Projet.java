package application.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @Column(name = "id_projet")
    private int idProjet;

    @Column(name = "titre")
    private String titre;

    @Column(name = "debut_production")
    private Timestamp debutProduction;

    @Column(name = "fin_production")
    private Timestamp finProduction;

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Timestamp getDebutProduction() {
        return debutProduction;
    }

    public void setDebutProduction(Timestamp debutProduction) {
        this.debutProduction = debutProduction;
    }

    public Timestamp getFinProduction() {
        return finProduction;
    }

    public void setFinProduction(Timestamp finProduction) {
        this.finProduction = finProduction;
    }
}
