/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.delivery.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.delivery.formatter.KhuyenMaiFormatter;
import com.delivery.validator.UserPassValidator;
import com.delivery.validator.WebAppValidator;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author PC
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {
    "com.delivery.controller",
    "com.delivery.repository",
    "com.delivery.service",
    "com.delivery.validator",
    "com.delivery.Utils"
})
@EnableTransactionManagement
public class WebConfig implements WebMvcConfigurer {
    
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/resource/css/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resource/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resource/js/");
        registry.addResourceHandler("/vendor/**").addResourceLocations("/resource/vendor/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("/resource/fonts/");
        registry.addResourceHandler("/assets/**").addResourceLocations("/resource/assets/");
        registry.addResourceHandler("/vendor_login/**").addResourceLocations("/resource/vendor_login/");
        registry.addResourceHandler("/assetsUI/**").addResourceLocations("/resource/assetsUI/");
    }
    
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new KhuyenMaiFormatter());
    }
    
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource
                = new ResourceBundleMessageSource();
        resource.setBasenames("messageuser", "messageorder", "messbooking");
        return resource;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
    
    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "nthou123",
                        "api_key", "142931975693911",
                        "api_secret", "eUyGZvkW3DXrsnxDITxwFSIJAxY",
                        "secure", true));
        return cloudinary;
    }
    
    @Override
    public Validator getValidator() {
        return validator(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean
                = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
    
    @Bean
    public WebAppValidator userValidator() {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UserPassValidator());
        
        WebAppValidator validator = new WebAppValidator();
        validator.setSpringValidators(springValidators);
        return validator;
    }
    
}
