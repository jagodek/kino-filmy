package pl.edu.agh.to.kinofilmy;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeRepository;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesRepository;

@SpringBootApplication
public class KinoFilmyApplication extends Application {


	private ConfigurableApplicationContext springContext;

	private KinoFilmyApplicationController appController;

	private Stage primaryStage;


	/*
	@Bean
	public CommandLineRunner testEmployeeInsert(RolesRepository rolesRepository, EmployeeRepository employeeRepository) {
		return args -> {
			Roles roles = new Roles("Admin", true, true, true);
			if(rolesRepository.count() == 0) {
				rolesRepository.save(roles);
			}
			System.out.println(rolesRepository.findAll());
			Employee employee = new Employee("Jan", "Kowalski", rolesRepository.getReferenceById((long) 1), "admin", "admin", "jank@mail.pl", "+48 123 123 123");
			employeeRepository.save(employee);
			System.out.println(employeeRepository.findAll());
		};
	}
	 */




	@Override
	public void start(Stage primaryStage) throws Exception {
		springContext = SpringApplication.run(KinoFilmyApplication.class);

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kino-Filmy");
		this.appController = springContext.getBean(KinoFilmyApplicationController.class);

		this.appController.setPrimaryStage(primaryStage);
		this.appController.initRootLayout();
	}

	@Override
	public void stop() throws Exception {
		springContext.stop();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
