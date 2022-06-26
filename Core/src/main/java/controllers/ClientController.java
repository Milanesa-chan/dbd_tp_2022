package controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import persistence.dao.ISocioDAO;
import persistence.entities.GrupoFamiliar;
import persistence.entities.Socio;
import services.interfaces.IGrupoFamiliarService;
import services.interfaces.ISocioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private IGrupoFamiliarService grupoFamiliarService;
    @Autowired
    private ISocioService socioService;

    @RequestMapping(value = "/newClient", method = RequestMethod.GET)
    public String selectUserType () {
        return "/clients/selectUserType";
    }

    @RequestMapping(value = "/newClient/group", method = RequestMethod.GET)
    public String newGroup() {
        return "/clients/new_group";
    }

    @RequestMapping(value = "/newClient/user", method = RequestMethod.GET)
    public String newUser(Model model) {

        List<Socio> socios = socioService.getSociosTitulares();
        model.addAttribute("socios", socios);

        return "/clients/new_user";
    }

    @RequestMapping(value = "/newClient/group/save", method = RequestMethod.POST)
    public String saveGroup(@RequestParam(name="domicilio") String domicilio, @RequestParam(name="tel_fijo") String telefono,
                            @RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido,
                            @RequestParam(name="celular") String celular, @RequestParam(name="fecha_nac") String fecha_nac) throws ParseException {

        GrupoFamiliar grupoFamiliar = new GrupoFamiliar(domicilio,telefono);
        Date fecha_nacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_nac);
        Date fecha_inscripcion_club = Date.from(Instant.now());
        int cod_base = grupoFamiliarService.save(grupoFamiliar);
        Socio socio = new Socio(cod_base, 0, nombre,apellido,celular,fecha_nacimiento,fecha_inscripcion_club,'M');
        int id_socio = socioService.save(socio);
        return "/clients/new_group";
    }

    @RequestMapping(value = "/newClient/user/save", method = RequestMethod.POST)
    public String saveGroup(@RequestParam(name="nombre") String nombre, @RequestParam(name="apellido") String apellido,
                            @RequestParam(name="celular") String celular, @RequestParam(name="fecha_nac") String fecha_nac,
                            @RequestParam(name="tipo_socio") char tipo_socio,
                            @RequestParam(name="idGrupo") int idGrupo) throws ParseException {

        Date fecha_nacimiento = new SimpleDateFormat("dd/MM/yyyy").parse(fecha_nac);
        Date fecha_inscripcion_club = Date.from(Instant.now());
        Socio socio = new Socio(idGrupo,nombre,apellido,celular,fecha_nacimiento,fecha_inscripcion_club, tipo_socio);
        int id_socio = socioService.save(socio);
        return "/clients/new_group";
    }
}
