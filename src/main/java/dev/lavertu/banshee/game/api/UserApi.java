package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.exception.api.EntityAlreadyExistsException;
import dev.lavertu.banshee.exception.api.EntityNotFoundException;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

// TODO - Make logger print request/responses
// TODO - Set up debugger
@RequestMapping("/api/user")
@RestController
public class UserApi {

//    private static final Logger LOGGER = LogManager.getLogger(UserApi.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApi.class);

    UsersService usersService;

    @Autowired
    public void setUserService(UsersService usersService){
        this.usersService = usersService;
    }

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userInput) throws EntityAlreadyExistsException {
        Validator.validateCreateUserRequest(userInput, usersService);
        User user = usersService.createUser(userInput);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/update/{userId}", produces = "application/Json")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User userInput) throws EntityNotFoundException {
        User user = usersService.updateUser(userId, userInput);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/allUsers", produces = "application/Json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = usersService.getAllUsers();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/userId/{userId}", produces = "application/Json")
    public ResponseEntity<User> getUserByUserId(@PathVariable UUID userId) {
        User user = usersService.getUserByUserId(userId);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/username/{username}", produces = "application/Json")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = usersService.getUserByUsername(username);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/email/{emailAddress}", produces = "application/Json")
    public ResponseEntity<User> getUserByEmailAddress(@PathVariable String emailAddress) {
        User user = usersService.getUserByEmailAddress(emailAddress);
        return ResponseEntity.ok().body(user);
    }
}
