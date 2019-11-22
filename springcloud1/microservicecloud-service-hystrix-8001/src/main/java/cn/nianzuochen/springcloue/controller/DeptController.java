package cn.nianzuochen.springcloue.controller;

import cn.nianzuochen.microservice.dao.Dept;
import cn.nianzuochen.springcloue.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    DeptService deptService;

    @Autowired
    DiscoveryClient client;

    @GetMapping("/find/{id}")
    // 一旦调用服务方法失败并抛出错误信息后，会自动调用 @HystrixCommand 标注的 fallbackMethod 指定的方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept =  deptService.findById(id);
        if (null == dept) {
            throw new RuntimeException("该ID: " + id + " 没有对应的信息");
        }

        return dept;
    }


    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).
                setDname("该ID: " + id + " 没有对应的信息， null -- @HystrixCommand").
                setDb_source("no this database in MySQL");
    }
}
