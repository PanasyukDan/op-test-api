package nl.op.controller;

import nl.op.domain.User;
import nl.op.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*") //TODO remove in production
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable UUID id){
        logger.info("REST request to get User={}",id);
        User response = userService.findById(id).orElseThrow(EntityNotFoundException::new);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@PathVariable UUID id){
        logger.info("REST request to save User={}",id);
        User response = userService.save(id);
        return ResponseEntity.ok(response);
    }


}
