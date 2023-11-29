package pro.sky.java.course2.homework28;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.homework28.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.homework28.exception.EmployeeIllegalNameOrLastNameException;
import pro.sky.java.course2.homework28.exception.EmployeeNotFoundException;
import pro.sky.java.course2.homework28.model.Employee;
import pro.sky.java.course2.homework28.service.DepartmentServiceImpl;
import pro.sky.java.course2.homework28.service.EmployeeService;
import pro.sky.java.course2.homework28.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl out;
    Employee emp = new Employee("Федор", "Фед", 34.0, 4);


    @Test
    public void testShouldCallEmployeeServiceWhenAdd() {
        when(employeeService.add("Федор", "Фед", 34.0, 4))
                .thenReturn(emp);
        assertNotNull(employeeService.add("Федор", "Фед", 34.0, 4));
        assertEquals(4, emp.getDepartment());
        verify(employeeService, atLeastOnce())
                .add("Федор", "Фед", 34.0, 4);

    }

    @Test
    public void testShouldCallEmployeeServiceWhenFind() {
        when(employeeService.find("Федор", "Фед"))
                .thenReturn(emp);
        assertNotNull(employeeService.find("Федор", "Фед"));
        assertEquals(employeeService.find("Федор", "Фед"), emp);
        assertEquals(4, emp.getDepartment());
        verify(employeeService, times(2)).find("Федор", "Фед");

    }

    @Test
    public void testShouldCallEmployeeServiceWhenRemove() {
        when(employeeService.remove("Федор", "Фед"))
                .thenReturn(emp);
        assertNotNull(employeeService.remove("Федор", "Фед"));
        assertEquals(employeeService.remove("Федор", "Фед"), emp);
        assertEquals(4, emp.getDepartment());
        verify(employeeService, times(2)).remove("Федор", "Фед");

    }

    @Test
    public void testFindAll() {
        Map<String, Employee> expected = new HashMap<>();
        when(employeeService.findAll())
                .thenReturn(expected);
        assertTrue(employeeService.findAll().isEmpty());
        verify(employeeService,atLeast(1)).findAll();
        verify(employeeService,timeout(100)).findAll();


    }

    @Test
    public void testCreateList() {
        List<Employee> expected = new ArrayList<>();
        when(employeeService.createList())
                .thenReturn(expected);
        assertTrue(employeeService.createList().isEmpty());
        verify(employeeService,atMost(2)).createList();

    }
    @Test
    public void testAddWhenException(){
        when(employeeService.add("Федор1", "Фед", 34.0, 4))
                .thenThrow(EmployeeIllegalNameOrLastNameException.class);
        when(employeeService.add("Федор", "Фед", 34.0, 4))
                .thenThrow(EmployeeAlreadyAddedException.class);
        assertThrows(EmployeeIllegalNameOrLastNameException.class,()->employeeService
                .add("Федор1", "Фед", 34.0, 4));
        assertThrows(EmployeeAlreadyAddedException.class,()->employeeService
                .add("Федор", "Фед", 34.0, 4));
    }
    @Test
    public void testFindWhenException(){
        when(employeeService.find("Федор", "Фед"))
                .thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class,()->employeeService
                .find("Федор", "Фед"));

    }
    @Test
    public void testRemoveWhenException(){
        when(employeeService.remove("Федор", "Фед"))
                .thenThrow(EmployeeNotFoundException.class);
        assertThrows(EmployeeNotFoundException.class,()->employeeService
                .remove("Федор", "Фед"));

    }
    @Test
    public void testFindEmployeeMaxSalaryInDepartment(){
     doReturn(emp).when(out.findEmployeeMaxSalaryInDepartment(4));


        assertEquals(34.0,emp.getSalary());

    }


}
