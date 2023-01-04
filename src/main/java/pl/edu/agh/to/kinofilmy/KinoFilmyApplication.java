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
import pl.edu.agh.to.kinofilmy.model.screen.ScreenRepository;

import java.io.InputStreamReader;


@SpringBootApplication
public class KinoFilmyApplication extends Application {


	private ConfigurableApplicationContext springContext;

	private KinoFilmyApplicationController appController;

	private Stage primaryStage;

	@Value("classpath:init.json")
	private Resource jsonFile;



	@Bean
	public CommandLineRunner initializeApp(JsonLoader jsonLoader, RolesRepository rolesRepository,
										   EmployeeRepository employeeRepository, FilmRepository filmRepository, ScreenRepository screenRepository) {
		return args -> {
			JSONObject obj = (JSONObject) new JSONParser().parse(new InputStreamReader(jsonFile.getInputStream()));
			if(filmRepository.findAll().isEmpty()) {
				Object filmsJsonArrayObj = obj.get("films");
				JSONArray filmsJsonArray = (JSONArray) filmsJsonArrayObj;
				filmRepository.saveAll(jsonLoader.jsonArrayToFilmList(filmsJsonArray));
			}
			if(rolesRepository.findAll().isEmpty()){
				Object rolesJsonArrayObj = obj.get("roles");
				JSONArray rolesJsonArray = (JSONArray) rolesJsonArrayObj;
				rolesRepository.saveAll(jsonLoader.jsonArrayToRolesList(rolesJsonArray));
			}
			if(employeeRepository.findAll().isEmpty()){
				Object usersJsonArrayObj = obj.get("users");
				JSONArray usersJsonArray = (JSONArray) usersJsonArrayObj;
				employeeRepository.saveAll(jsonLoader.jsonArrayToUsersList(usersJsonArray));
			}
			if(screenRepository.findAll().isEmpty()){
				Object screensJsonArrayObj = obj.get("screens");
				JSONArray screensJsonArray = (JSONArray) screensJsonArrayObj;
				screenRepository.saveAll(jsonLoader.jsonArrayToScreensList(screensJsonArray));
			}
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
