package com.karasov.spring_boot.config;

import com.karasov.spring_boot.profiles.DevProfile;
import com.karasov.spring_boot.profiles.ProductionProfile;
import com.karasov.spring_boot.profiles.SystemProfile;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "true")
    SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(prefix = "netology.profile", name = "dev", havingValue = "false")
    SystemProfile productionProfile() {
        return new ProductionProfile();
    }

}
