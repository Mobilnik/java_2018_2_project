package ru.milandr.courses.miptshop.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //Эта настройка будет конфликтовать с настройкой security.basic.enabled=false
    //Нужно удалить эту настройку
   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll(); //запросы к странице логина разрешаем все.
    }
*/
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        //Для входа в приложение использовать пару user : ef3f3f68-3436-49f9-bbce-ff343d1ad814
        //Пароль мелькает в логах в строке вида Using default security password: ef3f3f68-3436-49f9-bbce-ff343d1ad814
        http.authorizeRequests()
                .anyRequest().authenticated().and() //любой запрос должен быть аутентифицирован
                .formLogin().and() //включаем редирект на /login
                .httpBasic(); //включаем httpBasic
    } */
}