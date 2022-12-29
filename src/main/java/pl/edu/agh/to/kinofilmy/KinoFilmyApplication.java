package pl.edu.agh.to.kinofilmy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import pl.edu.agh.to.kinofilmy.Controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.Model.Employee.Employee;
import pl.edu.agh.to.kinofilmy.Model.Employee.EmployeeRepository;
import pl.edu.agh.to.kinofilmy.Model.Roles.Roles;
import pl.edu.agh.to.kinofilmy.Model.Roles.RolesRepository;

@SpringBootApplication
public class KinoFilmyApplication extends Application {


	private ConfigurableApplicationContext springContext;

	private KinoFilmyApplicationController appController;

	private Stage primaryStage;

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
		springContext = SpringApplication.run(KinoFilmyApplication.class);

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Kino-Filmy");

		this.appController = new KinoFilmyApplicationController(primaryStage);
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
