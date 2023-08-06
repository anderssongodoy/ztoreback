package pe.idat.ztore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.idat.ztore.auth.service.AuthenticationService;
import pe.idat.ztore.dto.RegisterDTO;
import pe.idat.ztore.dto.RequestDTO;
import pe.idat.ztore.dto.ResponseDTO;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@SecurityRequirement(name = "ztoreapi")
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "ZTORE API", version = "1.0.0", description = "API FOR STORE"))
@Tag(name = "Authentication Controller", description = "Controller for Register and Login")
public class AuthController {

	private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Operation(summary = "Register")
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(
            @Parameter(description = "Username and password", required = true)
            @Valid @RequestBody RegisterDTO registerDTO) {
        try {
            return ResponseEntity.ok(authenticationService.register(registerDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Login")
    @PostMapping("/authenticate")
    public ResponseEntity<ResponseDTO> authenticate(
            @Parameter(description = "Username and password", required = true)
            @Valid @RequestBody RequestDTO requestDTO) {
        try {
            return ResponseEntity.ok(authenticationService.authenticate(requestDTO));
        } catch (RuntimeException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}