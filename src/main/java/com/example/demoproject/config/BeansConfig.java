package com.example.demoproject.config;


import com.example.demoproject.beans.FirstBean;
import com.example.demoproject.beans.ThirdBean;
import com.example.demoproject.beans.ThirdBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {

    @Bean
    public FirstBean firstBeanBek(){
        return new FirstBean();
    }

    @Bean
    public FirstBean secondBean(){
        return new FirstBean("Almein", 15);
    }

    @Bean
    public ThirdBean thirdBean(){
        return new ThirdBeanImpl();
    }




}
