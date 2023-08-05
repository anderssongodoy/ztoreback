package pe.idat.ztore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.idat.ztore.auth.service.AuthenticationService;
import pe.idat.ztore.dto.RequestDTO;
import pe.idat.ztore.dto.JwtResponse;
import pe.idat.ztore.model.Customer;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "MOVE NOW API", version = "1.0.0", description = "API for movings"))
@Tag(name = "Authentication Controller", description = "Controller for Register and Login")
public class AuthController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(
            @Parameter(description = "Email, username and password", required = true)
            @Valid @RequestBody Customer customer) {
        try {
            return ResponseEntity.ok(authenticationService.register(customer));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Login")
    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(
            @Parameter(description = "Email and password", required = true)
            @Valid @RequestBody RequestDTO requestDTO) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(requestDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }



}