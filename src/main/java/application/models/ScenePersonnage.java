package application.models;

import javax.persistence.*;

@Entity
@Table(name = "scene_personnage")
public class ScenePersonnage {
    @Id
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id_scene", nullable = false)
    private int idScene;

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "id_personnage", nullable = false)
    private int idPersonnage;

    public int getIdPersonnage() {
        return idPersonnage;
    }

    public void setIdPersonnage(int idPersonnage) {
        this.idPersonnage = idPersonnage;
    }

    public int getIdScene() {
        return idScene;
    }

    public void setIdScene(int idScene) {
        this.idScene = idScene;
    }
}