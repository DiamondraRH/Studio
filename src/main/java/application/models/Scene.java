package application.models;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "scene")
public class Scene {
    @Id
    @Column(name = "id_scene")
    private int idScene;

    @ManyToOne(targetEntity = Projet.class)
    @JoinColumn(name = "id_projet")
    private Projet projet;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "titre")
    private String titre;

    @Column(name = "ordre")
    private int ordre;

    @Column(name = "duration")
    private float duration;

    @Column(name = "debut_tournage_preferable")
    private Time debutTournagePreferable;

    @Column(name = "fin_tournage_preferable")
    private Time finTournagePreferable;

    @Column(name = "debut_tournage")
    private Timestamp debutTournage;

    @Column(name = "fin_tournage")
    private Timestamp finTournage;

    public int getIdScene() {
        return idScene;
    }

    public void setIdScene(int idScene) {
        this.idScene = idScene;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getOrdre() {
        return ordre;
    }

    public void setOrdre(int ordre) {
        this.ordre = ordre;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public Time getDebutTournagePreferable() {
        return debutTournagePreferable;
    }

    public void setDebutTournagePreferable(Time debutTournagePreferable) {
        this.debutTournagePreferable = debutTournagePreferable;
    }

    public Time getFinTournagePreferable() {
        return finTournagePreferable;
    }

    public void setFinTournagePreferable(Time finTournagePreferable) {
        this.finTournagePreferable = finTournagePreferable;
    }

    public Timestamp getDebutTournage() {
        return debutTournage;
    }

    public void setDebutTournage(Timestamp debutTournage) {
        this.debutTournage = debutTournage;
    }

    public Timestamp getFinTournage() {
        return finTournage;
    }

    public void setFinTournage(Timestamp finTournage) {
        this.finTournage = finTournage;
    }
}
