/* Copyright © 2021 */
package com.ab.ploy.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@EnableWebSecurity(debug = true)
class WebSecurity : WebSecurityConfigurerAdapter() {

    @Bean fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http {
            authorizeRequests {
                authorize("/login", permitAll)
                authorize("/main.css", permitAll)
                authorize("/main.js", permitAll)
                authorize("/**", hasAnyRole("ADMIN", "USER"))
            }
            formLogin {
                loginPage = "/login"
                permitAll = true
                defaultSuccessUrl("/home", true)
            }
            logout { permitAll = true }
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
