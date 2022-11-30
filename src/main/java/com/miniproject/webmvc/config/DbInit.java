package com.miniproject.webmvc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.miniproject.webmvc.entities.FakultasEntity;
import com.miniproject.webmvc.entities.GedungEntity;
import com.miniproject.webmvc.entities.JurusanEntity;
import com.miniproject.webmvc.entities.RoleEntity;
import com.miniproject.webmvc.entities.RuangEntity;
import com.miniproject.webmvc.entities.UserEntity;
import com.miniproject.webmvc.repos.FakultasRepo;
import com.miniproject.webmvc.repos.GedungRepo;
import com.miniproject.webmvc.services.RoleService;
import com.miniproject.webmvc.services.UserService;

@Service
public class DbInit implements CommandLineRunner {

    private FakultasRepo fakultasRepo;
    private GedungRepo gedungRepo;
    private PasswordEncoder encoder;
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public DbInit(FakultasRepo fakultasRepo, GedungRepo gedungRepo, PasswordEncoder encoder, UserService userService,
            RoleService roleService) {
        this.fakultasRepo = fakultasRepo;
        this.gedungRepo = gedungRepo;
        this.encoder = encoder;
        this.userService = userService;
        this.roleService = roleService;
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

    private void initUserAndRole() {
        if (roleService.getCount() == 0) {
            this.roleService.save(Arrays.asList(
                    new RoleEntity("ROLE_ADMIN"),
                    new RoleEntity("ROLE_USER"),
                    new RoleEntity("ROLE_DOSEN"),
                    new RoleEntity("ROLE_MAHASISWA"),
                    new RoleEntity("ROLE_KEUANGAN")));
        }

        if (userService.getCount() == 0) {
            RoleEntity adminRole = roleService.getByName("ROLE_ADMIN");
            UserEntity admin = new UserEntity("admin", encoder.encode("admin123"), "admin@gmail.com",
                    Arrays.asList(adminRole));
            this.userService.save(admin);

            RoleEntity mhsRole = roleService.getByName("ROLE_MAHASISWA");
            UserEntity mhs = new UserEntity("mahasiswa", encoder.encode("mahasiswa123"), "mahasiswa@gmail.com",
                    Arrays.asList(mhsRole));
            this.userService.save(mhs);

            RoleEntity dosenRole = roleService.getByName("ROLE_DOSEN");
            UserEntity dosen = new UserEntity("dosen", encoder.encode("dosen123"), "dosen@gmail.com",
                    Arrays.asList(dosenRole));
            this.userService.save(dosen);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        initUserAndRole();
        initFakultas();
        initGedung();
    }
}
