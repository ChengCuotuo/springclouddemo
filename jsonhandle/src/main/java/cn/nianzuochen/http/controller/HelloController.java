package cn.nianzuochen.http.controller;

import cn.nianzuochen.http.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public User hello() {
        User user = new User();
        user.setId(8);
        user.setAge(21);
        user.setName("柳岩");
        user.setUsername("liuyan");
        return user;
    }
}
