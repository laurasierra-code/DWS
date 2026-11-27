package com.example.logistica.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class ConfiguracionI18n implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(Locale.forLanguageTag("es"));
        // TODO EXAMEN (Tarea 3): Establece el idioma por defecto.
        return resolver;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        // TODO EXAMEN (Tarea 3): Establece el nombre del parámetro para cambiar el idioma.
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // TODO EXAMEN (Tarea 3): Registra el interceptor de cambio de idioma.
        registry.addInterceptor(localeChangeInterceptor());
    }
}
