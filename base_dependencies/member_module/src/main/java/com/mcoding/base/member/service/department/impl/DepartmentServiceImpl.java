package com.mcoding.base.member.service.department.impl;

import com.mcoding.base.core.PageView;
import com.mcoding.base.member.bean.department.Department;
import com.mcoding.base.member.bean.department.DepartmentExample;
import com.mcoding.base.member.persistence.department.DepartmentMapper;
import com.mcoding.base.member.service.department.DepartmentService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    protected DepartmentMapper departmentMapper;

    @CacheEvict(value={"department"}, allEntries=true)
    @Override
    public void addObj(Department t) {
        this.departmentMapper.insertSelective(t);
    }

    @CacheEvict(value={"department"}, allEntries=true)
    @Override
    public void deleteObjById(int id) {
        this.departmentMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(value={"department"}, allEntries=true)
    @Override
    public void modifyObj(Department t) {
        if (t.getId() == null || t.getId() ==0) {
            throw new NullPointerException("id 为空，无法更新");
        }
        this.departmentMapper.updateByPrimaryKeySelective(t);
    }

    @Cacheable(value="department", key="'DepartmentService_' + #root.methodName + '_' +#id")
    @Override
    public Department queryObjById(int id) {
        return this.departmentMapper.selectByPrimaryKey(id);
    }

    @Cacheable(value="department", key="'DepartmentService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public List<Department> queryAllObjByExample(DepartmentExample example) {
        return this.departmentMapper.selectByExample(example);
    }

    @Cacheable(value="department", key="'DepartmentService_' + #root.methodName + '_'+ #example.toJson()")
    @Override
    public PageView<Department> queryObjByPage(DepartmentExample example) {
        PageView<Department> pageView = example.getPageView();
        if (pageView == null) {
            pageView = new PageView<>(1, 10);
            example.setPageView(pageView);
        }
        pageView.setQueryResult(this.departmentMapper.selectByExampleByPage(example));
        return pageView;
    }
}