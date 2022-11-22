package com.epam.rd.service;

import com.epam.rd.dto.AccountDTO;
import com.epam.rd.entity.User;
import com.epam.rd.enumirations.URoles;
import com.epam.rd.exceptions.UserExistException;
import com.epam.rd.payload.request.SignupRequest;
import com.epam.rd.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getFirstname());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(URoles.USER);
        user.setURoles(URoles.USER);
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            throw new UserExistException("The user " + user.getUsername() + " already exist. Please check credentials");
        }
    }



    public User getByPrincipal(Principal principal){

        return userRepository.findByUsername(principal.getName()).orElse(null);
    }
public List<User> getAll(){
        return userRepository.findAll();
}

    public User getCurrentUser(){
        String username = getPrincipal();
        if (username != null)
            return userRepository.findByUsername(username).orElse(null);
        return null;
    }

    private String getPrincipal() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);}

    public boolean check(String username) {
    return userRepository.findByUsername(username).isPresent();
    }

    public AccountDTO getUserDTO(Principal principal) {
        User user = this.getByPrincipal(principal);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setName(user.getName());
        accountDTO.setUsername(user.getUsername());
        accountDTO.setRole(user.getURoles());
        accountDTO.setPassword("");
        return accountDTO;
    }

    public AccountDTO updateUser(AccountDTO accountDTO, Principal principal) {
        User user = this.getByPrincipal(principal);
        user.setName(accountDTO.getName());
        user.setUsername(accountDTO.getUsername()+"");

        userRepository.save(user);
        return accountDTO;
    }

//    public boolean updatePassword(PasswordDTO passwordDTO, Principal principal) {
//        User user = this.getByPrincipal(principal);
//        if(passwordEncoder.matches(passwordDTO.getOld(), user.getPassword())) {
//            user.setPassword(passwordEncoder.encode(passwordDTO.getPassword()));
//            userRepository.save(user);
//            return true;
//        }
//        else return false;
//    }


}
