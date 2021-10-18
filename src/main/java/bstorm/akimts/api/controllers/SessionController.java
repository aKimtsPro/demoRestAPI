package bstorm.akimts.api.controllers;

import bstorm.akimts.api.models.form.LoginForm;
import bstorm.akimts.api.service.SessionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginForm form){
        return ResponseEntity.ok( sessionService.login(form) );
    }

}
