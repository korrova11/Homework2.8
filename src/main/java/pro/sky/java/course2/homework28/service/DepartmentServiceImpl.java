package pro.sky.java.course2.homework28.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.homework28.exception.EmployeeNotFoundException;
import pro.sky.java.course2.homework28.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;


    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;

    }


    @Override

    public Employee findEmployeeMaxSalaryInDepartment(int depart) {


        return employeeService.employeeMap.values().stream()
                .filter(e -> e.getDepartment() == depart)
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);


    }

    @Override

    public Employee findEmployeeMinSalaryInDepartment(int depart) {
        return employeeService.employeeMap.values().stream()
                .filter(e -> e.getDepartment() == depart)
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(EmployeeNotFoundException::new);


    }

    @Override
    public List<Employee> findAllDepartment(int depart) {
        final List<Employee> departmentEmployees =
                employeeService.employeeMap.values().stream()
                        .filter(e -> e.getDepartment() == depart)
                        .collect(Collectors.toList());
        if (departmentEmployees.isEmpty()) {
            throw new EmployeeNotFoundException();
        } else
            return departmentEmployees;

    }

    @Override
    public Map<Integer, List<Employee>> findAllDepartmentAll() {
        final Map<Integer, List<Employee>> employees =
                employeeService.employeeMap.values().stream()
                        .collect(Collectors.groupingBy(e -> e.getDepartment()));

        return employees;

    }

}
