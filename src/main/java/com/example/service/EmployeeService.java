package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員全件を取得する
     * 
     * @return List<Employee>型の配列
     */
    public List<Employee> showList() {
        return employeeRepository.findAll();
    }

    public Employee showDetail(Integer id) {
        return employeeRepository.load(id);
    }

    public void update(Employee employee) {
        employeeRepository.update(employee);
    }
}
