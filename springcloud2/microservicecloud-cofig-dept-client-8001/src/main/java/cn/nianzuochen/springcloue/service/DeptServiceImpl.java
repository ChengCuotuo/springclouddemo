package cn.nianzuochen.springcloue.service;

import cn.nianzuochen.microservice.dao.Dept;
import cn.nianzuochen.springcloue.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptMapper deptMapper;

    @Override
    public Dept findById(Long id) {
        return deptMapper.findById(id);
    }

    @Override
    public List<Dept> findAllDept() {
        return deptMapper.findAllDept();
    }

    @Override
    public boolean addDept(Dept dept) {
        return deptMapper.addDept(dept);
    }
}
