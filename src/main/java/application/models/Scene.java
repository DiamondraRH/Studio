package application.models;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    @JoinColumn(name = "id_scenariste")
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
    @Column(name = "estimation_tournage")
    private Time estimationTournage;

    @Column(name = "debut_tournage")
    private Timestamp debutTournage;

    @Column(name = "fin_tournage")
    private Timestamp finTournage;

    @ManyToOne(targetEntity = Plateau.class)
    @JoinColumn(name = "id_plateau")
    private Plateau plateau;

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

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Time getEstimationTournage() {
        return estimationTournage;
    }

    public void setEstimationTournage(Time estimationTournage) {
        this.estimationTournage = estimationTournage;
    }

    public static void orderByPreferableAsc(ArrayList<Scene> scene){
        Comparator<Scene> comparateurPreferable=new Comparator<Scene>() {
            @Override
            public int compare(Scene c1, Scene c2) {
                if(c1.getDebutTournagePreferable().compareTo(c2.getDebutTournagePreferable())==0)
                    return c1.getFinTournagePreferable().compareTo(c2.getFinTournagePreferable());
                return c1.getDebutTournagePreferable().compareTo(c2.getDebutTournagePreferable());
            }
        };
        Collections.sort(scene,comparateurPreferable);
    }
    public static Scene firstSceneNotDate(ArrayList<Scene> scene){
       Time t= scene.get(0).getDebutTournagePreferable();
        int indice=0;
        for(int r=0;r<scene.size();r++ ){
            if(scene.get(r).getDebutTournage()!=null&&t.after(scene.get(r).getDebutTournagePreferable()))
                indice=r;
        }
        return scene.get(indice);
    }
}
