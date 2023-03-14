package application.service;

import application.data.HibernateDAO;
import application.models.*;
import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class SceneService {
    @Autowired
    private HibernateDAO dao;

    private ArrayList<Scene> findByProject(Projet projet) throws Exception{
        ArrayList<Scene> ls=dao.findAll(new Scene());
        ArrayList<Scene> turn=new ArrayList<>();
        for(Scene sc:ls){
            if(sc.getProjet().getIdProjet()==projet.getIdProjet())
                turn.add(sc);
        }
        return turn;
    }
    public ArrayList<Scene> suggestion(Projet projet) throws  Exception{
        ArrayList<Scene> liste=this.findByProject(projet);
        ArrayList<Scene> sugg=new ArrayList<>();
        Timestamp debut=projet.getDebutProduction();
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
                        Object[] ob=changeDateTournage(sc, debut, ls, sugg, duree,tour);
                        debut=(Timestamp) ob[0];
                        duree=(float)ob[1];
                        tour=(int)ob[2];
                    }
                }
            }
        }
        return sugg;
    }
    private Object[] changeDateTournage(Scene sc,Timestamp dt,ArrayList<Scene> scene,ArrayList<Scene> sugg,float duree,int tour){
        Time last=new Time(dt.getHours(),dt.getMinutes(),dt.getSeconds());
        if(last.before(sc.getFinTournagePreferable())&&((duree+sc.getDuration())<=8.0)){
            if(last.after(sc.getDebutTournagePreferable())||last.compareTo(sc.getDebutTournagePreferable())==0||
                    sc.getDebutTournagePreferable()==null)
                sc.setDebutTournage(dt);
            else
                sc.setDebutTournage(new Timestamp(dt.getYear(),dt.getMonth(),dt.getDate(),sc.getDebutTournagePreferable().getHours()
                ,sc.getDebutTournagePreferable().getMinutes(),sc.getDebutTournagePreferable().getSeconds(),0));
            addDuration(sc);
            dt=sc.getFinTournage();
            duree=duree+sc.getEstimationTournage().getHours();
            duree=duree+(sc.getEstimationTournage().getMinutes()/60);
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
        last.setHours(0);
        last.setMinutes(0);
        last.setSeconds(0);
        return last;
    }
    private void addDuration(Scene sc){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(sc.getDebutTournage().getTime());
        double reste=(double)sc.getDuration()-(int)sc.getDuration();
        cal.add(Calendar.MINUTE, sc.getEstimationTournage().getMinutes());
        cal.add(Calendar.HOUR,sc.getEstimationTournage().getHours());
        sc.setFinTournage(new Timestamp(cal.getTimeInMillis()));
    }
}
