package pl.edu.agh.to.kinofilmy.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import pl.edu.agh.to.kinofilmy.model.Employee.Employee;
import pl.edu.agh.to.kinofilmy.model.Employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.Roles.Roles;


public class NewUserController {
    private EmployeeService employeeService;
    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private TextField roleInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private Button submit;

    public NewUserController(){    }

    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @FXML
    public void initialize(){

    }

    @FXML
    public void handleSubmitAction(ActionEvent event){
        Employee newEmployee = new Employee(
                firstNameInput.getText(),
                lastNameInput.getText(),
                new Roles(roleInput.getText(), false, false, false),
                emailInput.getText(),
                phoneInput.getText()
        );
        this.employeeService.addEmployee(newEmployee);
    }
}
