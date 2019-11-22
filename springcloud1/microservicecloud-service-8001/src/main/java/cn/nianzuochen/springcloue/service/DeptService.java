package cn.nianzuochen.springcloue.service;

import cn.nianzuochen.microservice.dao.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptService {
    Dept findById(Long id);

    List<Dept> findAllDept();

    boolean addDept(@Param("dept") Dept dept);
}
