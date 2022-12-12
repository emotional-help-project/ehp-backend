package com.epam.rd.controller;

import com.epam.rd.exceptions.UserNotFoundException;
import com.epam.rd.model.entity.User;
import com.epam.rd.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/forgot")
public class ResetPasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    //Genreate token by email or Token to Send email
    @PostMapping()
    public String processForgetPassword(@RequestParam(name = "email") String email, HttpServletRequest request) {
        String token = RandomString.make(50);
        System.out.println(email + "   \n" + token);
        try {
            userService.updateResetPassword(token, email);
            String resetPasswordLink = "http://5.58.12.93:9090" + "/reset?token=" + token;
            sendEmail(email, resetPasswordLink);
            System.out.println(resetPasswordLink);
        } catch (UserNotFoundException | MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    //GetUserbytoken
    @GetMapping()
    public ResponseEntity<User> findByToken(@RequestParam(value = "token") String token) {
        return ResponseEntity.ok(userService.get(token));
    }

    //
    @GetMapping("/reset")
    public ResponseEntity<User> resetPasswordForm(@RequestParam(name = "token") String token,
                                                  HttpServletRequest request) throws Exception {
        User user = userService.get(token);
        if (user == null) {
            throw new Exception("Invalid Token");
        }

//        userService.updatePassword(user, password);
        return ResponseEntity.ok(user);
    }

    /**
     * update password for new
     */
    @PostMapping("/reset")
    public ResponseEntity<User> resetPassword(@RequestParam(name = "token") String token,
                                              @RequestParam(name = "password") String password,
                                              HttpServletRequest request) throws Exception {
        User user = userService.get(token);
        if (user == null) {
            throw new Exception("Invalid Token");
        } else {
            userService.updatePassword(user, password);
            throw new Exception("Successfully updated ! ");
        }
//        return ResponseEntity.ok().build();
    }

    public void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("contact@help.com", "Emotional Help Support");
        messageHelper.setTo(email);
        String subject = "Her is  the link to reset your password";
        String content = "<p>Hello</p>" +
                "You have a request to recover your password <br>" +
                "Click the link below to change your password:<br> " +
                "<a href=\"" + resetPasswordLink + "\">Change my password </a>";
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        mailSender.send(message);
    }
}
