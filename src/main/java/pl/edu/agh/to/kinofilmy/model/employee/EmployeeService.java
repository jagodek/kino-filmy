package pl.edu.agh.to.kinofilmy.model.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {

    final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Optional<Employee> login(String username, String password) {
        Optional<Employee> employee = repository.findEmployeeByUsername(username);
        if(employee.isPresent()){
            if (employee.get().getPassword().equals(password)){
                return employee;
            }
        }
        return Optional.empty();
    }

    public ObservableList<EmployeeDisplay> findAllAsEmployeeDisplay(){
        ObservableList<EmployeeDisplay> employeeDisplayList = FXCollections.observableArrayList();
        for(Employee employee: this.repository.findAll()){
            employeeDisplayList.add(new EmployeeDisplay(employee));
        }
        return employeeDisplayList;
    }

    public void save(Employee employee) {
        this.repository.save(employee);
    }
    public void update(Employee employee) {
        Optional<Employee> optionalEmployee = this.repository.findEmployeeByUsername(employee.getUsername());
        if(optionalEmployee.isPresent()){
            Employee old = optionalEmployee.get();
            System.out.println(old.getFirstname() + " " + employee.getFirstname());
            if(!Objects.equals(old.getFirstname(), employee.getFirstname())){
                old.setFirstname(employee.getFirstname());
            }
            if(!Objects.equals(old.getLastname(), employee.getLastname())){
                old.setLastname(employee.getLastname());
            }
            if(!Objects.equals(old.getEmail(), employee.getEmail())){
                old.setEmail(employee.getEmail());
            }
            if(!Objects.equals(old.getPhoneNumber(), employee.getPhoneNumber())){
                old.setPhoneNumber(employee.getPhoneNumber());
            }
            if(!Objects.equals(old.getUsername(), employee.getUsername())){
                old.setUsername(employee.getUsername());
            }
            if(!Objects.equals(old.getPassword(), employee.getPassword())){
                old.setPassword(employee.getPassword());
            }
            if(!Objects.equals(old.getRole(), employee.getRole())){
                old.setRole(employee.getRole());
            }
        }
    }

    public void deleteEmployee(Employee employee){
        Optional<Employee> optionalEmployee = this.repository.findEmployeeByUsername(employee.getUsername());
        if(optionalEmployee.isPresent()){
            this.repository.delete(optionalEmployee.get());
        }

    }

    public Employee employeeFromEmployeeDisplay(EmployeeDisplay employee){
        return new Employee(employee);
    }

    public List<String> getAllEmployeeEmailAddresses(){
        LinkedList<String> emailList = new LinkedList<>();
        for(Employee employee: repository.findAll()){
            emailList.add(employee.getEmail());
        }
        return emailList;
    }


}
