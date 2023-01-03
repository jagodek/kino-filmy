package pl.edu.agh.to.kinofilmy.controllers.manageUserControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Controller
public class NewUserController {

    private final KinoFilmyApplicationController applicationController;

    private final EmployeeService employeeService;

    private final RolesService rolesService;


    private Stage newUserStage;

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



    public NewUserController(KinoFilmyApplicationController applicationController, EmployeeService employeeService, RolesService rolesService){
        this.applicationController = applicationController;
        this.employeeService = employeeService;
        this.rolesService = rolesService;

    }

    public void setNewUserStage(Stage newUserStage) {
        this.newUserStage = newUserStage;
    }

    @FXML
    public void initialize(){
        for (Roles role :this.rolesService.findAll()) {
            this.roleInput.getItems().add(role);
        }
        this.roleInput.setOnAction((event -> {
            this.roleInput.setValue(this.roleInput.getValue());
        }));





        AtomicBoolean firstNameInputTouched = new AtomicBoolean(false);
        this.firstNameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!firstNameInputTouched.get()){
                firstNameInputTouched.set(true);
                this.firstNameError.setText("First name required");
            }
        });
        this.firstNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(firstNameInputTouched.get()){
                Pair<Boolean,String> err = isNameValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.firstNameError.setText(msg);
                }
                else{
                    this.firstNameError.setText("");
                }
            }
            setButton();

        });


        AtomicBoolean lastNameInputTouched = new AtomicBoolean(false);
        this.lastNameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!lastNameInputTouched.get()){
                lastNameInputTouched.set(true);
                this.lastNameError.setText("Last name required");
            }
        });
        this.lastNameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(lastNameInputTouched.get()){
                Pair<Boolean,String> err = isNameValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.lastNameError.setText(msg);
                }
                else{
                    this.lastNameError.setText("");
                }
            }
            setButton();

        });

        AtomicBoolean rolesInputTouched = new AtomicBoolean(false);
        this.roleInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!rolesInputTouched.get()){
                rolesInputTouched.set(true);
                this.rolesError.setText("Role required");
                System.out.println(this.roleInput.getValue());
            }
        });
        this.roleInput.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(rolesInputTouched.get()){
                Pair<Boolean,String> err = isRolesValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.rolesError.setText(msg);
                }
                else{
                    this.rolesError.setText("");
                }
            }
            setButton();

        });



        AtomicBoolean usernameInputTouched = new AtomicBoolean(false);
        this.usernameInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!usernameInputTouched.get()){
                usernameInputTouched.set(true);
                this.usernameError.setText("Username required");
            }
        });
        this.usernameInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(usernameInputTouched.get()){
                Pair<Boolean,String> err = isUsernameValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.usernameError.setText(msg);
                }
                else{
                    this.usernameError.setText("");
                }
            }
            setButton();

        });

        AtomicBoolean passwordInputTouched = new AtomicBoolean(false);
        this.passwordInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!passwordInputTouched.get()){
                passwordInputTouched.set(true);
                this.passwordError.setText("Password  required");
            }
        });
        this.passwordInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(passwordInputTouched.get()){
                Pair<Boolean,String> err = isPasswordValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.passwordError.setText(msg);
                }
                else{
                    this.passwordError.setText("");
                }
            }
            setButton();

        });

        AtomicBoolean emailInputTouched = new AtomicBoolean(false);
        this.emailInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!emailInputTouched.get()){
                emailInputTouched.set(true);
                this.emailError.setText("email  required");
            }
        });
        this.emailInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(emailInputTouched.get()){
                Pair<Boolean,String> err = isEmailValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.emailError.setText(msg);
                }
                else{
                    this.emailError.setText("");
                }
            }
            setButton();

        });

        AtomicBoolean phoneInputTouched = new AtomicBoolean(false);
        this.phoneInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!phoneInputTouched.get()){
                phoneInputTouched.set(true);
                this.phoneError.setText("phone  required");
            }
        });
        this.phoneInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if(phoneInputTouched.get()){
                Pair<Boolean,String> err = isPhoneValid(newValue);
                boolean valid = err.getKey();
                String msg = err.getValue();
                if(!valid){
                    this.phoneError.setText(msg);
                }
                else{
                    this.phoneError.setText("");
                }
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
        Employee newEmployee = new Employee(
                firstNameInput.getText(),
                lastNameInput.getText(),
                roleInput.getValue(),
                usernameInput.getText(),
                passwordInput.getText(),
                emailInput.getText(),
                phoneInput.getText()
        );

        this.employeeService.addEmployee(newEmployee);
        this.newUserStage.close();
    }
}
