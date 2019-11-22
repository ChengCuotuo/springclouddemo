package cn.nianzuochen.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Config_Git_Eureka7001Application {
    public static void main(String[] args) {
        SpringApplication.run(Config_Git_Eureka7001Application.class, args);
    }
}