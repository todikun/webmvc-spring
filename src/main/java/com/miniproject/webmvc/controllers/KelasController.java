package com.miniproject.webmvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.miniproject.webmvc.models.DosenModel;
import com.miniproject.webmvc.models.KelasModel;
import com.miniproject.webmvc.models.MataKuliahModel;
import com.miniproject.webmvc.models.RuangModel;
import com.miniproject.webmvc.services.DosenService;
import com.miniproject.webmvc.services.KelasService;
import com.miniproject.webmvc.services.MataKuliahService;
import com.miniproject.webmvc.services.RuangService;
import com.miniproject.webmvc.utils.DateUtil;

import java.util.List;

@Controller
@RequestMapping("/kelas")
public class KelasController {

    private KelasService kelasService;
    private RuangService ruangService;
    private MataKuliahService mataKuliahService;
    private DosenService dosenService;

    @Autowired
    public KelasController(
            KelasService kelasService, RuangService ruangService,
            MataKuliahService mataKuliahService, DosenService dosenService) {

        this.kelasService = kelasService;
        this.ruangService = ruangService;
        this.mataKuliahService = mataKuliahService;
        this.dosenService = dosenService;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        model.addAttribute("activePage", "kelas");
        model.addAttribute("title", "Kelas");

        ModelAndView view = new ModelAndView("pages/kelas/index.html");
        List<KelasModel> kelas = kelasService.getAll();
        view.addObject("dataList", kelas);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView add() {
        ModelAndView view = new ModelAndView("pages/kelas/add.html");
        List<RuangModel> ruang = ruangService.getAll();
        List<MataKuliahModel> mataKuliah = mataKuliahService.getAll();
        List<DosenModel> dosen = dosenService.getAll();

        view.addObject("ruangList", ruang);
        view.addObject("mataKuliahList", mataKuliah);
        view.addObject("dosenList", dosen);
        view.addObject("hariList", DateUtil.hari());
        return view;
    }

    @PostMapping("/save")
    public ModelAndView save(@ModelAttribute KelasModel request) {
        this.kelasService.save(request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id, @ModelAttribute KelasModel request) {
        KelasModel kelas = this.kelasService.getById(id);
        if (kelas == null) {
            return new ModelAndView("redirect:/kelas");
        }

        ModelAndView view = new ModelAndView("pages/kelas/edit.html");
        List<RuangModel> ruang = ruangService.getAll();
        List<MataKuliahModel> mataKuliah = mataKuliahService.getAll();
        List<DosenModel> dosen = dosenService.getAll();

        view.addObject("data", kelas);
        view.addObject("ruangList", ruang);
        view.addObject("mataKuliahList", mataKuliah);
        view.addObject("dosenList", dosen);
        view.addObject("hariList", DateUtil.hari());
        return view;
    }

    @PostMapping("/update")
    public ModelAndView update(@ModelAttribute KelasModel request) {
        this.kelasService.update(request.getId(), request);
        return new ModelAndView("redirect:/kelas");
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id) {
        KelasModel kelas = kelasService.getById(id);
        if (kelas == null) {
            return new ModelAndView("redirect:/kelas");
        }

        ModelAndView view = new ModelAndView("pages/kelas/detail.html");
        view.addObject("data", kelas);
        return view;
    }

    @PostMapping("/delete")
    public ModelAndView delete(@ModelAttribute KelasModel request) {
        KelasModel kelas = kelasService.getById(request.getId());
        if (kelas == null) {
            return new ModelAndView("redirect:/kelas");
        }

        this.kelasService.delete(request.getId());
        return new ModelAndView("redirect:/kelas");
    }
}
