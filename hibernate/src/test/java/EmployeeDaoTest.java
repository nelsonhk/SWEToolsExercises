import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EmployeeDaoTest {
    static Dao<Employee, Long> employeeDao;

    @BeforeAll
    static void setUp() throws Exception {
        employeeDao = new Dao<Employee, Long>();
        employeeDao.setup("hibernate.cfg.xml");
    }

    @AfterAll
    static void tearDown() {
        employeeDao.clear("Employee");
        employeeDao.exit();
    }

    @Nested
    class EmployeeTests {
        Employee employee;

        @BeforeEach
        void setUp() {
            employee = new Employee();
            employee.setDepartment("Accounting");
            employee.setJobTitle("Accountant");
            employee.setSalary(40000.0);
        }

        @Test
        void create() {
            employeeDao.create(employee);
            Employee employeeFromDB = employeeDao.read(Employee.class, employee.getEmployeeID());
            assertEquals(employee.getEmployeeID(), employeeFromDB.getEmployeeID());
        }

        @Test
        void update() {
            employeeDao.create(employee);
            employee.setJobTitle("Updated Job Title");
            employeeDao.update(employee);
            Employee employeeFromDB = employeeDao.read(Employee.class, employee.getEmployeeID());
            assertEquals(employee.getJobTitle(), employeeFromDB.getJobTitle());
        }

        @Test
        void delete() throws Exception {
            employeeDao.create(employee);
            Employee employeeFromDB = employeeDao.read(Employee.class, employee.getEmployeeID());
            if (employeeFromDB != null){
                employeeDao.delete(employee);
                employeeFromDB = employeeDao.read(Employee.class, employee.getEmployeeID());
                assertNull(employeeFromDB);
            }
            else {
                throw new Exception("Create Doesn't Work");
            }
        }
    }

}
