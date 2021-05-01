package hu.szte.fenykepalbumok.service;

import hu.szte.fenykepalbumok.dto.UserRegistrationDto;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Megye;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.model.Varos;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.OrszagRepository;
import hu.szte.fenykepalbumok.repository.VarosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FelhasznaloServiceImp implements FelhasznaloService{

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Felhasznalo findByEmail(String email) {
        return felhasznaloRepository.findByEmail(email);
    }

    @Autowired
    private OrszagRepository orszagRepository;

    @Autowired
    private VarosRepository varosRepository;

    @Override
    public Felhasznalo save(UserRegistrationDto registration) {
        Felhasznalo user = new Felhasznalo();

        user.setKeresztNev(registration.getFirstName());

        user.setVezetekNev(registration.getLastName());
        user.setFelhasznaloNev(registration.getUserName());
        user.setTelefonszam(registration.getMobileNumber());
        user.setEmail(registration.getEmail());
        user.setJelszo(passwordEncoder.encode(registration.getPassword()));

        //todo admin
        user.setJogosultsag("ROLE_USER");
        user.setVaros(registration.getVaros());





        var varos = lakcimbeallitas(registration.getVaros().getMegnevezes(),registration.getVaros().getMegye().getMegnevezes(),registration.getVaros().getMegye().getOrszag().getMegnevezes());

        user.setVaros(varos);

        return felhasznaloRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Felhasznalo user = felhasznaloRepository.findByEmail(s);
        if (user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getJelszo(),
                mapRolesToAuthorities(user.getJogosultsag()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String roles){
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(roles));

        return simpleGrantedAuthorities;
        /*
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

         */
    }

    private Varos lakcimbeallitas(String varosNev, String megyeNev, String orszagNev) {

        Orszag orszag = orszagRepository.findOrszagByMegnevezes(orszagNev);

        if (orszag == null) {
            orszag = new Orszag();
            orszag.setMegnevezes(orszagNev);
            Megye megye = new Megye();
            megye.setMegnevezes(megyeNev);
            megye.setOrszag(orszag);
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye);
            varosRepository.save(varos);
            return varos;
        }

        if (!orszag.getMegyek().stream().anyMatch(n -> n.getMegnevezes().equals(megyeNev))) {
            Megye megye = new Megye();
            megye.setMegnevezes(megyeNev);
            megye.setOrszag(orszag);
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye);

            varosRepository.save(varos);
            return varos;

        }
        var megye = orszag.getMegyek().stream().filter(n -> n.getMegnevezes().equals(megyeNev)).findFirst();

        if (!megye.get().getVarosok().stream().anyMatch(n -> n.getMegnevezes().equals(varosNev))) {
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye.get());

            varosRepository.save(varos);

            return varos;
        }
        var varos = megye.get().getVarosok().stream().filter(n -> n.getMegnevezes().equals(varosNev)).findFirst().get();

        return varos;
    }
}
