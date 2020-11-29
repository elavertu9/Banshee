package dev.lavertu.banshee.utils;

import dev.lavertu.banshee.exception.api.*;
import dev.lavertu.banshee.services.UsersService;
import dev.lavertu.banshee.user.User;

import java.util.Map;
import java.util.UUID;

public class Validator {
    private Validator() {}

    public static void validateCreateGameRequest(Map<String, String> payload, UsersService usersService) throws ValidationException, UsernameNotFoundException {
        String user1Username = payload.get("user1Username");
        String user2Username = payload.get("user2Username");
        if (user1Username == null || user2Username == null) {
            String missingUsername = (user1Username == null) ? "user 1" : "user 2";
            String failureMessage = String.format("Username for %s not provided. 2 valid usernames are required to play the game.", missingUsername);
            throw new ValidationException(failureMessage);
        }
        validateUserByUsername(user1Username, usersService);
        validateUserByUsername(user2Username, usersService);
    }

    public static void validateCreateUserRequest(User user, UsersService usersService) throws UsernameAlreadyExistsException, EmailAddressAlreadyExistsException {
        String username = user.getUsername();
        String emailAddress = user.getEmailAddress();
        User foundUser = usersService.getUserByUsernameOrEmail(username, emailAddress);
        if (foundUser != null) {
            if (foundUser.getUsername().equals(username)) {
                throw new UsernameAlreadyExistsException("Username " + username + " already taken. Please try another.");
            }
            if (foundUser.getEmailAddress().equals(emailAddress)) {
                throw new EmailAddressAlreadyExistsException("Email " + emailAddress + " already taken. Please try another.");
            }
        }
    }

    public static void validateUserByUsername(String username, UsersService usersService) throws UsernameNotFoundException {
        User user = usersService.getUserByUsername(username);
        if (user == null) {
            String msg = String.format("Username %s not found. User must be created prior to playing.", username);
            throw new UsernameNotFoundException(msg);
        }
    }

    public static void validateUserByUserId(UUID userId, UsersService usersService) throws UserNotFoundException {
        User user = usersService.getUserByUserId(userId);
        if (user == null) {
            String msg = String.format("User ID %s not found. User must be created prior to playing.", userId);
            throw new UserNotFoundException(msg);
        }
    }
}
