package com.miniproject.webmvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.miniproject.webmvc.models.GedungModel;
import com.miniproject.webmvc.services.GedungService;

@Controller
@RequestMapping("/gedung")
public class GedungController {
    private GedungService service;

    @Autowired
    public GedungController(GedungService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "gedung");
        model.addAttribute("title", "Gedung");

        ModelAndView view = new ModelAndView("pages/gedung/index.html");
        List<GedungModel> result = service.getAll();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        GedungModel gedung = service.getById(id);
        if (gedung == null) {
            return new ModelAndView("redirect:/gedung");
        }

        model.addAttribute("activePage", "gedung");
        model.addAttribute("title", "Gedung");

        ModelAndView view = new ModelAndView("pages/gedung/detail.html");
        view.addObject("data", gedung);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("activePage", "gedung");
        model.addAttribute("title", "Gedung");

        return new ModelAndView("pages/gedung/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute GedungModel request) {
        this.service.save(request);
        return new ModelAndView("redirect:/gedung");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        GedungModel gedung = service.getById(id);
        if (gedung == null) {
            return new ModelAndView("redirect:/gedung");
        }

        model.addAttribute("activePage", "gedung");
        model.addAttribute("title", "Gedung");

        ModelAndView view = new ModelAndView("pages/gedung/edit.html");
        view.addObject("data", gedung);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute GedungModel request) {
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/gedung");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute GedungModel request) {
        GedungModel gedung = service.getById(request.getId());
        if (gedung == null) {
            return new ModelAndView("redirect:/gedung");
        }

        this.service.delete(request.getId());
        return new ModelAndView("redirect:/gedung");
    }
}
