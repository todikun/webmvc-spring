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
@RequestMapping("/kelas")
public class KelasController {

    private RuangService ruangService;
    private GedungService gedungService;

    @Autowired
    public KelasController(RuangService ruangService, GedungService
    gedungService) {
    this.ruangService = ruangService;
    this.gedungService = gedungService;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "kelas");
        model.addAttribute("title", "Kelas");

        ModelAndView view = new ModelAndView("pages/kelas/index.html");
        // List<RuangModel> ruang = ruangService.getAll();
        // view.addObject("dataList", ruang);
        return view;
    }
}
