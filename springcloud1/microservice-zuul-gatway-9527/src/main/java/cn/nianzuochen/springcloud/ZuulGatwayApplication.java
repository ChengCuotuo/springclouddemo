package cn.nianzuochen.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ZuulGatwayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulGatwayApplication.class,args);
    }
}
