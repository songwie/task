package com.songwie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.event.ContextClosedEvent;
 
@SpringBootApplication
@EnableSpringConfigured
@EnableAspectJAutoProxy
//@ComponentScan(basePackages = "com.songwie")
public class RootApplication {
    public static void main(String[] args) {
    	ConfigurableApplicationContext cac = SpringApplication.run(RootApplication.class, args);
        System.err.println("## task-service startup success...");
        
        cac.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {

            }
        });
    }
}
