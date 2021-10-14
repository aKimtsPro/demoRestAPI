package bstorm.akimts.api.controllers;

import bstorm.akimts.api.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.api.exceptions.ElementNotFoundException;
import bstorm.akimts.api.models.dto.ErrorDTO;
import bstorm.akimts.api.models.dto.LivreDTO;
import bstorm.akimts.api.models.form.LivreForm;
import bstorm.akimts.api.models.form.LivreUpdateForm;
import bstorm.akimts.api.service.LivreService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Instant;
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
    public ResponseEntity<List<LivreDTO>> getAll(){
        return ResponseEntity
                .ok(service.getAll());
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<LivreDTO> getOne(@PathVariable String isbn){
        return ResponseEntity
                .ok( service.getOne(isbn) );
    }

    // GET http://localhost:8080/livre?isbn={valeur}
    @GetMapping(params = {"isbn"})
    public ResponseEntity<LivreDTO> getOneByParam(@RequestParam String isbn){
        return ResponseEntity
                .ok(service.getOne(isbn));
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

//    @ExceptionHandler( ElementNotFoundException.class )
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDTO handle(ElementNotFoundException ex){
//        return new ErrorDTO(ex.getMessage());
//    }

    @ExceptionHandler( ElementNotFoundException.class ) // priorité par rapport à @ControllerAdvice
    public ResponseEntity<ErrorDTO> handle(ElementNotFoundException ex){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Expires", Instant.now().plusMillis(600_000).toString());

//        return new ResponseEntity<>( new ErrorDTO(ex.getMessage()), headers, HttpStatus.NOT_FOUND);
        return ResponseEntity
//                .ok(ErrorDTO) crée une resp directement
//                .ok() status 200
//                .badRequest() status 400
//                .notFound()
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(new ErrorDTO(ex.getMessage()));
//                .build();
    }



}
