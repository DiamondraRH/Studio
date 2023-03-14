package application.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import application.services.SceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import application.models.Scene;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SceneController {
    @Autowired
    private final SceneService service;

    public SceneController(SceneService sceneService) {
        this.service = sceneService;
    }

    @GetMapping("/scenes/schedule")
    public ModelAndView schedule() {
        ModelAndView modelAndView = new ModelAndView("scene-planning-form");
        try {
            modelAndView.addObject("sceneList", service.findAllUnplannedScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelAndView.addObject("now", Timestamp.valueOf(LocalDateTime.now()));

        return modelAndView;
    }

    @PostMapping("/plannifier")
    public ModelAndView plannifier(HttpServletRequest request) throws Exception {
        String[] idScenes = request.getParameterValues("idScene");
        ArrayList<Scene> scenes = new ArrayList<Scene>();
        for (int i = 0; i < idScenes.length; i++) {
            scenes.add(service.findById(idScenes[i]));
        }
        String debut = request.getParameter("dateDebut");
        String fin = request.getParameter("dateFin");

        debut = debut + " 08:00:00";
        fin = fin + " 23:59:59";

        ArrayList<Scene> planning = service.suggestion(scenes , Timestamp.valueOf(debut) , Timestamp.valueOf(fin));
        ModelAndView modelAndView = new ModelAndView("valider-planning");
        modelAndView.addObject("scenes" , planning);
        return modelAndView;
    }

    @PostMapping("/validerPlanning")
    public ModelAndView validerPlanning(HttpServletRequest request) throws Exception {
        String[] scenes = request.getParameterValues("scenes");

        for (int i = 0; i < scenes.length; i++) {
            String[] values = scenes[i].split(";");
            Scene scene = service.findById(values[0]);
            scene.setDebutTournage(values[1]);
            scene.setFinTournage(values[2]);
            service.update(scene);
        }

        //redirect To Main Calendar
        return new ModelAndView();
    }
}
