package pro.sky.java.course2.homework28;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.homework28.model.Employee;
import pro.sky.java.course2.homework28.service.DepartmentService;
import pro.sky.java.course2.homework28.service.EmployeeServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceMockTest {

    /*private EmployeeServiceImpl employeeService;

    public DepartmentServiceMockTest(EmployeeServiceImpl empl) {
        this.employeeService = empl;
    }*/
    @Mock
    Map<String, Employee> employeeMap;

    private DepartmentService out;

    public DepartmentServiceMockTest(DepartmentService out) {
        this.out = out;
    }

    Employee emp = new Employee("Федор", "Фед", 34.0, 4);
    /*@BeforeEach
    public void setUp(){
        Map<String, Employee> employeeMap = new HashMap<String, Employee>();
    }*/
    @Test
    public void testMaxSalary(){
        when(out.findEmployeeMaxSalaryInDepartment(4))
                .thenReturn(emp);
        assertNotNull(out.findEmployeeMaxSalaryInDepartment(4));
    }

}
