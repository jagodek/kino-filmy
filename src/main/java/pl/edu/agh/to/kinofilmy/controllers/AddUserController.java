package pl.edu.agh.to.kinofilmy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AddUserController {
    @Autowired
    private KinoFilmyApplicationController applicationController;

}