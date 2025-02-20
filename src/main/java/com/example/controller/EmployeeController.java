package com.example.controller;

import com.example.common.DTO.AddEmployeeDTO;
import com.example.common.DTO.EmployeeLoginDTO;
import com.example.common.VO.EmployeeLoginVO;
import com.example.common.entity.Employee;
import com.example.common.properties.JWTProperties;
import com.example.common.result.Result;
import com.example.common.utils.JWTUtil;
import com.example.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;
    private final JWTProperties jwtProperties;

    public EmployeeController(EmployeeService employeeService, JWTProperties jwtProperties) {
        this.employeeService = employeeService;
        this.jwtProperties = jwtProperties;
    }

    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("Employee login: {}", employeeLoginDTO.getUsername());

        Employee employee = employeeService.login(employeeLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put("empId", employee.getId());
        String token = JWTUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTTL(),
                claims
        );
        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUserName())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    @PostMapping("/addEmployee")
    public Result addEmployee(@RequestBody AddEmployeeDTO addEmployeeDTO){
        log.info("Add new employee userName: {}", addEmployeeDTO.getUserName());
        employeeService.addEmployee(addEmployeeDTO);
        return Result.success();
    }

}
