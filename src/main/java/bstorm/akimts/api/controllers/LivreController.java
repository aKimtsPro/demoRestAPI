package bstorm.akimts.api.controllers;

import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.models.form.LivreForm;
import bstorm.akimts.api.models.form.LivreUpdateForm;
import bstorm.akimts.api.service.LivreService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/livre")
public class LivreController {

    private final LivreService service;

    public LivreController(LivreService service) {
        this.service = service;
    }

//    GET http://localhost:8080/livre
//    GET http://localhost:8080/livre/
//    GET http://localhost:8080/livre/all
//    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @GetMapping(path = {"","/","/all"})
    public List<LivreDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/{isbn}")
    public LivreDTO getOne(@PathVariable String isbn){
        return service.getOne(isbn);
    }

    // GET http://localhost:8080/livre?isbn={valeur}
    @GetMapping(params = {"isbn"})
    public LivreDTO getOneByParam(@RequestParam String isbn){
        return service.getOne(isbn);
    }

    // POST http://localhost:8080/livre
    // POST http://localhost:8080/livre/
    // POST http://localhost:8080/livre/add
    @PostMapping(path = {"", "/", "/add"})
    public LivreDTO insert(@Valid @RequestBody LivreForm form, @RequestHeader HttpHeaders headers){

        for (String key : headers.keySet()) {
            System.out.println( headers.get(key) );
        }
        return service.insert(form);

    }

    @DeleteMapping(params = "isbn")
    public LivreDTO deleteByParam(@RequestParam(name = "isbn") String id){
        return service.delete(id);
    }

    @DeleteMapping("/{isbn}")
    public LivreDTO delete(@PathVariable(name = "isbn") String id){
        return service.delete(id);
    }

    @PutMapping(params = "isbn")
    public LivreDTO update(@RequestParam String isbn,@Valid @RequestBody LivreUpdateForm form){
        return service.update(isbn, form);
    }
}
