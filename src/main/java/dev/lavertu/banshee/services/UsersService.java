package dev.lavertu.banshee.services;

import dev.lavertu.banshee.exception.api.EmailAddressAlreadyExistsException;
import dev.lavertu.banshee.exception.api.UserNotFoundException;
import dev.lavertu.banshee.exception.api.UsernameAlreadyExistsException;
import dev.lavertu.banshee.repository.UsersRepository;
import dev.lavertu.banshee.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

//    private static final Logger LOGGER = LogManager.getLogger(UsersService.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

    @Autowired
    private UsersRepository usersRepository;

    public User createUser(User user) throws UsernameAlreadyExistsException, EmailAddressAlreadyExistsException {
        user.createUserId();
        user.initializeGameStats();
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

    public User updateUser(UUID userId, User user) throws UserNotFoundException {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String emailAddress = user.getEmailAddress();

        User foundUser = getUserByUserId(userId);
        if (foundUser == null) {
            throw new UserNotFoundException("User not found. User must be created before being updated.");
        }

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