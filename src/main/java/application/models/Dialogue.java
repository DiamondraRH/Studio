package application.models;

import javax.persistence.*;

@Entity
@Table(name = "dialogue")
public class Dialogue {
    @Id
    @Column(name = "id_dialogue")
    private int idDialogue;

    @Column(name = "ordre")
    private int ordre;

    @ManyToOne(targetEntity = Scene.class)
    @JoinColumn(name = "id_scene")
    private Scene scene;

    @ManyToOne(targetEntity = Personnage.class)
    @JoinColumn(name = "id_personnage")
    private int idPersonnage;

    @Column(name = "dialogue")
    private String dialogue;

    @Column(name = "action")
    private String action;

    public int getIdDialogue() {
        return idDialogue;
    }

    public void setIdDialogue(int idDialogue) {
        this.idDialogue = idDialogue;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public int getIdPersonnage() {
        return idPersonnage;
    }

    public void setIdPersonnage(int idPersonnage) {
        this.idPersonnage = idPersonnage;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
