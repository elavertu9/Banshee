package dev.lavertu.banshee.services;

import dev.lavertu.banshee.exception.api.EmailAddressAlreadyExistsException;
import dev.lavertu.banshee.exception.api.UsernameAlreadyExistsException;
import dev.lavertu.banshee.repository.UsersRepository;
import dev.lavertu.banshee.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    private static final Logger LOGGER = LogManager.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

//    public User createUser(String username, String firstName, String lastName, String emailAddress) {
//        User user = new User(UUID.randomUUID(), username, firstName, lastName, emailAddress);
//        usersRepository.saveUser(user);
//        return user;
//    }

    public User createUser(User user) throws UsernameAlreadyExistsException, EmailAddressAlreadyExistsException {
        String username = user.getUsername();
        String emailAddress = user.getEmailAddress();

        User foundUser = getUserByUsernameOrEmail(username, emailAddress);
        if (foundUser != null) {
            if (foundUser.getUsername().equals(username)) {
                throw new UsernameAlreadyExistsException("Username " + username + " already taken. Please try another.");
            }
            if (foundUser.getEmailAddress().equals(emailAddress)) {
                throw new EmailAddressAlreadyExistsException("Email " + emailAddress + " already taken. Please try another.");
            }
        }

        user.createUserId();
        usersRepository.saveUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        return usersRepository.getAllUsers();
    }

    public User getUserByUsernameOrEmail(String username, String emailAddress) {
        return usersRepository.getUserByUsernameOrEmailAddress(username, emailAddress);
    }

    public User getUserByUserId(UUID userId) {
        return usersRepository.getUserByUserId(userId);
    }

    public User getUserByUsername(String username) {
        return usersRepository.getUserByUsername(username);
    }

    public User getUserByEmailAddress(String emailAddress) {
        return usersRepository.getUserByEmailAddress(emailAddress);
    }

    public User updateUser(UUID userId, User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String emailAddress = user.getEmailAddress();

        User foundUser = getUserByUserId(userId);
        if (firstName != null && !foundUser.getFirstName().equals(firstName)) {
            foundUser.setFirstName(firstName);
        }
        if (lastName != null && !foundUser.getLastName().equals(lastName)) {
            foundUser.setLastName(lastName);
        }
        if (emailAddress != null && !foundUser.getEmailAddress().equals(emailAddress)) {
            foundUser.setEmailAddress(emailAddress);
        }

        usersRepository.saveUser(foundUser);
        return foundUser;
    }
}