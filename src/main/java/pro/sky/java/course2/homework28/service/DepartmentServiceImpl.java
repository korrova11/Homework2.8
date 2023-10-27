package pro.sky.java.course2.homework28.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.homework28.exception.EmployeeNotFoundException;
import pro.sky.java.course2.homework28.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;
    private Map<String, Employee> employeeMap;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService, Map<String, Employee> employeeMap) {
        this.employeeService = employeeService;
        this.employeeMap = employeeMap;
    }


    @Override

    public Optional<Employee> findEmployeeMaxSalaryInDepartment(int depart) {
        Optional<Employee> employee1 =
                employeeService.createList(employeeService.employeeMap).stream()
                        .filter(e -> e.getDepartment() == depart)
                        .max(Comparator.comparingDouble(employee -> employee.getSalary()));
        return Optional.of(employee1.orElseThrow(() -> new RuntimeException("В отделе нет сотрудников")));


    }

    @Override

    public Optional<Employee> findEmployeeMinSalaryInDepartment(int depart) {
        Optional<Employee> employee1 =
                employeeService.createList(employeeService.employeeMap).stream()
                        .filter(e -> e.getDepartment() == depart)
                        .min(Comparator.comparingDouble(employee -> employee.getSalary()));

        return Optional.of(employee1.orElseThrow(() -> new RuntimeException("В отделе нет сотрудников")));


    }

    @Override
    public List<Employee> findAllDepartment(int depart) {
        final List<Employee> departmentEmployees =
                employeeService.createList(employeeService.employeeMap).stream()
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
                employeeService.createList(employeeService.employeeMap).stream()
                        .collect(Collectors.groupingBy(e -> e.getDepartment()));

        return employees;

    }

}
