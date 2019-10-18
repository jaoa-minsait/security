package com.prueba.security.demo.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration//es una clase de configuracion al fin y al cabo
@EnableWebSecurity//esta aotacion es imprescindible para tener activada la seguridad
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        http
            .authorizeRequests()
                .antMatchers("/","/public").permitAll()
                .anyRequest().permitAll()
                .and()
            .formLogin()//Aqui define el tipo de login al que pueden acceder todos los que entren por ahí
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()// en esta parte indica el tipo de logout que van a llevar a cabo
                .permitAll()
        ;
    }

    @Autowired
    public void configurationGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser("falken").password("josua")
            .roles("USER")
            ;
    }

}
