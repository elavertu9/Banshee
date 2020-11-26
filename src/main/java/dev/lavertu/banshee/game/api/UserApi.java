package dev.lavertu.banshee.game.api;

import dev.lavertu.banshee.exception.api.EmailAddressAlreadyExistsException;
import dev.lavertu.banshee.exception.api.UsernameAlreadyExistsException;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;
import org.apache.coyote.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger(UserApi.class);

    UsersService usersService;

    @Autowired
    public void setUserService(UsersService usersService){
        this.usersService = usersService;
    }

    // TODO - Make input an update object, instead of list of params
    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@Valid @RequestBody User userInput) throws EmailAddressAlreadyExistsException, UsernameAlreadyExistsException {
        User user = usersService.createUser(userInput);
        return ResponseEntity.ok().body(user);
//        String username = userInput.getUsername();
//        String emailAddress = userInput.getEmailAddress();
//        String firstName = userInput.getFirstName();
//        String lastName = userInput.getLastName();
//        User user = usersService.getUserByUsernameOrEmail(username, emailAddress);
//        if (user != null) {
//            if (user.getUsername().equals(username)) {
//                throw new UsernameAlreadyExistsException("Username " + username + " already taken. Please try another.");
//            }
//            if (user.getEmailAddress().equals(emailAddress)) {
//                throw new EmailAddressAlreadyExistsException("Email " + emailAddress + " already taken. Please try another.");
//            }
//        }
//        user = usersService.createUser(username, firstName, lastName, emailAddress);
//        return ResponseEntity.ok().body(user);
    }

    // TODO - Make input an update object, instead of list of params
    @PutMapping(value = "/update/{userId}", produces = "application/Json")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User userInput) {
        User user = usersService.updateUser(userId, userInput);
        return ResponseEntity.ok().body(user);
//        String firstName = userInput.getFirstName();
//        String lastName = userInput.getLastName();
//        String emailAddress = userInput.getEmailAddress();
//        User user = usersService.getUserByUserId(userId);
//        if (firstName != null && !user.getFirstName().equals(firstName)) {
//            user.setFirstName(firstName);
//        }
//        if (lastName != null && !user.getLastName().equals(lastName)) {
//            user.setLastName(lastName);
//        }
//        if (emailAddress != null && !user.getEmailAddress().equals(emailAddress)) {
//            user.setEmailAddress(emailAddress);
//        }
//        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/allusers", produces = "application/Json")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = usersService.getAllUsers();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping(value = "/id/{userId}", produces = "application/Json")
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