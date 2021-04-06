package hu.szte.fenykepalbumok.service;

import hu.szte.fenykepalbumok.dto.UserRegistrationDto;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface FelhasznaloService extends UserDetailsService {
    Felhasznalo findByEmail(String email);

    Felhasznalo save(UserRegistrationDto registration);
}
