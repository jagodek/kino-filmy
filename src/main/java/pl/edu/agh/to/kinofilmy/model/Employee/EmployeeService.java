package pl.edu.agh.to.kinofilmy.model.employee;

import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Optional<Roles> login(String username, String password) {
        Optional<Employee> employee = repository.findEmployeeByUsername(username);
        if(employee.isPresent()){
            if (employee.get().getPassword().equals(password)){
                return Optional.of(employee.get().getRole());
            }
        }
        return Optional.empty();
    }

    public List<Employee> getEmployees(){
        return this.repository.findAll();
    }

    public void addEmployee(Employee employee) {
        this.repository.save(employee);
    }

    public void deleteEmployee(Employee employee){this.repository.delete(employee);}
}
