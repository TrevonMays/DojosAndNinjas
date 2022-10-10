package com.example.DojosAndNinjas.controllers;

import com.example.DojosAndNinjas.models.Dojo;
import com.example.DojosAndNinjas.models.Ninja;
import com.example.DojosAndNinjas.services.DojoServices;
import com.example.DojosAndNinjas.services.NinjaServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    private final DojoServices dojoServices;
    public MainController(DojoServices dojoServices, NinjaServices ninjaServices) {
        this.dojoServices = dojoServices;
        this.ninjaServices = ninjaServices;
    }

    private final NinjaServices ninjaServices;

    //! CREATE AND SHOW ALL
    @GetMapping("/dojos")
    public String newDojo(@ModelAttribute(value = "dojo") Dojo dojo, Model model){
        model.addAttribute("dojos", dojoServices.getAll());
        return "/dojos/new.jsp";
    }

    @PostMapping("/dojos")
    public String createDojo(@Valid @ModelAttribute(value = "dojo") Dojo dojo, BindingResult result){
        if(result.hasErrors()){
            return "/dojos/new.jsp";
        } else {
            dojoServices.create(dojo);
            return "redirect:/dojos";
        }
    }

    //! READ SHOW ONE DOJO

    @GetMapping("/dojos/{id}")
    public String showDojo(Model model, @PathVariable("id") Long id){
        Dojo dojo = dojoServices.getOne(id);
        model.addAttribute("dojo", dojo);
        return "/dojos/show.jsp";
    }

    //! CREATE NINJA

    @GetMapping("/ninjas")
    public String newNinja(@ModelAttribute("ninja") Ninja ninja, Model model){
        model.addAttribute("dojos", dojoServices.getAll());
        return "/ninjas/new.jsp";
    }

    @PostMapping("/ninjas")
    public String createNinja(@ModelAttribute("ninja") Ninja ninja, BindingResult result){
        String id = String.valueOf(ninja.getDojo().getId());
        if(result.hasErrors()){
            return "/ninjas/new.jsp";
        } else {
            ninjaServices.create(ninja);
            return String.format("redirect:/dojos/%s", id);
        }

    }
}