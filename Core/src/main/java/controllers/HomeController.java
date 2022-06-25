package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import persistence.entities.GrupoFamiliar;
import services.interfaces.IGrupoFamiliarService;

@Controller
public class HomeController {
    @Autowired
    private IGrupoFamiliarService grupoFamiliarService;

    @GetMapping("/")
    public String home () {
        return "index";
    }

    @RequestMapping(value = "/example", method = RequestMethod.GET)
    public String example () {
        GrupoFamiliar gf = grupoFamiliarService.findOne(103);
        int cod_base = grupoFamiliarService.save(gf);
        System.out.println(gf.getDomicilio());
        System.out.println("Codigo Base" + cod_base);
        return "index";
    }
}
