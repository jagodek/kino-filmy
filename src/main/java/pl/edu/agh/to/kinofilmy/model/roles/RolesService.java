package pl.edu.agh.to.kinofilmy.model.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class RolesService {

    private final RolesRepository repository;

    public RolesService(RolesRepository repository) {
        this.repository = repository;
    }

    public List<Roles> findAll(){
        return this.repository.findAll();
    }

    public Optional<Roles> findRolesByName(String name){
        return  this.repository.findRolesByRoleName(name);
    }
}
