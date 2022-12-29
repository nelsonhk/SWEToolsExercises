import javax.persistence.*;

@Entity
@Table(name="Employee")
public class Employee {

    @Id
    @Column(name="employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long employeeID;

    @Column(name="department")
    private String department;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="salary")
    private double salary;

    public Employee(long employeeID, String department, String jobTitle, double salary) {
        this.employeeID = employeeID;
        this.department = department;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public Employee() {
    }

    public long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
