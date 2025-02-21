package com.example.mapper;

import com.example.common.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee WHERE user_name = #{userName}")
    Employee getByUserName(String userName);

    void addEmployee(Employee newEmployee);
}
