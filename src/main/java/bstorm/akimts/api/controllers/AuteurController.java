package bstorm.akimts.api.controllers;

import bstorm.akimts.api.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.api.exceptions.ElementNotFoundException;
import bstorm.akimts.api.models.dto.AuteurDTO;
import bstorm.akimts.api.models.dto.ErrorDTO;
import bstorm.akimts.api.service.AuteurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auteur")
public class AuteurController {

    private final AuteurService service;

    public AuteurController(AuteurService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuteurDTO> getOne(@PathVariable long id){
        return ResponseEntity.ok( service.getOne(id) );
    }

}
