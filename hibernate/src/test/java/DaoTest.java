import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class DaoTest {
    static Dao<Book, Long> bookDao;
    static Dao<Employee, Long> employeeDao;

    @BeforeAll
    static void setUp() throws Exception {
        bookDao = new Dao<Book, Long>();
        employeeDao = new Dao<Employee, Long>();
        bookDao.setup("hibernate.cfg.xml");
        employeeDao.setup("hibernate.cfg.xml");
    }

    @AfterAll
    static void tearDown() {
        bookDao.clear("Book");
        bookDao.exit();
        employeeDao.clear("Employee");
        employeeDao.exit();
    }


//    They should have another Nested set of tests
    @Nested
    class BookTests {
        Book book;

        @BeforeEach
        void setUp() {
            book = new Book();
            book.setTitle("Title");
            book.setAuthor("Author");
            book.setPrice(0.0f);
        }

        @Test
        void create() {
            bookDao.create(book);
            Book bookFromDB = bookDao.read(Book.class, book.getBookId());
            assertEquals(book.getTitle(), bookFromDB.getTitle());
        }

        @Test
        void update() {
            bookDao.create(book);
            book.setTitle("Updated Title");
            bookDao.update(book);
            Book bookFromDB = bookDao.read(Book.class, book.getBookId());
            assertEquals(book.getTitle(), bookFromDB.getTitle());
        }

        @Test
        void delete() throws Exception {
            bookDao.create(book);
            Book bookFromDB = bookDao.read(Book.class, book.getBookId());
            if (bookFromDB != null){
                bookDao.delete(book);
                bookFromDB = bookDao.read(Book.class, book.getBookId());
                assertNull(bookFromDB);
            }
            else {
                throw new Exception("Create Doesn't Work");
            }
        }
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