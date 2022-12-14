package pl.edu.agh.to.kinofilmy.Employee;

import pl.edu.agh.to.kinofilmy.Roles.Roles;

import javax.persistence.*;

/**
 * The data model class for a record in the Employee database, containing general information about employees.
 *
 */
@Entity
public class Employee {
    private Long id;

    private String firstname;

    private String lastname;

    private Roles role;

    private String email;

    private String phoneNumber;

    public Employee(String firstname, String lastname, Roles role, String email, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.role = role;
        this.email = email;
        this.phoneNumber = phoneNumber;
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role=" + role +
                '}';
    }
}
