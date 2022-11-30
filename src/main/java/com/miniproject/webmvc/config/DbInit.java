package com.miniproject.webmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.FakultasEntity;
import com.miniproject.webmvc.entities.GedungEntity;
import com.miniproject.webmvc.entities.JurusanEntity;
import com.miniproject.webmvc.entities.RuangEntity;
import com.miniproject.webmvc.repos.FakultasRepo;
import com.miniproject.webmvc.repos.GedungRepo;

@Service
public class DbInit implements CommandLineRunner {

    private FakultasRepo fakultasRepo;
    private GedungRepo gedungRepo;

    @Autowired
    public DbInit(FakultasRepo fakultasRepo, GedungRepo gedungRepo) {
        this.fakultasRepo = fakultasRepo;
        this.gedungRepo = gedungRepo;
    }

    private void initFakultas() {
        if (fakultasRepo.count() == 0) {
            FakultasEntity fakultasSatu = new FakultasEntity("FMIPA", "Fakultas Matematika dan IPA", "Jambi");
            fakultasSatu.addJurusan(new JurusanEntity("MTK", "Matematika"));
            fakultasSatu.addJurusan(new JurusanEntity("FIK", "Fisika"));
            fakultasSatu.addJurusan(new JurusanEntity("BIO", "Biologi"));
            fakultasSatu.addJurusan(new JurusanEntity("SNS", "Sains"));

            this.fakultasRepo.save(fakultasSatu);

            FakultasEntity fakultasDua = new FakultasEntity("FKIK", "Fakultas Kedokteran dan Ilmu Kesehatan", "Jambi");
            fakultasDua.addJurusan(new JurusanEntity("IKM", "Ilmu Kesehatan Masyarakat"));
            fakultasDua.addJurusan(new JurusanEntity("KG", "Kedokteran Gigi"));
            fakultasDua.addJurusan(new JurusanEntity("KP", "Keperawatan"));
            fakultasDua.addJurusan(new JurusanEntity("KC", "Kedokteran Hewan"));

            this.fakultasRepo.save(fakultasDua);
        }
    }

    private void initGedung() {
        if (gedungRepo.count() == 0) {
            GedungEntity gedungSatu = new GedungEntity("A49", "Gedung Praktikum", 5);
            gedungSatu.addRuang(new RuangEntity("G23", "Ruang Praktikum Fisika", 4));
            gedungSatu.addRuang(new RuangEntity("G27", "Ruang Praktikum Kimia", 4));
            gedungSatu.addRuang(new RuangEntity("G21", "Ruang Praktikum Biologi", 2));

            this.gedungRepo.save(gedungSatu);

            GedungEntity gedungDua = new GedungEntity("B36", "Gedung Seminar", 3);
            gedungDua.addRuang(new RuangEntity("G02", "Ruang Seminar Umum", 1));
            gedungDua.addRuang(new RuangEntity("G04", "Ruang Seminar Tertutup", 3));

            this.gedungRepo.save(gedungDua);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        initFakultas();
        initGedung();
    }
}
