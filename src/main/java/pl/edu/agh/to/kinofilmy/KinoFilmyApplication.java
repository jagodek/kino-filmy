package pl.edu.agh.to.kinofilmy;

import javafx.application.Application;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import pl.edu.agh.to.kinofilmy.controllers.KinoFilmyApplicationController;
import pl.edu.agh.to.kinofilmy.json.JsonLoader;
import pl.edu.agh.to.kinofilmy.model.employee.EmployeeRepository;
import pl.edu.agh.to.kinofilmy.model.film.FilmRepository;
import pl.edu.agh.to.kinofilmy.model.roles.RolesRepository;

import java.io.InputStreamReader;


@SpringBootApplication
public class KinoFilmyApplication extends Application {


	private ConfigurableApplicationContext springContext;

	private KinoFilmyApplicationController appController;

	private Stage primaryStage;

	@Value("classpath:init.json")
	private Resource jsonFile;



	@Bean
	public CommandLineRunner initializeApp(JsonLoader jsonLoader, RolesRepository rolesRepository, EmployeeRepository employeeRepository, FilmRepository filmRepository) {
		return args -> {
//			Roles roles = new Roles("Admin", true, true, true,true,true,true);
//			if(rolesRepository.findAll().isEmpty()) {
//				if (rolesRepository.count() == 0) {
//					rolesRepository.save(roles);
//				}
//			}
//			if(employeeRepository.findAll().isEmpty()){
//				Employee employee = new Employee("Jan", "Kowalski", rolesRepository.findByRoleName("Admin"), "admin", "admin", "jank@mail.pl", "+48 123 123 123");
//				employeeRepository.save(employee);
//			}
			JSONObject obj = (JSONObject) new JSONParser().parse(new InputStreamReader(jsonFile.getInputStream()));
			Object filmsJsonArrayObj = obj.get("films");
			JSONArray filmsJsonArray = (JSONArray) filmsJsonArrayObj;
			filmRepository.saveAll(jsonLoader.jsonObjectToFilmList(filmsJsonArray));

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
