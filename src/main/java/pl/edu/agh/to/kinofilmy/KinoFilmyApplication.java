package pl.edu.agh.to.kinofilmy;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.to.kinofilmy.controllers.AppController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeRepository;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeService;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesRepository;

@SpringBootApplication
public class KinoFilmyApplication extends Application {

	private Stage primaryStage;
	private AppController appController;

	public static void main(String[] args) {
		SpringApplication.run(KinoFilmyApplication.class, args);
		Application.launch(args);
	}

	@Bean
	public CommandLineRunner testEmployeeInsert(RolesRepository rolesRepository, EmployeeRepository employeeRepository) {
		return args -> {
			Roles roles = new Roles("Admin", true, true, true);
			if(rolesRepository.count() == 0) {
				rolesRepository.save(roles);
			}
			System.out.println(rolesRepository.findAll());
			Employee employee = new Employee("Jan", "Kowalski", rolesRepository.getReferenceById((long) 1), "jank@mail.pl", "+48 123 123 123");
			employeeRepository.save(employee);
			System.out.println(employeeRepository.findAll());
			EmployeeService.getInstance().setEmployeeRepository(employeeRepository);
		};
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.appController = new AppController(this.primaryStage);
		this.appController.showAddUserForm();
	}
}
