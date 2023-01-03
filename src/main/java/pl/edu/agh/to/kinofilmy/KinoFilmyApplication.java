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
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.film.FilmRepository;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesRepository;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalTime;


@SpringBootApplication
public class KinoFilmyApplication extends Application {


	private ConfigurableApplicationContext springContext;

	private KinoFilmyApplicationController appController;

	private Stage primaryStage;



	@Bean
	public CommandLineRunner testEmployeeInsert(RolesRepository rolesRepository, EmployeeRepository employeeRepository) {
		return args -> {
			Roles roles = new Roles("Admin", true, true, true);
			if(rolesRepository.findAll().isEmpty()) {
				if (rolesRepository.count() == 0) {
					rolesRepository.save(roles);
				}
			}
			if(employeeRepository.findAll().isEmpty()){
				Employee employee = new Employee("Jan", "Kowalski", rolesRepository.findByRoleName("Admin"), "admin", "admin", "jank@mail.pl", "+48 123 123 123");
				employeeRepository.save(employee);
			}
		};
	}


	@Bean
	public CommandLineRunner insertMovies(FilmRepository repository) {
		return args -> {
			File fi = new File(KinoFilmyApplication.class.getResource("/posters/interstellar.jpg").toURI());
			byte[] fileContent = Files.readAllBytes(fi.toPath());
			Film film = new Film("Interstellar",LocalTime.of(2,49,0,0),"Science Fiction","Christopher Nolan",fileContent);

			repository.save(film);
		};
	}



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
