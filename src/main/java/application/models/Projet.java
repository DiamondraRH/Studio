package application.models;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @Column(name = "id_projet")
    private Integer idProjet;

    @Column(name = "titre")
    private String titre;

    @Column(name = "debut_production")
    private Timestamp debutProduction;

    @Column(name = "fin_production")
    private Timestamp finProduction;

    public Integer getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Integer idProjet) {
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

    public Criterion getConditionIdProject(){
        return Restrictions.eq("projet",this.getIdProjet());
    }



    public Criterion getConditionScenesNotUnplanned(){
        return Restrictions.and(getConditionIdProject(),Scene.getConditionUnplanned());
//        return null;
    }

    public Criterion getConditionScenesPlanned(){
        return getConditionIdProject();
//        return Restrictions.and(getConditionIdProject(),Scene.getConditionUnplanned());
//        return null;
    }

    public Scene provideSceneWithProject(){
        Scene scene=new Scene();
        scene.setProjet(this);
        return scene;
    }
}
