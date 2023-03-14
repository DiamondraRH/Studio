package application.controllers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import application.service.SceneService;

@Controller
public class SceneController {
    @Autowired
    private SceneService service;

    @GetMapping("/scenes/schedule")
    public ModelAndView schedule() {
        ModelAndView modelAndView = new ModelAndView("scene-planning-form");
        modelAndView.addObject("sceneList", service.findAllUnplannedScene());
        modelAndView.addObject("now", Timestamp.valueOf(LocalDateTime.now()));

        return modelAndView;
    }
}
