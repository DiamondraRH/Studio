package application.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import application.models.Scene;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SceneController {
    private final SceneService sceneService;

    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
    }

    @GetMapping("/scenes/schedule")
    public ModelAndView schedule() {
        ModelAndView modelAndView = new ModelAndView("scene-planning-form");
        modelAndView.addObject("sceneList", service.findAllUnplannedScene());
        modelAndView.addObject("now", Timestamp.valueOf(LocalDateTime.now()));

        return modelAndView;
    }

    @PostMapping("/validerPlanning")
    public ModelAndView validerPlanning(HttpServletRequest request) throws Exception {
        String[] scenes = request.getParameterValues("scenes");

        for (int i = 0; i < scenes.length; i++) {
            String[] values = scenes[i].split(";");
            Scene scene = sceneService.findById(values[0]);
            scene.setDebutTournage(values[1]);
            scene.setFinTournage(values[2]);
            sceneService.update(scene);
        }

        //redirect To Main Calendar
        return new ModelAndView();
    }
}
