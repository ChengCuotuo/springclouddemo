package cn.nianzuochen.springcloue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@MapperScan("cn.nianzuochen.springcloue.mapper")
@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class ServiceHystrix8001Application {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHystrix8001Application.class, args);
    }
}
