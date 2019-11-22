package cn.nianzuochen.springcloue.mapper;

import cn.nianzuochen.microservice.dao.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptMapper {

    Dept findById(Long id);

    List<Dept> findAllDept();

    boolean addDept(@Param("dept") Dept dept);
}
