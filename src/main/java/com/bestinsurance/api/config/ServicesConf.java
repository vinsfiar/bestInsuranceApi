package com.bestinsurance.api.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the REST services
 */
@Configuration
@ComponentScan({"com.bestinsurance.api.rest"})
public class ServicesConf {
}
