package com.example.service;

import com.example.common.DTO.AddEmployeeDTO;
import com.example.common.DTO.EmployeeLoginDTO;
import com.example.common.entity.Employee;

public interface EmployeeService {
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    void addEmployee(AddEmployeeDTO addEmployeeDTO);
}
