package pl.edu.agh.to.kinofilmy.model.employee;

import javafx.beans.property.*;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

public class EmployeeDisplay {
    private LongProperty id;
    private StringProperty firstname;
    private StringProperty lastname;
    private ObjectProperty<Roles> role;
    private StringProperty email;
    private StringProperty phoneNumber;
    private StringProperty username;
    private StringProperty password;

    public EmployeeDisplay(Employee employee) {
        this.firstname = new SimpleStringProperty(employee.getFirstname());
        this.lastname = new SimpleStringProperty(employee.getLastname());
        this.role = new SimpleObjectProperty<>(employee.getRole());
        this.username = new SimpleStringProperty(employee.getUsername());
        this.password = new SimpleStringProperty(employee.getPassword());
        this.email = new SimpleStringProperty(employee.getEmail());
        this.phoneNumber = new SimpleStringProperty(employee.getPhoneNumber());
    }

    public Long getId() {
        return id.get();
    }

    public String getFirstname() {
        return firstname.get();
    }

    public String getLastname() {
        return lastname.get();
    }

    public  Roles getRole(){return role.get();}

    public String getEmail() {
        return email.get();
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getPassword() {
        return password.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public ObjectProperty<Roles> roleProperty() {
        return role;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }
}
