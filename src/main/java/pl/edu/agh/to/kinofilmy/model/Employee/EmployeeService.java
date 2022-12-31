package pl.edu.agh.to.kinofilmy.model.Employee;

import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private static final EmployeeService instance = new EmployeeService();
    private EmployeeRepository employeeRepository;

    private EmployeeService() {}

    public static EmployeeService getInstance(){
        return EmployeeService.instance;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    public void addEmployee(Employee employee){
        this.employeeRepository.save(employee);
    }
}
