package controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

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

}
