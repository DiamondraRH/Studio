package application.models;

import javax.persistence.*;

@Entity
@Table(name = "type_plateau")
public class TypePlateau {
    @Id
    @Column(name = "id_type_plateau")
    private int idTypePlateau;

    @Column(name = "libelle_type_plateau")
    private String libelleTypePlateau;

    public int getIdTypePlateau() {
        return idTypePlateau;
    }

    public void setIdTypePlateau(int idTypePlateau) {
        this.idTypePlateau = idTypePlateau;
    }

    public String getLibelleTypePlateau() {
        return libelleTypePlateau;
    }

    public void setLibelleTypePlateau(String libelleTypePlateau) {
        this.libelleTypePlateau = libelleTypePlateau;
    }
}
