package pl.edu.agh.to.kinofilmy.json;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import pl.edu.agh.to.kinofilmy.KinoFilmyApplication;
import pl.edu.agh.to.kinofilmy.model.film.Film;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonLoader {

    public List<Film> jsonObjectToFilmList(JSONArray jsonArray) throws IOException, URISyntaxException {
        ArrayList<Film> filmArrayList = new ArrayList<>();
        for(Object obj: jsonArray){
            Film film = new Film();
            JSONObject jsonObject = (JSONObject) obj;
            film.setTitle((String) jsonObject.get("title"));
            film.setRuntime(LocalTime.parse((String) jsonObject.get("runtime")));
            film.setGenre((String) jsonObject.get("genre"));
            film.setDirector((String) jsonObject.get("director"));
            File fi = new File(KinoFilmyApplication.class.getResource((String) jsonObject.get("iconPath")).toURI());
            film.setIcon(Files.readAllBytes(fi.toPath()));
            filmArrayList.add(film);
        }

        return filmArrayList;
    }
}
