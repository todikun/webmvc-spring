package com.miniproject.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miniproject.webmvc.models.DosenModel;
import com.miniproject.webmvc.services.DosenService;

import java.util.List;

@Controller
@RequestMapping("/dosen")
public class DosenController {

    private DosenService service;

    @Autowired
    public DosenController(DosenService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "dosen");
        model.addAttribute("title", "Dosen");

        ModelAndView view = new ModelAndView("pages/dosen/index.html");
        List<DosenModel> dosen = service.getAll();
        view.addObject("dataList", dosen);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("activePage", "dosen");
        model.addAttribute("title", "Dosen");

        return new ModelAndView("pages/dosen/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute DosenModel request) {
        this.service.save(request);
        return new ModelAndView("redirect:/dosen");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        DosenModel dosen = service.getById(id);
        if (dosen == null) {
            return new ModelAndView("redirect:/dosen");
        }

        model.addAttribute("activePage", "dosen");
        model.addAttribute("title", "Dosen");

        ModelAndView view = new ModelAndView("pages/dosen/detail.html");
        view.addObject("data", dosen);
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        DosenModel dosen = service.getById(id);
        if (dosen == null) {
            return new ModelAndView("redirect:/dosen");
        }

        model.addAttribute("activePage", "dosen");
        model.addAttribute("title", "Dosen");

        ModelAndView view = new ModelAndView("pages/dosen/edit.html");
        view.addObject("data", dosen);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute DosenModel request) {
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/dosen");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute DosenModel request) {
        DosenModel dosen = service.getById(request.getId());
        if (dosen == null) {
            return new ModelAndView("redirect:/dosen");
        }

        this.service.delete(request.getId());
        return new ModelAndView("redirect:/dosen");
    }
}
