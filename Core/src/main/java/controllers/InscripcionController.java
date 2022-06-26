package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import persistence.entities.Actividad;
import persistence.entities.SeInscribeEn;
import persistence.entities.Socio;
import persistence.entities.Turno;
import services.interfaces.IActividadService;
import services.interfaces.ISeInscribeEnService;
import services.interfaces.ISocioService;
import services.interfaces.ITurnoService;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Controller
public class InscripcionController {
    @Autowired
    private ISocioService socioService;

    @Autowired
    private IActividadService actividadService;

    @Autowired
    private ITurnoService turnoService;

    @Autowired
    private ISeInscribeEnService seInscribeEnService;



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

    @RequestMapping(value="/actividades/{idGrupoFamiliar}/socio/{idSocio}/actividades/{idActividad}/turnos/{idTurno}/inscribir", method = RequestMethod.POST)
    public String inscribir(@PathVariable(name="idGrupoFamiliar") int idGrupoFamiliar, @PathVariable(name="idSocio") int idSocio, @PathVariable(name="idActividad") int idActividad, @PathVariable(name="idTurno") int idTurno) {
        try{
            SeInscribeEn inscripcion = new SeInscribeEn(idGrupoFamiliar, idSocio, idTurno, Date.from(Instant.now()), true);
            seInscribeEnService.save(inscripcion);
            return "redirect:/actividades";
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Error al inscribir al socio en la actividad", e);
        }
    }


}
