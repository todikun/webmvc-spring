package com.miniproject.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproject.webmvc.models.GedungModel;
import com.miniproject.webmvc.models.RuangModel;
import com.miniproject.webmvc.services.GedungService;
import com.miniproject.webmvc.services.RuangService;

import java.util.List;

@Controller
@RequestMapping("/ruang")
public class RuangController {

    private RuangService ruangService;
    private GedungService gedungService;

    @Autowired
    public RuangController(RuangService ruangService, GedungService gedungService) {
        this.ruangService = ruangService;
        this.gedungService = gedungService;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "ruang");
        model.addAttribute("title", "Ruang");

        ModelAndView view = new ModelAndView("pages/ruang/index.html");
        List<RuangModel> ruang = ruangService.getAll();
        view.addObject("dataList", ruang);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id, Model model) {
        RuangModel ruang = ruangService.getById(id);
        if (ruang == null) {
            return new ModelAndView("redirect:/ruang");
        }

        model.addAttribute("activePage", "ruang");
        model.addAttribute("title", "Ruang");

        ModelAndView view = new ModelAndView("pages/ruang/detail.html");
        view.addObject("data", ruang);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add(@ModelAttribute RuangModel request, Model model) {
        model.addAttribute("activePage", "ruang");
        model.addAttribute("title", "Ruang");

        ModelAndView view = new ModelAndView("pages/ruang/add.html");
        List<GedungModel> gedung = gedungService.getAll();
        view.addObject("gedungList", gedung);
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute RuangModel request, RedirectAttributes redirectAttrs) {
        this.ruangService.save(request);
        redirectAttrs.addFlashAttribute("success", "Data has been successfully saved!");
        return new ModelAndView("redirect:/ruang");
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, Model model) {
        RuangModel ruang = ruangService.getById(id);
        if (ruang == null) {
            return new ModelAndView("redirect:/ruang");
        }

        model.addAttribute("activePage", "ruang");
        model.addAttribute("title", "Ruang");

        List<GedungModel> gedung = gedungService.getAll();

        ModelAndView view = new ModelAndView("pages/ruang/edit.html");
        view.addObject("data", ruang);
        view.addObject("gedungList", gedung);
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute RuangModel request, RedirectAttributes redirectAttrs) {
        this.ruangService.update(request.getId(), request);
        redirectAttrs.addFlashAttribute("success", "Data has been successfully updated!");
        return new ModelAndView("redirect:/ruang");
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute RuangModel request, RedirectAttributes redirectAttrs) {
        RuangModel ruang = ruangService.getById(request.getId());
        if (ruang == null) {
            return new ModelAndView("redirect:/ruang");
        }

        redirectAttrs.addFlashAttribute("success", "Data has been successfully deleted!");

        this.ruangService.delete(request.getId());
        return new ModelAndView("redirect:/ruang");
    }

}
