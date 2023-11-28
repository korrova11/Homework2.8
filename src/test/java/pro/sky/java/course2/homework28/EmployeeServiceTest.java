package pro.sky.java.course2.homework28;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.java.course2.homework28.exception.EmployeeAlreadyAddedException;
import pro.sky.java.course2.homework28.exception.EmployeeIllegalNameOrLastNameException;
import pro.sky.java.course2.homework28.exception.EmployeeNotFoundException;
import pro.sky.java.course2.homework28.model.Employee;
import pro.sky.java.course2.homework28.service.EmployeeService;
import pro.sky.java.course2.homework28.service.EmployeeServiceImpl;

import java.util.List;
import java.util.Map;

public class EmployeeServiceTest {
    EmployeeServiceImpl out = new EmployeeServiceImpl();

    @Test
    public void testCreateList() {
        Assertions.assertNotNull(out.createList());
    }


    @Test
    public void testAddWillThrowExceptionWhenContains() {
        out.add("Антон","Антонов",25000.0,4);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class,
                ()->{
            out.add("Антон","Антонов",25000.0,4);
                });


    }
    @Test
    public void testAddOnContains() {
        Employee emp = new Employee("Антон","Антонов",25000.0,4);
        out.add("Антон","Антонов",25000.0,4);
        Assertions.assertEquals(out.find("Антон","Антонов"),emp);

    }
    @Test
    public void testAddWillThrowExceptionWhenIllegalName() {

        Assertions.assertThrows(EmployeeIllegalNameOrLastNameException.class,
                ()->{
            out.add("Антон!","Антонов",25000.0,4);
                });


    }

    @Test
    public void testRemoveNotContains() {
        Assertions.assertThrows(EmployeeNotFoundException.class,
                ()->{
            out.remove("Винни","Пух");
                });

    }

    @Test
    public void testFindOnContains() {
        Assertions.assertNotNull(out.find("Сергей",
                "Сергеев"));

    }

    @Test
    public Map<String, Employee> findAll() {
        return null;
    }


}

