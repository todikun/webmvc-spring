package com.miniproject.webmvc.controllers;

import com.miniproject.webmvc.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class HomeController {

    private MahasiswaRepo mahasiswaRepo;
    private JurusanRepo jurusanRepo;
    private FakultasRepo fakultasRepo;
    private MataKuliahRepo mataKuliahRepo;
    private DosenRepo dosenRepo;
    private GedungRepo gedungRepo;
    private RuangRepo ruangRepo;

    @Autowired
    public HomeController(
            MahasiswaRepo mahasiswaRepo,
            JurusanRepo jurusanRepo,
            FakultasRepo fakultasRepo,
            MataKuliahRepo mataKuliahRepo,
            DosenRepo dosenRepo,
            GedungRepo gedungRepo,
            RuangRepo ruangRepo) {
        this.mahasiswaRepo = mahasiswaRepo;
        this.jurusanRepo = jurusanRepo;
        this.fakultasRepo = fakultasRepo;
        this.mataKuliahRepo = mataKuliahRepo;
        this.dosenRepo = dosenRepo;
        this.gedungRepo = gedungRepo;
        this.ruangRepo = ruangRepo;
    }

    @GetMapping
    public ModelAndView index(Model model) {
        ModelAndView view = new ModelAndView("pages/index.html");

        // count data
        int mahasiswaCount = (int) mahasiswaRepo.count();
        int jurusanCount = (int) jurusanRepo.count();
        int fakultasCount = (int) fakultasRepo.count();
        int mataKuliahCount = (int) mataKuliahRepo.count();
        int dosenCount = (int) dosenRepo.count();
        int gedungCount = (int) gedungRepo.count();
        int ruangCount = (int) ruangRepo.count();

        model.addAttribute("activePage", "dashboard");
        model.addAttribute("title", "Dashboard");

        view.addObject("mahasiswa", mahasiswaCount);
        view.addObject("jurusan", jurusanCount);
        view.addObject("fakultas", fakultasCount);
        view.addObject("matakuliah", mataKuliahCount);
        view.addObject("dosen", dosenCount);
        view.addObject("gedung", gedungCount);
        view.addObject("ruang", ruangCount);
        return view;
    }
}
