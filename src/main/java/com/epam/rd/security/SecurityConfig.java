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

        http
                .authorizeRequests()

                // account

                .antMatchers("/api/account/signin").permitAll()
                .antMatchers("/api/account/signup").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/forgot").permitAll()
                .antMatchers(HttpMethod.POST, "/api/forgot/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/forgot").permitAll()
                .antMatchers(HttpMethod.GET, "/api/forgot/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/account").hasAnyAuthority(URole.ADMIN.toString())

                //Users

                .antMatchers("/api/users/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers("/api/user/profile/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())

                .antMatchers("/api/admin/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/**").hasAnyAuthority(URole.ADMIN.toString(), URole.USER.toString())
                .antMatchers(HttpMethod.POST, "/api/testTypes/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/answers/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/appoint/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAnyAuthority(URole.ADMIN.toString())
                //courses
                .antMatchers(HttpMethod.DELETE, "/api/courses/**").hasAnyAuthority(URole.ADMIN.toString())
                .antMatchers(HttpMethod.POST, "/api/courses/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/courses/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                //psychologic
                .antMatchers(HttpMethod.POST, "/api/psychologists/search").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                //test
                .antMatchers(HttpMethod.POST, "/api/tests/test/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/api/tests/test/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/api/tests/test/**").hasAnyAuthority(URole.USER.toString(), URole.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/api/tests/test/**").hasAnyAuthority(URole.ADMIN.toString())

                .anyRequest().authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler).and().
                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

// 		Add a filter to validate the tokens with every request
        http.addFilterBefore(jwtFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web)  {
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






