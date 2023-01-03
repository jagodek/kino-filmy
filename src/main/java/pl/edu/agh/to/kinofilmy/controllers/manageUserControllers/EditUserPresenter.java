package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class EditUserPresenter {
    private final EmployeeService employeeService;
    private final RolesService rolesService;

    private Stage editUserStage;
    private Employee user;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private ChoiceBox<Roles> roleInput;

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField passwordInput;

    @FXML
    private TextField emailInput;

    @FXML
    private TextField phoneInput;

    @FXML
    private Button submit;

    public EditUserPresenter(EmployeeService employeeService, RolesService rolesService) {
        this.employeeService = employeeService;
        this.rolesService = rolesService;
    }
    @FXML
    private Label firstNameError;
    @FXML
    public Label lastNameError;
    @FXML
    public Label usernameError;
    @FXML
    public Label passwordError;
    @FXML
    public Label emailError;
    @FXML
    public Label phoneError;
    @FXML
    public Label rolesError;


    public void setStage(Stage stage) {
        this.editUserStage = stage;
    }
    public void setEmployee(Employee employee){
        this.user = employee;
        firstNameInput.setText(this.user.getFirstname());
        lastNameInput.setText(this.user.getLastname());
        roleInput.setValue(this.user.getRole());
        emailInput.setText(this.user.getEmail());
        phoneInput.setText(this.user.getPhoneNumber());
        usernameInput.setText(this.user.getUsername());
        passwordInput.setText(this.user.getPassword());
    }

    @FXML
    public void initialize(){
        for (Roles role :this.rolesService.findAll()) {
            this.roleInput.getItems().add(role);
        }
        this.roleInput.setOnAction((event -> {
            this.roleInput.setValue(this.roleInput.getValue());
        }));

        this.firstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
                Pair<Boolean,String> err = isNameValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.firstNameError.setText(msg);
                }
                else{
                    this.firstNameError.setText("");
                }

            setButton();

        });


       this.lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            Pair<Boolean,String> err = isNameValid(newValue);
            boolean valid = err.getKey();
            String msg = err.getValue();
            if(!valid){
                this.lastNameError.setText(msg);
            }
            else{
                this.lastNameError.setText("");
            }

            setButton();
       });


        this.roleInput.valueProperty().addListener((observable, oldValue, newValue) -> {
                  Pair<Boolean,String> err = isRolesValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.rolesError.setText(msg);
                }
                else{
                    this.rolesError.setText("");
                }
            setButton();

        });
        this.usernameInput.textProperty().addListener((observable, oldValue, newValue) -> {
                Pair<Boolean,String> err = isUsernameValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.usernameError.setText(msg);
                }
                else{
                    this.usernameError.setText("");
                }
            setButton();
        });


        this.passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
                Pair<Boolean,String> err = isPasswordValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.passwordError.setText(msg);
                }
                else{
                    this.passwordError.setText("");
                }
            setButton();
        });


        this.emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
                Pair<Boolean,String> err = isEmailValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.emailError.setText(msg);
                }
                else{
                    this.emailError.setText("");
                }
            setButton();
        });


        this.phoneInput.textProperty().addListener((observable, oldValue, newValue) -> {
                Pair<Boolean,String> err = isPhoneValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.phoneError.setText(msg);
                }
                else{
                    this.phoneError.setText("");
                }

            setButton();
        });

    }


    private void setButton(){
        if(isNameValid(this.firstNameInput.getText()).getKey() &&
                isNameValid(this.lastNameInput.getText()).getKey() &&
                isRolesValid(this.roleInput.getValue()).getKey() &&
                isUsernameValid(this.usernameInput.getText()).getKey() &&
                isPasswordValid(this.passwordInput.getText()).getKey() &&
                isEmailValid(this.emailInput.getText()).getKey() &&
                isPhoneValid(this.phoneInput.getText()).getKey()

        ){
            this.submit.setDisable(false);
        }
        else
            this.submit.setDisable(true);
    }


    private Pair<Boolean,String> isRolesValid(Roles role){
        if(role == null){
            return new Pair<>(false,"role required");
        }
        return new Pair<>(true,"");
    }
    private Pair<Boolean,String> isNameValid(String string){
        if(string.equals("")){
            return new Pair<>(false,"name required");
        }
        else if(!string.matches("[A-ZĄŁÓŻŹĆĘŚŃ]{1}[a-zęóąśłżźćń]*"))
        {
            return  new Pair<>(false,"Wrong name format");
        }
        return new Pair<>(true,"");
    }

    private Pair<Boolean,String> isUsernameValid(String string){
        if(string.equals("")){
            return new Pair<>(false,"username required");
        }
        else if(!string.matches("[A-ZĄŁÓŻŹĆĘŚŃa-zęóąśłżźćń_$]{1}.*"))
        {
            return  new Pair<>(false,"Wrong name format");
        }
        return new Pair<>(true,"");
    }

    private Pair<Boolean,String> isPasswordValid(String string){
        if(string.equals("")){
            return new Pair<>(false,"password required, do i have to say it?");
        }
        else if(!string.matches(".{6}.+"))
        {
            return  new Pair<>(false,"Password too short");
        }
        return new Pair<>(true,"");
    }

    private Pair<Boolean,String> isEmailValid(String string){
        if(string.equals("")){
            return new Pair<>(false,"email required");
        }
        else if(!string.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
        {
            return  new Pair<>(false,"Wrong format of email");
        }
        return new Pair<>(true,"");
    }

    private Pair<Boolean, String> isPhoneValid(String string){
        if(string.equals("")){
            return new Pair<>(false,"Phone number is required");
        }
        else if(!string.matches("^([+]?([0-9]{1}[ ]?){3})?([0-9]{1}[\\s-]*){9}$")){
            return new Pair<>(false,"wrong phone number format");
        }
        return new Pair<>(true,"");
    }

    @FXML
    public void handleSubmitAction(ActionEvent event){

            boolean isEdited = false;
            if(!Objects.equals(firstNameInput.getText(), this.user.getFirstname())){
                this.user.setFirstname(firstNameInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(lastNameInput.getText(), this.user.getLastname())){
                this.user.setLastname(lastNameInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(emailInput.getText(), this.user.getEmail())){
                this.user.setEmail(emailInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(phoneInput.getText(), this.user.getPhoneNumber())){
                this.user.setPhoneNumber(phoneInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(roleInput.getValue(), this.user.getRole())){
                this.user.setRole(roleInput.getValue());
                isEdited = true;
            }
            if(!Objects.equals(usernameInput.getText(), this.user.getUsername())){
                this.user.setUsername(usernameInput.getText());
                isEdited = true;
            }
            if(!Objects.equals(passwordInput.getText(), this.user.getPassword())){
                this.user.setPassword(passwordInput.getText());
                isEdited = true;
            }

            if(isEdited){
                this.employeeService.addEmployee(this.user);
            }

            this.editUserStage.close();

    }
}
