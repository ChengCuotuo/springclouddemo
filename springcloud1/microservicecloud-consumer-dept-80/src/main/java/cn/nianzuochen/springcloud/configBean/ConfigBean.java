package cn.nianzuochen.springcloud.configBean;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {
    @Bean
    @LoadBalanced  // Spring Cloud Ribbom 是基于 Netflix Ribbon 实现的一套客户端负载均衡的工具
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    // 定义了查询方法就会覆盖原有的算法
//    @Bean
//    public IRule myRole() {
////        return new RandomRule();  // 达到的目的，用我们重新选择的随机算法替代默认的轮询算法
//        return new RetryRule();
//    }
}

/**
 *
 * @Bean
 * public UserService getUserService() {
 *     return new UserServiceImpl();
 * }
 *
 * applicationContext.xml == ConfigBean(@Configuration)
 * <bean id="userService" class = "......"></>
 *
 */
