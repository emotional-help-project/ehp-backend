package com.epam.rd.security;


import com.epam.rd.model.enumerations.URole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    UserProvider userProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userProvider).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.cors().and().csrf().disable();

        http.authorizeRequests()
                // account
                .antMatchers("/api/account/signin").permitAll()
                .antMatchers("/api/account/signup").permitAll()
                .antMatchers(HttpMethod.GET, "/api/account").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/forgot").permitAll()
                .antMatchers(HttpMethod.POST, "/api/forgot/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/forgot").permitAll()
                .antMatchers(HttpMethod.GET, "/api/forgot/**").permitAll()

                //Users

                .antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/users/page").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/users/check/{email}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.GET, "/api/users/{id}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.PUT, "/api/users").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.DELETE, "/api/users/{id}").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/users/search").hasAnyAuthority(URole.ADMIN.toString())

                //User Profile
                .antMatchers(HttpMethod.PUT, "/api/user/profile/update").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/user/profile/update/password").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/user/profile/{id}/info").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/user/profile/{userId}/map/{testId}").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/user/profile/{id}/statistics").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/user/profile/tests").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/user/profile").hasAnyAuthority(URole.ADMIN.toString())
                //admin
                .antMatchers(HttpMethod.POST, "/api/admin/test").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/admin/advice").hasAnyAuthority(URole.ADMIN.toString())
                //answer
                .antMatchers(HttpMethod.GET, "/api/answers").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.GET, "/api/answers/{id}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.POST, "/api/answers").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/answers").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/answers/{id}").hasAnyAuthority(URole.ADMIN.toString())
                //course
                .antMatchers(HttpMethod.POST, "/api/courses").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/courses").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/courses/{id}").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/courses/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/courses/page").permitAll()
                .antMatchers(HttpMethod.GET, "/api/courses").permitAll()
                .antMatchers(HttpMethod.POST, "/api/courses/search").permitAll()
                .antMatchers(HttpMethod.POST, "/api/courses/{id}/book").hasAnyAuthority(URole.USER.toString())
                //appointment
                .antMatchers(HttpMethod.POST, "/api/appointments/appoint").hasAnyAuthority(URole.ADMIN.toString())
                //links
                .antMatchers(HttpMethod.GET, "/api/links").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.GET, "/api/links/{id}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.POST, "/api/links").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/links").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/links/{id}").hasAnyAuthority(URole.ADMIN.toString())
                //psychologic
                .antMatchers(HttpMethod.POST, "/api/psychologists/search").permitAll()
                .antMatchers(HttpMethod.POST, "/api/psychologists").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/psychologists").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/psychologists/{id}").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/psychologists/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/psychologists/available").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/psychologists").permitAll()
                .antMatchers(HttpMethod.GET, "/api/psychologists/page").permitAll()
                .antMatchers(HttpMethod.GET, "/api/psychologists/psy/{psychologistId}").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                //Question
                .antMatchers(HttpMethod.GET, "/api/questions").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.GET, "/api/questions/{id}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.POST, "/api/questions").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/questions").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/questions/{id}").hasAnyAuthority(URole.ADMIN.toString())
                //tests
                .antMatchers(HttpMethod.GET, "/api/tests").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.GET, "/api/tests/{id}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.POST, "/api/tests").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/tests").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/tests/{id}").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/tests/test/{id}/init").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/tests/test/{testId}/session/{sessionId}").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/tests//user/{id}").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/tests/test/session/{sessionId}").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/tests/test/session/{sessionId}/finalize").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                //testType
                .antMatchers(HttpMethod.GET, "/api/testTypes").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.GET, "/api/testTypes/{id}").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.POST, "/api/testTypes").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/testTypes").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/testTypes/{id}").hasAnyAuthority(URole.ADMIN.toString())

                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/app/**/*.{js,html}")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/h2-console/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/test/**")
                .antMatchers("/*.*"); // #3
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:4200");

        config.addAllowedOrigin("http://localhost:63033");
        config.addAllowedOrigin("http://192.168.100.42:4200");
        config.addAllowedOrigin("http://127.0.0.1:9090");
        config.addAllowedOrigin("http://127.0.0.1:9090/");
        config.addAllowedOrigin("http://localhost:4201");
        config.addAllowedOrigin("http://localhost:5501");
        config.addAllowedOrigin("http://localhost:5502");
        config.addAllowedOrigin("http://5.58.12.93");
        config.addAllowedOrigin("http://5.58.12.93:8095");
        config.addAllowedOrigin("http://5.58.12.93:9090");
        config.addAllowedOrigin("http://5.58.12.93:8080");
        config.addAllowedOrigin("http://localhost:4200");

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}






