package cn.nianzuochen.springcloue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@MapperScan("cn.nianzuochen.springcloue.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Service8001Application {
    public static void main(String[] args) {
        SpringApplication.run(Service8001Application.class, args);
    }
}
