package com.miniproject.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miniproject.webmvc.models.FakultasModel;
import com.miniproject.webmvc.models.JurusanModel;
import com.miniproject.webmvc.services.FakultasService;
import com.miniproject.webmvc.services.JurusanService;

import java.util.List;

@Controller
@RequestMapping("/jurusan")
public class JurusanController {

    private JurusanService jurusanService;
    private FakultasService fakultasService;

    @Autowired
    public JurusanController(JurusanService jurusanService, FakultasService fakultasService) {
        this.jurusanService = jurusanService;
        this.fakultasService = fakultasService;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "jurusan");
        model.addAttribute("title", "Jurusan");

        ModelAndView view = new ModelAndView("pages/jurusan/index.html");
        List<JurusanModel> result = jurusanService.getAll();
        view.addObject("dataList", result);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("activePage", "jurusan");
        model.addAttribute("title", "Jurusan");

        ModelAndView view = new ModelAndView("pages/jurusan/add.html");
        List<FakultasModel> result = fakultasService.getAll();
        view.addObject("fakultasList", result);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute JurusanModel request) {
        this.jurusanService.save(request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        JurusanModel jurusan = jurusanService.getById(id);
        if (jurusan == null) {
            return new ModelAndView("redirect:/jurusan");
        }

        List<FakultasModel> fakultas = fakultasService.getAll();

        model.addAttribute("activePage", "jurusan");
        model.addAttribute("title", "Jurusan");

        ModelAndView view = new ModelAndView("pages/jurusan/edit.html");
        view.addObject("data", jurusan);
        view.addObject("fakultasList", fakultas);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute JurusanModel request) {
        this.jurusanService.update(request.getId(), request);
        return new ModelAndView("redirect:/jurusan");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        JurusanModel jurusan = jurusanService.getById(id);
        if (jurusan == null) {
            return new ModelAndView("redirect:/jurusan");
        }

        model.addAttribute("activePage", "jurusan");
        model.addAttribute("title", "Jurusan");

        ModelAndView view = new ModelAndView("pages/jurusan/detail.html");
        view.addObject("data", jurusan);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute JurusanModel request) {
        JurusanModel jurusan = jurusanService.getById(request.getId());
        if (jurusan == null) {
            return new ModelAndView("redirect:/jurusan");
        }

        this.jurusanService.delete(request.getId());
        return new ModelAndView("redirect:/jurusan");
    }
}
