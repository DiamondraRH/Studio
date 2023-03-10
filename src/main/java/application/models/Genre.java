package application.models;

import javax.persistence.*;

@Entity
@Table(name = "genre")
public class Genre {
    @Id
    @Column(name = "id_genre")
    private int idGenre;

    @Column(name = "libelle_genre")
    private String libelleGenre;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getLibelleGenre() {
        return libelleGenre;
    }

    public void setLibelleGenre(String libelleGenre) {
        this.libelleGenre = libelleGenre;
    }
}
