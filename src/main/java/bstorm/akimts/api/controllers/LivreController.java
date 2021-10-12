package bstorm.akimts.api.controllers;

import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.service.LivreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livre")
public class LivreController {

    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

//    GET http://localhost:8080/livre/all
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @GetMapping("/all")
    public List<LivreDTO> getAll(){
        return service.getAll();
    }
}
