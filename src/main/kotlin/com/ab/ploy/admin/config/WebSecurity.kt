/* Copyright © 2021 */
package com.ab.ploy.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@EnableWebSecurity(debug = true)
class WebSecurity : WebSecurityConfigurerAdapter() {

    @Bean fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http {
            authorizeRequests {
                authorize("/login", permitAll)
                authorize("/main.css", permitAll)
                authorize("/main.js", permitAll)
                authorize("/access-denied", permitAll)
                authorize("/**", hasRole("ADMIN"))
            }
            formLogin {
                loginPage = "/login"
                permitAll = true
                defaultSuccessUrl("/home", true)
            }
            logout { permitAll = true }
            exceptionHandling {
                // Return 401 for the REST endpoints instead of Login Page
                defaultAuthenticationEntryPointFor(
                    HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                    AntPathRequestMatcher("/admin/api/**"))
                accessDeniedPage = "/access-denied"
            }
        }
    }

    @Bean
    fun inMemoryUserDetailsManager(): InMemoryUserDetailsManager? {
        val user: UserDetails =
            User.withUsername("user")
                .password(passwordEncoder().encode("hej123"))
                .roles("USER")
                .build()
        val admin: UserDetails =
            User.withUsername("admin")
                .password(passwordEncoder().encode("hej123"))
                .roles("USER", "ADMIN")
                .build()
        val inMemoryUserDetailsManager = InMemoryUserDetailsManager()
        inMemoryUserDetailsManager.createUser(user)
        inMemoryUserDetailsManager.createUser(admin)
        return inMemoryUserDetailsManager
    }
}
