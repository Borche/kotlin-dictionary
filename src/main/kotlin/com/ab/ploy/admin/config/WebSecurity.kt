/* Copyright © 2021 */
package com.ab.ploy.admin.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
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
class WebSecurity {

    @Order(1)
    @Configuration
    class AdminSecurity : WebSecurityConfigurerAdapter() {
        override fun configure(http: HttpSecurity) {
            http {
                securityMatcher("/admin/**")
                authorizeRequests {
                    authorize("/admin/login", permitAll)
                    authorize("/admin/main.css", permitAll)
                    authorize("/admin/main.js", permitAll)
                    authorize("/admin/**", hasRole("ADMIN"))
                }
                formLogin {
                    loginProcessingUrl = "/admin/login"
                    loginPage = "/admin/login"
                    permitAll = true
                    defaultSuccessUrl("/admin/", true)
                }
                logout { permitAll = true }
                exceptionHandling {
                    // Return 401 for the REST endpoints instead of Login Page
                    defaultAuthenticationEntryPointFor(
                        HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        AntPathRequestMatcher("/admin/api/**"))
                }
            }
        }
    }

    @Order(2)
    @Configuration
    class PublicSecurity : WebSecurityConfigurerAdapter() {
        override fun configure(http: HttpSecurity) {
            http {
                authorizeRequests {
                    authorize("/login", permitAll)
                    authorize("/", hasRole("USER"))
                }
                formLogin {
                    loginProcessingUrl = "/login"
                    loginPage = "/login"
                    permitAll = true
                    defaultSuccessUrl("/", true)
                }
                logout { permitAll = true }
                exceptionHandling {
                    // Return 401 for the REST endpoints instead of Login Page
                    defaultAuthenticationEntryPointFor(
                        HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),
                        AntPathRequestMatcher("/public/api/**"))
                }
            }
        }
    }

    @Bean fun passwordEncoder() = BCryptPasswordEncoder()

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