package com.example.universityRegestrationSchedule.Scurity;


import com.example.universityRegestrationSchedule.Scurity.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/style.css").permitAll()
                .antMatchers("/courses").permitAll()
                .antMatchers("/student/{id}",
                        "/delete_schedule/{id}").hasAnyAuthority("STUDENT")
                .antMatchers("/instructor/{id}",
                        "/delete_course/{id}").hasAnyAuthority("INSTRUCTOR")
                .antMatchers("/addcourse",
                        "/update/{id}",
                        "/delete/{id}",
                        "/signup",
                        "/signup/student",
                        "/signup/instructor",
                        "/deleteStudent/{id}",
                        "/deleteInstructor/{id}",
                        "/instructors",
                        "/students").hasAnyAuthority("ADMIN")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/" , true)
                .failureUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout" , "GET"))
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/login");
    }
}