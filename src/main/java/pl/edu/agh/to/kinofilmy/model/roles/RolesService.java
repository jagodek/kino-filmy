package pl.edu.agh.to.kinofilmy.model.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;

@Service
public class RolesService {

    @Autowired
    private RolesRepository repository;

}
