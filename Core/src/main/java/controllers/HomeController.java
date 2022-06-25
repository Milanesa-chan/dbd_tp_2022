package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import persistence.entities.GrupoFamiliar;
import services.interfaces.IGrupoFamiliarService;

@Controller
public class HomeController {
    @Autowired
    private IGrupoFamiliarService grupoFamiliarService;

    @GetMapping("/example")
    public String home () {
        GrupoFamiliar gf = grupoFamiliarService.findOne(103);
        System.out.println(gf.getDomicilio());
        return "index";
    }
}
