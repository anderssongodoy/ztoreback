package pe.idat.ztore.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import pe.idat.ztore.model.UserEntity;
import pe.idat.ztore.service.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
@Tag(name = "Users", description = "Manage Users")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Hidden
    @PostMapping("")
    public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) {
        try {
            if (user == null) {
                return ResponseEntity.badRequest().body(null);
            }

            UserEntity savedUser = userService.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        try {
            Optional<UserEntity> optionalUser = userService.findById(id);
            if (optionalUser.isPresent()) {
                UserEntity user = optionalUser.get();
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        try {
            List<UserEntity> users = userService.getAll();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        try {
            if (userService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            UserEntity updatedUser = userService.update(user);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            if (userService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            userService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
