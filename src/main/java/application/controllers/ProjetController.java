package application.controllers;

import application.models.Projet;
import application.models.Scene;
import application.services.ProjetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
@RequestMapping("/projets")
public class ProjetController {
    private final ProjetService projetService;

    public ProjetController(ProjetService projetService) {
        this.projetService = projetService;
    }

    @GetMapping
    public ModelAndView findAll(){
        ModelAndView modelAndView=new ModelAndView("index");
        try {
            modelAndView.addObject("projets",projetService.findAll());
        }catch (Exception e){
            e.printStackTrace();
        }
        return modelAndView;
    }

    @GetMapping("/planning/{idprojet}")
    public ModelAndView toPlanning(@PathVariable("idprojet") Integer idprojet){
        ModelAndView modelAndView=new ModelAndView("planning");
        try {
            Projet projet=projetService.findById(idprojet);
            ArrayList<Scene> scenes=projetService.getScenesForProject(projet);
            modelAndView.addObject("projet",projet);
            modelAndView.addObject("scenes",scenes);
            System.out.println("SCENES:=>");
            scenes.forEach(scene->System.out.println(scene.toString()));
            System.out.println(scenes);
            modelAndView.addObject("events",Scene.toJSONCallendar(scenes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
}
