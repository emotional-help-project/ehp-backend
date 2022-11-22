package com.epam.rd.dbinit;

import com.epam.rd.entity.User;
import com.epam.rd.enumirations.URoles;
import com.epam.rd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DBInit implements CommandLineRunner {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {





        if(userRepository.findAll().isEmpty()){
            User user = new User();
            user.setName("Otabek");

            user.setUsername("shootingstar99");
            user.getRoles().add(URoles.SUPER_ADMIN);
            user.setURoles(URoles.SUPER_ADMIN);
            user.setPassword(passwordEncoder.encode("otabek0311"));
            userRepository.save(user);
            user = new User();
            user.setName("admin");

            user.setUsername("admin123");
            user.getRoles().add(URoles.ADMIN);
            user.setURoles(URoles.ADMIN);

            user.setPassword(passwordEncoder.encode("admin321"));
            userRepository.save(user);
        }

    }

}