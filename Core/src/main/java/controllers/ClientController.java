package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import persistence.entities.GrupoFamiliar;
import persistence.entities.Socio;
import services.interfaces.IGrupoFamiliarService;

@Controller
public class ClientController {

    @Autowired
    private IGrupoFamiliarService grupoFamiliarService;

    @RequestMapping(value = "/newClient", method = RequestMethod.GET)
    public String selectUserType () {
        return "/clients/selectUserType";
    }

    @RequestMapping(value = "/newClient/group", method = RequestMethod.GET)
    public String newGroup() {
        return "/clients/new_group";
    }

    @RequestMapping(value = "/newClient/user", method = RequestMethod.GET)
    public String newUser() {
        return "/clients/new_user";
    }

    @RequestMapping(value = "/newClient/group/save", method = RequestMethod.POST)
    public String saveGroup(@RequestParam(name="domicilio") String domicilio, @RequestParam(name="telefono") String telefono,
                            @RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido,
                            @RequestParam(name="celular") String celular, @RequestParam(name="fecha_nac") String fecha_nac,
                            @RequestParam(name="tipo") char tipo) {

        //Guarda Grupo Familiar
        GrupoFamiliar grupoFamiliar = new GrupoFamiliar(domicilio,telefono);
        grupoFamiliarService.save(grupoFamiliar);
        //Guarda el Socio titular
        Socio socio = new Socio(nombre,apellido,celular,fecha_nac,tipo,grupoFamiliar);
        return "/clients/new_group";
    }
}
