package com.epam.rd.security;

import com.epam.rd.model.entity.User;
import com.epam.rd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Custom userdetails service  removed and this class added
 */
@Service
public class UserProvider implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> ou = userRepository.findByEmail(email);
        if (ou.isPresent()) {
            User u = ou.get();
            UserSpecial um = new UserSpecial(u);
            um.setRoles(u.getRoles());
            return um;
        }
        throw new UsernameNotFoundException("Not found");
    }
}
