package com.miniproject.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.miniproject.webmvc.models.JurusanModel;
import com.miniproject.webmvc.models.MahasiswaModel;
import com.miniproject.webmvc.services.JurusanService;
import com.miniproject.webmvc.services.MahasiswaService;

import java.util.List;

@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {

    private MahasiswaService mahasiswaService;
    private JurusanService jurusanService;

    @Autowired
    public MahasiswaController(MahasiswaService mahasiswaService, JurusanService jurusanService) {
        this.mahasiswaService = mahasiswaService;
        this.jurusanService = jurusanService;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "mahasiswa");
        model.addAttribute("title", "Mahasiswa");

        ModelAndView view = new ModelAndView("pages/mahasiswa/index.html");
        List<MahasiswaModel> mahasiswa = mahasiswaService.getAll();
        List<JurusanModel> jurusan = jurusanService.getAll();
        view.addObject("dataList", mahasiswa);
        view.addObject("jurusanList", jurusan);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(Model model) {
        model.addAttribute("activePage", "mahasiswa");
        model.addAttribute("title", "Mahasiswa");

        ModelAndView view = new ModelAndView("pages/mahasiswa/add.html");
        List<JurusanModel> result = jurusanService.getAll();
        view.addObject("jurusanList", result);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute MahasiswaModel request) {
        this.mahasiswaService.save(request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        MahasiswaModel mahasiswa = mahasiswaService.getById(id);
        if (mahasiswa == null) {
            return new ModelAndView("redirect:/mahasiswa");
        }

        List<JurusanModel> jurusan = jurusanService.getAll();

        model.addAttribute("activePage", "mahasiswa");
        model.addAttribute("title", "Mahasiswa");

        ModelAndView view = new ModelAndView("pages/mahasiswa/edit.html");
        view.addObject("data", mahasiswa);
        view.addObject("jurusanList", jurusan);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute MahasiswaModel request) {
        this.mahasiswaService.update(request.getId(), request);
        return new ModelAndView("redirect:/mahasiswa");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        MahasiswaModel mahasiswa = mahasiswaService.getById(id);
        if (mahasiswa == null) {
            return new ModelAndView("redirect:/mahasiswa");
        }

        model.addAttribute("activePage", "mahasiswa");
        model.addAttribute("title", "Mahasiswa");

        ModelAndView view = new ModelAndView("pages/mahasiswa/detail.html");
        view.addObject("data", mahasiswa);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute MahasiswaModel request) {
        MahasiswaModel mahasiswa = mahasiswaService.getById(request.getId());
        if (mahasiswa == null) {
            return new ModelAndView("redirect:/mahasiswa");
        }

        this.mahasiswaService.delete(request.getId());
        return new ModelAndView("redirect:/mahasiswa");
    }
}
