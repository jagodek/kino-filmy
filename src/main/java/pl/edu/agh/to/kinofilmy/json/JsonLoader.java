package pl.edu.agh.to.kinofilmy.json;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.kinofilmy.KinoFilmyApplication;
import pl.edu.agh.to.kinofilmy.model.employee.Employee;
import pl.edu.agh.to.kinofilmy.model.film.Film;
import pl.edu.agh.to.kinofilmy.model.roles.Roles;
import pl.edu.agh.to.kinofilmy.model.roles.RolesService;
import pl.edu.agh.to.kinofilmy.model.screen.Screen;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonLoader {

    private final RolesService rolesService;

    public JsonLoader(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    public JSONObject filmToJSONObject(Film film){
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("title",film.getTitle());
        jsonObject.put("runtime", film.getRuntime().format(DateTimeFormatter.ofPattern("HH:mm")));
        jsonObject.put("genre", film.getGenre());
        jsonObject.put("director", film.getDirector());
        String base64EncodedIcon = DatatypeConverter.printBase64Binary(film.getIcon());
        jsonObject.put("filmIcon", base64EncodedIcon);

        return jsonObject;
    }

    public List<Film> jsonArrayToFilmList(JSONArray jsonArray) throws IOException, URISyntaxException {
        ArrayList<Film> filmArrayList = new ArrayList<>();
        for(Object obj: jsonArray){
            Film film = new Film();
            JSONObject jsonObject = (JSONObject) obj;
            film.setTitle((String) jsonObject.get("title"));
            film.setRuntime(LocalTime.parse((String) jsonObject.get("runtime")));
            film.setGenre((String) jsonObject.get("genre"));
            film.setDirector((String) jsonObject.get("director"));
            if(jsonObject.containsKey("iconPath")) {
                File fi = new File(KinoFilmyApplication.class.getResource((String) jsonObject.get("iconPath")).toURI());
                film.setIcon(Files.readAllBytes(fi.toPath()));
            } else if(jsonObject.containsKey("filmIcon")){
                byte[] base64DecodedIcon = DatatypeConverter.parseBase64Binary((String) jsonObject.get("filmIcon"));
                film.setIcon(base64DecodedIcon);
            }
            filmArrayList.add(film);
        }
        return filmArrayList;
    }

    public List<Roles> jsonArrayToRolesList(JSONArray jsonArray) {
        ArrayList<Roles> rolesArrayList = new ArrayList<>();
        for(Object obj: jsonArray){
            Roles roles = new Roles();
            JSONObject jsonObject = (JSONObject) obj;
            roles.setRoleName((String) jsonObject.get("roleName"));
            roles.setManageRoles((Boolean) jsonObject.get("manageRoles"));
            roles.setManageUsers((Boolean) jsonObject.get("manageUsers"));
            roles.setGetStatistics((Boolean) jsonObject.get("getStatistics"));
            roles.setManageCinema((Boolean) jsonObject.get("manageCinema"));
            roles.setSellTickets((Boolean) jsonObject.get("sellTickets"));
            roles.setCheckTickets((Boolean) jsonObject.get("checkTickets"));
            rolesArrayList.add(roles);
        }
        return rolesArrayList;
    }

    public List<Employee> jsonArrayToUsersList(JSONArray jsonArray) {
        ArrayList<Employee> usersArrayList = new ArrayList<>();
        for(Object obj: jsonArray){
            Employee employee = new Employee();
            JSONObject jsonObject = (JSONObject) obj;
            employee.setFirstname((String) jsonObject.get("firstname"));
            employee.setLastname((String) jsonObject.get("lastname"));
            employee.setRole(rolesService.findRolesByName((String) jsonObject.get("role")).get());
            employee.setEmail((String) jsonObject.get("email"));
            employee.setPhoneNumber((String) jsonObject.get("phoneNumber"));
            employee.setUsername((String) jsonObject.get("username"));
            employee.setPassword((String) jsonObject.get("password"));
            usersArrayList.add(employee);
        }
        return usersArrayList;
    }

    public List<Screen> jsonArrayToScreensList(JSONArray jsonArray) {
        ArrayList<Screen> screenArrayList = new ArrayList<>();
        for(Object obj: jsonArray){
            Screen screen = new Screen();
            JSONObject jsonObject = (JSONObject) obj;
            screen.setName((String) jsonObject.get("name"));
            screen.setSeatsNumber(((Long) jsonObject.get("seatsNumber")).intValue());
            screen.setRowNumber(((Long) jsonObject.get("rowsNumber")).intValue());
            screenArrayList.add(screen);
        }
        return screenArrayList;
    }

}
