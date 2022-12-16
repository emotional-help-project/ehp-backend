package com.epam.rd.model.entity;

import com.epam.rd.model.enumerations.Gender;
import com.epam.rd.model.enumerations.URole;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
    @Column(name = "reset_password_token", length = 50)
    private String resetPasswordToken;


    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Min(value = 5, message = "User age should not be less than 5 years old.")
    @Max(value = 150, message = "User age should be less than 150 years old.")
    private int age;

    @Enumerated(EnumType.STRING)
    private URole role;

    @ElementCollection(targetClass = URole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<URole> roles = new HashSet<>();
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(Long id,
                String firstName,
                String lastName,
                String email,
                String password,
                Gender gender,
                int age,
                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.authorities = authorities;
    }

    /**
     * Security
     */

}
