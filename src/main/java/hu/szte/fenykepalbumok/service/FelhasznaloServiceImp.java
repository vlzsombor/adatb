package hu.szte.fenykepalbumok.service;

import hu.szte.fenykepalbumok.dto.UserRegistrationDto;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
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

    @Override
    public Felhasznalo save(UserRegistrationDto registration) {
        Felhasznalo user = new Felhasznalo();

        user.setKeresztNev(registration.getFirstName());

        user.setVezetekNev(registration.getLastName());
        user.setFelhasznaloNev(registration.getUserName());
        user.setTelefonszam(registration.getMobileNumber());
        user.setEmail(registration.getEmail());
        user.setJelszo(passwordEncoder.encode(registration.getPassword()));
        user.setJogosultsag("ROLE_USER");
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
}
