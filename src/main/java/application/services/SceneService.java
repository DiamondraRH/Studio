package application.services;

import application.data.HibernateDAO;
import application.models.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.util.*;

@Service
public class SceneService {
    @Autowired
    private HibernateDAO dao;

    public void validate(Scene scene) throws Exception {
        update(scene);

        IndisponibilitePlateau indisponibilitePlateau = new IndisponibilitePlateau();
        indisponibilitePlateau.setIdPlateau(scene.getPlateau().getIdPlateau());
        indisponibilitePlateau.setDateDebut(scene.getDebutTournage());
        indisponibilitePlateau.setDateFin(scene.getFinTournage());
        indisponibilitePlateau.setMotif("tournage");
        dao.save(indisponibilitePlateau);

        List<Personnage> personnages = findActorByScene(scene.getIdScene());
        IndisponibiliteActeur indisponibiliteActeur;
        for(Personnage p : personnages) {
            indisponibiliteActeur = new IndisponibiliteActeur();
            indisponibiliteActeur.setIdActeur(p.getIdActeur());
            indisponibiliteActeur.setDateDebut(scene.getDebutTournage());
            indisponibiliteActeur.setDateFin(scene.getFinTournage());
            indisponibiliteActeur.setMotif("tournage");
            dao.save(indisponibiliteActeur);
        }
    }

    private List<Personnage> findActorByScene(int idScene) {
        Session session = dao.getSessionFactory().openSession();
        List<Personnage> personnages = null;

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Personnage> criteriaQuery = builder.createQuery(Personnage.class);
            // init query
            Root<Personnage> model = criteriaQuery.from(Personnage.class);

            criteriaQuery.where((model.get("idPersonnage").in(idPersonnagesByScene(idScene))));
            personnages = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return personnages;
    }

    private List<ScenePersonnage> findPersonnageByScene(int idScene) {
        Session session = dao.getSessionFactory().openSession();
        List<ScenePersonnage> scenePersonnages = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ScenePersonnage> criteriaQuery = builder.createQuery(ScenePersonnage.class);
            // init query
            Root<ScenePersonnage> model = criteriaQuery.from(ScenePersonnage.class);
            criteriaQuery.where(builder.equal(model.get("idScene"),idScene));
            scenePersonnages = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return scenePersonnages;
    }

    private List<Integer> idPersonnagesByScene(int idScene) {
        List<ScenePersonnage> scenePersonnages = findPersonnageByScene(idScene);
        List<Integer> ids = new ArrayList<>();
        for (ScenePersonnage d: scenePersonnages) {
            ids.add(d.getIdPersonnage());
        }
        return ids;
    }

    public void update(Scene scene) throws Exception {
        dao.update(scene);
    }

    public Scene findById(String id) throws Exception {
        return dao.findOneById(new Scene(),Integer.valueOf(id));
    }

    public List<Scene> findAllPlannedScene(Timestamp dateDebut , Timestamp dateFin) {
        Session session = dao.getSessionFactory().openSession();
        List<Scene> sceneList = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Scene> criteriaQuery = builder.createQuery(Scene.class);
            // init query
            Root<Scene> model = criteriaQuery.from(Scene.class);
            // condition(s)
            criteriaQuery.where(builder.between(model.get("debutTournage"),dateDebut,dateFin));
            // ordering
            // criteriaQuery.orderBy(builder.desc(model.get("projet").get("id_projet")));
            sceneList = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return sceneList;
    }
    public  ArrayList<Scene> findByProject(Projet projet) throws Exception{
        ArrayList<Scene> ls=dao.findAll(new Scene());
        ArrayList<Scene> turn=new ArrayList<>();
        for(Scene sc:ls){
            if(sc.getProjet().getIdProjet()==projet.getIdProjet())
                turn.add(sc);
        }
        return turn;
    }

    public List<Scene> findAllUnplannedScene() {
        Session session = dao.getSessionFactory().openSession();
        List<Scene> sceneList = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Scene> criteriaQuery = builder.createQuery(Scene.class);
            // init query
            Root<Scene> model = criteriaQuery.from(Scene.class);
            // condition(s)
            criteriaQuery.where(builder.isNull(model.get("debutTournage")));
            // ordering
            // criteriaQuery.orderBy(builder.desc(model.get("projet").get("id_projet")));
            sceneList = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return sceneList;
    }

    public List<IndisponibilitePlateau> findAllIndisponibilitePlateauBetween(Timestamp dateDebut, Timestamp dateFin) {
        Session session = dao.getSessionFactory().openSession();
        List<IndisponibilitePlateau> indisponibilitePlateauList = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<IndisponibilitePlateau> criteriaQuery = builder.createQuery(IndisponibilitePlateau.class);
            // init query
            Root<IndisponibilitePlateau> model = criteriaQuery.from(IndisponibilitePlateau.class);
            // condition(s)
            Predicate greaterThanOrEqualToDateDebut = builder.greaterThanOrEqualTo(model.get("dateDebut"), dateDebut);
            Predicate lesserThanOrEqualToDateFin = builder.lessThanOrEqualTo(model.get("dateFin"), dateFin);
            criteriaQuery.select(model).where(builder.and(greaterThanOrEqualToDateDebut, lesserThanOrEqualToDateFin));
            //fetch result
            indisponibilitePlateauList = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return indisponibilitePlateauList;
    }

    public List<IndisponibiliteActeur> findAllIndisponibiliteActeurBetween(Timestamp dateDebut, Timestamp dateFin) {
        Session session = dao.getSessionFactory().openSession();
        List<IndisponibiliteActeur> indisponibiliteActeurList = null;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<IndisponibiliteActeur> criteriaQuery = builder.createQuery(IndisponibiliteActeur.class);
            // init query
            Root<IndisponibiliteActeur> model = criteriaQuery.from(IndisponibiliteActeur.class);
            // condition(s)
            Predicate greaterThanOrEqualToDateDebut = builder.greaterThanOrEqualTo(model.get("dateDebut"), dateDebut);
            Predicate lesserThanOrEqualToDateFin = builder.lessThanOrEqualTo(model.get("dateFin"), dateFin);
            criteriaQuery.select(model).where(builder.and(greaterThanOrEqualToDateDebut, lesserThanOrEqualToDateFin));
            // fetch result
            indisponibiliteActeurList = session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return indisponibiliteActeurList;
    }

    public ArrayList<Scene> suggestion(ArrayList<Scene> liste,Timestamp debut,Timestamp fin) throws  Exception{
        ArrayList<Scene> sugg=new ArrayList<>();
        List<IndisponibilitePlateau> idPlateau = findAllIndisponibilitePlateauBetween(debut, fin);
        List<IndisponibiliteActeur> idActeur = findAllIndisponibiliteActeurBetween(debut, fin);
        int tour=0;
        float duree=(float)0;
        HashMap<Plateau, ArrayList<Scene>> map=Plateau.sceneParPlateau(dao.findAll(new Plateau()),liste);
        ArrayList<Plateau> plateau=Plateau.orderByLongLat(liste.get(0),map);
        while(sugg.size()!=liste.size()){
            if(tour==1) {
                debut = changeDate(debut);
                plateau=Plateau.orderByLongLat(Scene.firstSceneNotDate(liste),map);
                duree=0;
            }
            tour=1;
            for(Plateau p:plateau){
                ArrayList<Scene> ls=map.get(p);
                for(Scene sc: ls){
                    if(sc.getDebutTournage()==null) {
                        Object[] ob=changeDateTournage(sc, debut, sugg, duree,tour, idPlateau, idActeur);
                        debut=(Timestamp) ob[0];
                        duree=(float)ob[1];
                        tour=(int)ob[2];
                    }
                }
            }
        }
        this.checkIsPossible(fin,sugg);
        return sugg;
    }

    private Object[] changeDateTournage(Scene sc,Timestamp dt,ArrayList<Scene> sugg,float duree,int tour,
                                        List<IndisponibilitePlateau> iPlateau,List<IndisponibiliteActeur> iActeur) throws Exception{
        ArrayList<Personnage> acteur=dao.findAll(new Personnage());
        Time last=new Time(dt.getHours(),dt.getMinutes(),dt.getSeconds());
        float duration=sc.getEstimationTournage().getHours();
        duration=duration+(sc.getEstimationTournage().getMinutes()/60);
        if(last.before(sc.getFinTournagePreferable())&&((duree+duration)<=8.0)&&
                dispoActeur(dt,iActeur,acteur)&&dispoPlateau(dt,iPlateau,sc.getPlateau())){
            if(last.after(sc.getDebutTournagePreferable())||last.compareTo(sc.getDebutTournagePreferable())==0||
                    sc.getDebutTournagePreferable()==null)
                sc.setDebutTournage(dt);
            else
                sc.setDebutTournage(new Timestamp(dt.getYear(),dt.getMonth(),dt.getDate(),sc.getDebutTournagePreferable().getHours()
                        ,sc.getDebutTournagePreferable().getMinutes(),sc.getDebutTournagePreferable().getSeconds(),0));
            addDuration(sc);
            dt=sc.getFinTournage();
            duree=duree+duration;
            sugg.add(sc);
            tour=0;
        }
        Object []turn =new Object[3];
        turn[0]=dt;
        turn[1]=duree;
        turn[2]=tour;
        return turn;
    }

    private Timestamp changeDate(Timestamp last){
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(last.getTime());
        cal.add(Calendar.DAY_OF_MONTH,1);
        last=new Timestamp(cal.getTimeInMillis());
        return checkWeekEnd(last);
    }
    private void addDuration(Scene sc){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(sc.getDebutTournage().getTime());
        double reste=(double)sc.getDuration()-(int)sc.getDuration();
        cal.add(Calendar.MINUTE, sc.getEstimationTournage().getMinutes());
        cal.add(Calendar.HOUR,sc.getEstimationTournage().getHours());
        sc.setFinTournage(new Timestamp(cal.getTimeInMillis()));
    }
    private Timestamp checkWeekEnd(Timestamp tp){
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(tp.getTime());
        while(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
            cal.add(Calendar.DAY_OF_MONTH,1);
        tp=new Timestamp(cal.getTimeInMillis());
        tp.setHours(0);
        tp.setMinutes(0);
        tp.setSeconds(0);
        return tp;
    }
    private void checkIsPossible(Timestamp fin,ArrayList<Scene> scene)throws Exception{
        Scene sc=scene.get(scene.size()-1);
        if(sc.getFinTournage().after(fin))
            throw new Exception("Emploie du temps surchargé");
    }
    private boolean dispoPlateau(Timestamp tm,List<IndisponibilitePlateau> pl,Plateau plateau){
        for(IndisponibilitePlateau p:pl){
            if((p.getDateDebut().before(tm)||tm.equals(p.getDateDebut()))&&(p.getDateFin().after(tm)||tm.equals(p.getDateFin()))
                    &&plateau.getIdPlateau()==p.getIdPlateau())
                return false;
        }
        return true;
    }
    private boolean dispoActeur(Timestamp tm,List<IndisponibiliteActeur> ac,List<Personnage> acteur){
        for(Personnage pc:acteur ){
            for(IndisponibiliteActeur p:ac){
                if(p.getIdActeur()==pc.getIdActeur()&&p.getDateDebut().before(tm)&&p.getDateFin().after(tm))
                    return false;
            }
        }
        return true;
    }
}
