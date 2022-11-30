package com.miniproject.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miniproject.webmvc.models.MataKuliahModel;
import com.miniproject.webmvc.services.MataKuliahService;

import java.util.List;

@Controller
@RequestMapping("/matakuliah")
public class MataKuliahController {

    private MataKuliahService service;

    @Autowired
    public MataKuliahController(MataKuliahService service) {
        this.service = service;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "matakuliah");
        model.addAttribute("title", "Matakuliah");

        ModelAndView view = new ModelAndView("pages/matakuliah/index.html");
        List<MataKuliahModel> mataKuliah = service.getAll();
        view.addObject("dataList", mataKuliah);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("activePage", "matakuliah");
        model.addAttribute("title", "Matakuliah");

        return new ModelAndView("pages/matakuliah/add.html");
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MataKuliahModel request) {
        this.service.save(request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        MataKuliahModel mataKuliah = service.getById(id);
        if (mataKuliah == null) {
            return new ModelAndView("redirect:/matakuliah");
        }

        model.addAttribute("activePage", "matakuliah");
        model.addAttribute("title", "Matakuliah");

        ModelAndView view = new ModelAndView("pages/matakuliah/detail.html");
        view.addObject("data", mataKuliah);
        return view;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        MataKuliahModel mataKuliah = service.getById(id);
        if (mataKuliah == null) {
            return new ModelAndView("redirect:/matakuliah");
        }

        model.addAttribute("activePage", "matakuliah");
        model.addAttribute("title", "Matakuliah");

        ModelAndView view = new ModelAndView("pages/matakuliah/edit.html");
        view.addObject("data", mataKuliah);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MataKuliahModel request) {
        this.service.update(request.getId(), request);
        return new ModelAndView("redirect:/matakuliah");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MataKuliahModel request) {
        MataKuliahModel mataKuliah = service.getById(request.getId());
        if (mataKuliah == null) {
            return new ModelAndView("redirect:/matakuliah");
        }

        this.service.delete(request.getId());
        return new ModelAndView("redirect:/matakuliah");
    }
}
