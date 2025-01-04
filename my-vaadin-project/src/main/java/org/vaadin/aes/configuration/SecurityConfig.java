/*
package org.vaadin.aes.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll() // Bu URL'ler herkes tarafından erişilebilir
                .anyRequest().authenticated() // Diğer tüm URL'ler kimlik doğrulama gerektirir
                .and()
                .formLogin()
                .loginPage("/login") // Login sayfası URL'si
                .permitAll() // Login sayfasına herkes erişebilir
                .and()
                .logout()
                .permitAll(); // Logout işlemi herkes tarafından yapılabilir

        // Eğer kimlik doğrulaması yapılmadan korunan bir kaynağa erişilmeye çalışılırsa login sayfasına yönlendirme yapar
        http.exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));

        return http.build(); // HttpSecurity nesnesini döndür
    }
}
*/
