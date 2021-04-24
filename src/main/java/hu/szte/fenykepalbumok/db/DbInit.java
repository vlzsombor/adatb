package hu.szte.fenykepalbumok.db;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private FelhasznaloRepository userRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;


    private PasswordEncoder passwordEncoder;

    public DbInit(FelhasznaloRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;
    @Override
    public void run(String... args) {
        //defaultDatabase();
    }

    private void defaultDatabase(){
        // Delete all
        // this.userRepository.deleteAll();

        // Crete users
        //User dan = new User("dan",passwordEncoder.encode("dan123"),"USER","");
        //User admin = new User("admin",passwordEncoder.encode("admin123"),"ADMIN","ACCESS_TEST1,ACCESS_TEST2");
        //User manager = new User("manager",passwordEncoder.encode("manager123"),"MANAGER","ACCESS_TEST1");

        // public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
        //
        Felhasznalo dan = new Felhasznalo("dan","dan","dan@a.hu", passwordEncoder.encode("dan123"), "ROLE_USER");
        //User manager = new User("manager","manager","manager@a.hu",passwordEncoder.encode("manager123"),Arrays.asList(new Role("ROLE_MANAGER")));
        Felhasznalo admin = new Felhasznalo("admin","admin","admin@a.hu",passwordEncoder.encode("admin123"),"ROLE_ADMIN");
        //public RealEstate(String name, String userName, String address, String description, long price)
        //public RealEstate(String name, User user, String address, String description, Long price) {


        Kategoria kategoria = new Kategoria(KategoriaEnum.TERMESZET_FOTOK.toString(), "ezek itten termeszet fotok");
        Kategoria kategoria2 = new Kategoria(KategoriaEnum.TERMESZET_FOTOK2.toString(), "ezek itten termeszet fotok2");

        kategoriaRepository.save(kategoria);
        kategoriaRepository.save(kategoria2);

        List<Felhasznalo> users = Arrays.asList(dan,admin);

        // Save to db
        this.userRepository.saveAll(users);
    }


}
