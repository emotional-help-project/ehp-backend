package com.epam.rd.controller;

import com.epam.rd.dto.AccountDTO;
import com.epam.rd.entity.User;
import com.epam.rd.service.ManagementService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.ResponseEntity.ok;


/**
 *
 * Faqatgina Admin va Super admin uchun xizmat qiluvchi controller.
 *
 *
* */

@CrossOrigin(maxAge = 36000)
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {


private final ManagementService managementService;



//    @PostMapping("/addmanager")
//    public ResponseEntity<?> createAdmin(@RequestBody AccountDTO accountDTO){
//        User user = managementService.createManager(accountDTO);
//        if(user==null) return (ResponseEntity<?>) ResponseEntity.badRequest();
//        else return ok(user);
//    }


//    @GetMapping("/user")
//    public ResponseEntity<List<AccountDTO>> getAllUser(){
//        return ResponseEntity.ok(.getAll());
//    }



}
