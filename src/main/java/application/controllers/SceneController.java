package application.controllers;

import application.models.Scene;
import application.services.SceneService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SceneController {
    private final SceneService sceneService;

    public SceneController(SceneService sceneService) {
        this.sceneService = sceneService;
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
