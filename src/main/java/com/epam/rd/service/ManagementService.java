package com.epam.rd.service;

import com.epam.rd.dto.AccountDTO;
import com.epam.rd.entity.User;
import com.epam.rd.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagementService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;





    private User createUser(AccountDTO accountDTO){
        User user = new User() ;
        user.setName(accountDTO.getName());
        user.setUsername(accountDTO.getUsername()+"");
        user.getRoles().add(accountDTO.getRole());
        user.setURoles(accountDTO.getRole());
        user.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
        return userRepository.save(user);
    }

}
