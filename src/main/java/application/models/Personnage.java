package application.models;

import javax.persistence.*;

@Entity
@Table(name = "personnage")
public class Personnage {
    @Id
    @Column(name = "id_personnage")
    private int idPersonnage;

    @Column(name = "id_projet")
    private int idProjet;

    @Column(name = "id_acteur")
    private int idActeur;

    @Column(name = "nom")
    private String nom;

    public int getIdPersonnage() {
        return idPersonnage;
    }

    public void setIdPersonnage(int idPersonnage) {
        this.idPersonnage = idPersonnage;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public int getIdActeur() {
        return idActeur;
    }

    public void setIdActeur(int idActeur) {
        this.idActeur = idActeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
