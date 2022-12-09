package com.epam.rd.service;

import com.epam.rd.model.entity.User;
import com.epam.rd.model.enumerations.Gender;
import com.epam.rd.model.enumerations.URole;
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


        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin Emotional");
        user.setAge(25);
        user.setGender(Gender.MALE);
        user.setEmail("djavistdev@mail.ru");
        user.getRoles().add(URole.ADMIN);
        user.setRole(URole.ADMIN);
        user.setPassword(passwordEncoder.encode("Star19!("));
        userRepository.save(user);

    }

}