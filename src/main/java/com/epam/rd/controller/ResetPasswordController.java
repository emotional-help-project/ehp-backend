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
//@RequiredArgsConstructor
//@AllArgsConstructor
public class ResetPasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping()
    public String processForgetPassword(HttpServletRequest request) {
        String email = request.getHeader("email");
        String token = RandomString.make(50);
        System.out.println(email + "   \n" + token);
        try {
            userService.updateResetPassword(token, email);
            String resetPasswordLink = request.getRequestURL().toString() + "/reset-password?token=" + token;
            sendEmail(email, resetPasswordLink);
            System.out.println(resetPasswordLink);
        } catch (UserNotFoundException | MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    @GetMapping()
    public ResponseEntity<User> findByToken(@RequestParam(value = "token") String token) {
        return ResponseEntity.ok(userService.get(token));
    }

    @PostMapping("reset")
    public ResponseEntity<User> resetpassword(HttpServletRequest request) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        User user = userService.get(token);
        if (user != null)
            userService.updatePassword(user, password);
        return ResponseEntity.ok(user);
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
