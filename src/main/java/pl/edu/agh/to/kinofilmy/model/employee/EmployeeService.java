package pl.edu.agh.to.kinofilmy.model.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public Optional<Roles> login(String username, String password) {
        Optional<Employee> employee = repository.findByUsername(username);
        if(employee.isPresent()){
            if (employee.get().getPassword().equals(password)){
                return Optional.of(employee.get().getRole());
            }
        }
        return Optional.empty();
    }
}
