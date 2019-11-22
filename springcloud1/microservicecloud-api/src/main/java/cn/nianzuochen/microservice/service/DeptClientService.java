package cn.nianzuochen.microservice.service;

import cn.nianzuochen.microservice.dao.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "MICROSERVICECLOUD", fallbackFactory = DeptClientServiceFallback.class)
public interface DeptClientService {

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept) ;

    @GetMapping("/dept/find/{id}")
    public Dept get(@PathVariable("id") Long id) ;

    @GetMapping("/dept/list")
    public List<Dept> list() ;
}
