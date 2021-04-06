package hu.szte.fenykepalbumok.db;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    @Autowired
    private FelhasznaloRepository userRepository;


    private PasswordEncoder passwordEncoder;

    public DbInit(FelhasznaloRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
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

        List<Felhasznalo> users = Arrays.asList(dan,admin);

        // Save to db
        this.userRepository.saveAll(users);
    }
}
