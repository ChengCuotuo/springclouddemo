package cn.nianzuochen.springcloud.controller;

import cn.nianzuochen.microservice.dao.Dept;
import cn.nianzuochen.microservice.service.DeptClientService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class DeptController_Consumer {
    @Autowired
    private DeptClientService service;

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @GetMapping("/dept/find/{id}")
//    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> list( ) {
        return service.list();
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id) {
        return new Dept().setDeptno(id).
                setDname("该ID: " + id + " 没有对应的信息， null -- @HystrixCommand").
                setDb_source("no this database in MySQL");
    }
}
