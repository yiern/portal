package com.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.portal.Controller","com.portal.aws"})
public class PortalApplication {

        public static void main(String[] args) {
                SpringApplication.run(PortalApplication.class,args)  ;
        }
}
