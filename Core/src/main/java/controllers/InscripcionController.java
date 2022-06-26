package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import persistence.entities.Socio;
import services.interfaces.ISocioService;

import java.util.List;


@Controller
public class InscripcionController {
    @Autowired
    private ISocioService socioService;

    @RequestMapping(value = "/actividades", method = RequestMethod.GET)
    public String signup (Model model) {
        List<Socio> listaSocio = socioService.findAll();
        model.addAttribute("socios", listaSocio);

        return "/inscripcion/signup";
    }
}
