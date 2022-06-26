package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import persistence.entities.Actividad;
import persistence.entities.Socio;
import persistence.entities.Turno;
import services.interfaces.IActividadService;
import services.interfaces.ISocioService;
import services.interfaces.ITurnoService;

import java.util.List;


@Controller
public class InscripcionController {
    @Autowired
    private ISocioService socioService;

    @Autowired
    private IActividadService actividadService;

    @Autowired
    private ITurnoService turnoService;



    @ResponseBody
    @RequestMapping(value="/actividades/grupoFamiliar/{cod_base}/socios", method = RequestMethod.GET)
    public List<Socio> getSocios(@PathVariable(name="cod_base") int cod_base) {
        try{
            List<Socio> socios = socioService.getSociosByCodBase(cod_base);
            return socios;

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="/actividades/{cod_base}/socio/{idSocio}/actividadesDisponibles",method=RequestMethod.GET)
    public List<Actividad> getActividades(@PathVariable(name="cod_base") int cod_base, @PathVariable(name="idSocio") int idSocio) {
        try{
            List<Actividad> actividades = actividadService.findAllBySocio(cod_base, idSocio);
            return actividades;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @ResponseBody
    @RequestMapping(value="/actividades/{idActividad}/turnosDisponibles",method=RequestMethod.GET)
    public List<Turno> getTurnos(@PathVariable(name="idActividad") int idActividad, Model model) {
        try{
            List<Turno> turnos = turnoService.findAllByActividad(idActividad);
            return turnos;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    @RequestMapping(value = "/actividades", method = RequestMethod.GET)
    public String signup (Model model) {
        List <Socio> titulares = socioService.getSociosTitulares();
//        List<Socio> listaSocio = socioService.findAll();
//        model.addAttribute("socios", listaSocio);
        model.addAttribute("titulares", titulares);

        return "/inscripcion/signup";
    }

    @RequestMapping(value="/actividades/{idGrupoFamiliar}/socio/{idSocio+}/actividades/{idActividad}/turnos/{idTurno}/inscribir", method = RequestMethod.POST)
    public String inscribir(@PathVariable(name="idGrupoFamiliar") int idGrupoFamiliar, @PathVariable(name="idSocio") int idSocio, @PathVariable(name="idActividad") int idActividad, @PathVariable(name="idTurno") int idTurno) {
        try{
            socioService.inscribir(idGrupoFamiliar, idSocio, idActividad, idTurno);
            return "redirect:/actividades";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/actividades";
        }
    }


}
