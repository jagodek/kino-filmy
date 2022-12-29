package pl.edu.agh.to.kinofilmy;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import pl.edu.agh.to.kinofilmy.Employee.Employee;
import pl.edu.agh.to.kinofilmy.Employee.EmployeeRepository;
import pl.edu.agh.to.kinofilmy.Roles.Roles;
import pl.edu.agh.to.kinofilmy.Roles.RolesRepository;

@SpringBootApplication
public class KinoFilmyApplication extends Application {



	public static void main(String[] args) {
		SpringApplication.run(KinoFilmyApplication.class, args);
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
		};
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

	}
}
