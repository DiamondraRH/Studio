package application.services;

import application.data.HibernateDAO;
import application.models.Projet;
import application.models.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProjetService {
    @Autowired
    private HibernateDAO dao;

    public ArrayList<Projet> findAll()throws Exception{
        return dao.findAll(new Projet());
    }

    public Projet findById(Integer idprojet)throws Exception{
        return dao.findOneById(new Projet(),idprojet);
    }

    public ArrayList<Scene> getScenesForProject(Projet projet)throws Exception{
        Scene scene=projet.provideSceneWithProject();
        ArrayList<Scene> scenes=dao.find(scene,false);
        ArrayList<Scene> s=new ArrayList<>();
        for (Scene sce:scenes) {
            if (sce.getDebutTournage()!=null&&sce.getFinTournage()!=null) s.add(sce);
        }
        return s;
    }
}
