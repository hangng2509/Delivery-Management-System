/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.config;

import com.delivery.config.handlers.LoginSuccessHandler;
import com.delivery.config.handlers.LogoutHandler;

import com.delivery.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 *
 * @author PC
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.delivery"
})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LogoutSuccessHandler LogoutHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public LogoutSuccessHandler LogoutHandler() {
        return new LogoutHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Sửa lỗi UTF-8 Spring MVC
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);
        //Xử lý các thao tác đăng nhập, đăng xuất, set quyền
        http.formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password");
        http.formLogin().failureUrl("/login?error");
        //Viết 1 class implements AuthenticationSuccessHandler -> có 1 phương thức trừu tượng-> lấy user bỏ vào session
        //tại phương thức này, logout thì remove session. Lấy đầy đủ thông tin user, lấy từ session ra
        http.formLogin().successHandler(this.loginSuccessHandler);

        http.logout().logoutSuccessHandler(this.LogoutHandler);
        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");

        //All
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/dsdonhang").permitAll()
                //Customer
                .antMatchers("/admin/quanlydaugia")
                .access("hasAnyRole('" + User.Role.ROLE_CUSTOMER.name() + "')")
                //Admin
                .antMatchers("/admin/quanlytaixe/**")
                .access("hasAnyRole('" + User.Role.ROLE_ADMIN.name() + "')")
                .antMatchers("/admin/quanlykhuyenmai/**")
                .access("hasAnyRole('" + User.Role.ROLE_ADMIN.name() + "')")
                .antMatchers("/admin/orderStats")
                .access("hasAnyRole('" + User.Role.ROLE_ADMIN.name() + "')")
                .antMatchers("/admin/cusStats")
                .access("hasAnyRole('" + User.Role.ROLE_ADMIN.name() + "')")
                .antMatchers("/admin/shipStats")
                .access("hasAnyRole('" + User.Role.ROLE_ADMIN.name() + "')")
                .antMatchers("/admin/thongketatcataixe")
                .access("hasAnyRole('" + User.Role.ROLE_ADMIN.name() + "')")
                //Shipper
                .antMatchers("/cart")
                .access("hasAnyRole('" + User.Role.ROLE_SHIPPER.name() + "')")
                .antMatchers("/quanlygiaohang")
                .access("hasAnyRole('" + User.Role.ROLE_SHIPPER.name() + "')")
                .antMatchers("/lichsugiaohangshipper/**")
                .access("hasAnyRole('" + User.Role.ROLE_SHIPPER.name() + "')")
                //Admin,Cus
                .antMatchers("/admin/orders/**")
                .access("hasAnyRole('" + User.Role.ROLE_CUSTOMER.name() + "','" + User.Role.ROLE_ADMIN.name() + "')")
                .antMatchers("/admin")
                .access("hasAnyRole('" + User.Role.ROLE_CUSTOMER.name() + "','" + User.Role.ROLE_ADMIN.name() + "')")
                //Admin,Cus,Shipper
                .antMatchers("/dstaixe/**")
                .access("hasAnyRole('" + User.Role.ROLE_CUSTOMER.name() + "','" + User.Role.ROLE_ADMIN.name() + "','" + User.Role.ROLE_SHIPPER.name() + "')")
                .antMatchers("/order/**")
                .access("hasAnyRole('" + User.Role.ROLE_CUSTOMER.name() + "','" + User.Role.ROLE_ADMIN.name() + "','" + User.Role.ROLE_SHIPPER.name() + "')");
        http.csrf().disable();
    }

}
