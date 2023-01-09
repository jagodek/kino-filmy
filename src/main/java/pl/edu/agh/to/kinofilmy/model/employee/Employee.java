package pl.edu.agh.to.kinofilmy.model.employee;

import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The data model class for a record in the Employee database, containing general information about employees.
 *
 */
@Entity
public class Employee {
    private Long id;

    @NotBlank(message = "First name is mandatory")
    private String firstname;

    @NotBlank(message = "Last name is mandatory")
    private String lastname;


    private Roles role;

    @Email(message = "Email is incorrect")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Size(min = 9, max = 15)
    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    private String username;

    private String password;

    public Employee(String firstname, String lastname, Roles role, String username, String password, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Employee(EmployeeDisplay employeeDisplay) {
        this.setFirstname(employeeDisplay.getFirstname());
        this.setLastname(employeeDisplay.getLastname());
        this.setUsername(employeeDisplay.getUsername());
        this.setPassword(employeeDisplay.getPassword());
        this.setEmail(employeeDisplay.getEmail());
        this.setPhoneNumber(employeeDisplay.getPhoneNumber());
        this.setRole(employeeDisplay.getRole());
    }

    public Employee() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @ManyToOne
    public Roles getRole() {
        return role;
    }

    public void setRole(Roles roles) {
        this.role = roles;
    }

    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role=" + role +
                ", username=" + username +
                '}';
    }
}
