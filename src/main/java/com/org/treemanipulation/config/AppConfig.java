package com.hingehealth.demo.config;

import com.hingehealth.demo.mapper.TreeMapper;
import com.hingehealth.demo.util.IdGenerator;
import com.hingehealth.demo.util.SimpleIdGenerator;
import com.hingehealth.demo.util.SimpleTreeParser;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public IdGenerator idGenerator() {
        return new SimpleIdGenerator();
    }

    @Bean
    public SimpleTreeParser treeParser() {
        return new SimpleTreeParser();
    }

    @Bean
    public TreeMapper treeMapper() {
        return new TreeMapper();
    }
}