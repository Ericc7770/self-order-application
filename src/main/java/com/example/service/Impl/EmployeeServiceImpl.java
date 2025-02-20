package com.example.service.Impl;

import com.example.common.DTO.AddEmployeeDTO;
import com.example.common.DTO.EmployeeLoginDTO;
import com.example.common.constant.MessageConstant;
import com.example.common.entity.Employee;
import com.example.common.exception.AccountExistedException;
import com.example.common.exception.AccountLockedException;
import com.example.common.exception.AccountNotFoundException;
import com.example.common.exception.PasswordInvalidException;
import com.example.mapper.EmployeeMapper;
import com.example.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String userName = employeeLoginDTO.getUsername();
        String pwd = employeeLoginDTO.getPassword();
        Employee employee = employeeMapper.getByUserName(userName);
        if (employee == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
        if (!pwd.equals(employee.getPassword())){
            throw new PasswordInvalidException(MessageConstant.INVALID_PASSWORD);
        }
        if (employee.getStatus() == Employee.DISABLED){
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        return employee;
    }

    @Override
    public void addEmployee(AddEmployeeDTO addEmployeeDTO) {
        String userName = addEmployeeDTO.getUserName();
        Employee employee = employeeMapper.getByUserName(userName);
        if (employee != null){
            throw new AccountExistedException(MessageConstant.ACCOUNT_EXIST);
        }
        LocalDateTime now = LocalDateTime.now();
        Employee newEmployee = Employee.builder()
                .name(addEmployeeDTO.getName())
                .userName(userName)
                .createTime(now)
                .updateTime(now)
                .password(DigestUtils.md5DigestAsHex(addEmployeeDTO.getPassword().getBytes()))
                .build();
        employeeMapper.addEmployee(newEmployee);
    }
}
