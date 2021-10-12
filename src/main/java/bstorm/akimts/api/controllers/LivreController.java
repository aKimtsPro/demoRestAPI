package bstorm.akimts.api.controllers;

import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.service.LivreService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{isbn}")
    public LivreDTO getOne(@PathVariable String isbn){
        return service.getOne(isbn);
    }
}
