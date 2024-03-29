package com.epam.rd.controller;

import com.epam.rd.exceptions.UserNotFoundException;
import com.epam.rd.model.entity.User;
import com.epam.rd.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api/forgot")
public class ResetPasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    JavaMailSender mailSender;
    private String link;

    @Value("${link}")
    public void setPath(String path) {
        this.link = path;
    }

    /**
     * Generate token by email or Token to Send email
     */
    @PostMapping()
    public String processForgetPassword(@RequestParam(name = "email") String email) {
        String token = RandomString.make(50);
        System.out.println(email + "   \n" + token);
        try {
            userService.updateResetPassword(token, email);
            String resetPasswordLink = link + "/reset-password?token=" + token;
            System.out.println(resetPasswordLink);
            sendEmail(email, resetPasswordLink);
            System.out.println(resetPasswordLink);
        } catch (UserNotFoundException | MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    /**
     * Get User by token
     */

    @GetMapping()
    public ResponseEntity<User> findByToken(@RequestParam(value = "token") String token) {
        return ResponseEntity.ok(userService.get(token));
    }

    @GetMapping("/reset-password")
    public ResponseEntity<User> resetPasswordForm(@RequestParam(name = "token") String token) throws Exception {
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
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam(name = "token") String token,
                                              @RequestParam(name = "password") String password) throws Exception {
        User user = userService.get(token);
        if (user == null) {
            throw new Exception("Invalid Token");
        } else {
            userService.updatePassword(user, password);
        }
        return ResponseEntity.noContent().build();
    }

    public void sendEmail(String email, String resetPasswordLink) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("contact@help.com", "Emotional Help Support");
        messageHelper.setTo(email);
        String subject = "Emotional Help Password reset confirmation";
        String content = "<p>Hello</p>" +
                "There was recently a request to change the password on your account. <br>" +
                "If you requested this password change, please click the link below to set a new password:<br> " +
                "<a href=\"" + resetPasswordLink + "\">Change my password </a>";
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);
        mailSender.send(message);
    }
}
