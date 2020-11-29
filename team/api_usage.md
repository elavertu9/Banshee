# Banshee API Usage

## Table of Contents
* [Recommended Toolset](#recommended-toolset)
* [GUI Endpoints](#gui-endpoints)
    * [hello](#hello)
* [API Endpoints](#api-endpoints)
    * [Games](#games)
        * [Create Game](#create-game)
        * [Get All Games](#get-all-games)
        * [Get All Active Games](#get-all-active-games)
        * [Get Game By GameId](#get-game-by-game-id)
        * [Get Game By UserId](#get-game-by-userid)
    * [Users](#users)
        * [Create User](#create-user)
        * [Update User](#update-user)
        * [Get All Users](#get-all-users)
        * [Get User By UserId](#get-user-by-userid)
        * [Get User By Username](#get-user-by-username)
        * [Get User By Email Address](#get-user-by-email-address)
        
## Recommended Toolset
* [Insomnia](https://support.insomnia.rest/article/23-installation)
* Download Insomnia and select File Import and provide it the JSON file here:
```text
/Banshee/insomnia_configuration/incomnia_configuration.json
```

## GUI Endpoints
#### hello
```text
[GET] /hello
``` 
* Returns the Banshee home page
    
    
## API Endpoints
* All requests to the API must start with **/api**  
* Any posted data must be in JSON format  

### Games
* All requests to the Games API must be prefixed with **/api/game**  
* Example:  
```text 
curl -X POST --url http://localhost:8080/api/game/create -H "Content-Type:application/json" -d '{"player1Username": "player1", "player2Username": "player2"}'
```

#### Create Game
```text
[POST] /api/game/create
```
* Request Parameters
    * userUsername - *REQUIRED* - Username of user1
    * user2Username - *REQUIRED* - Username of user2
* Response
    * The newly created Game object
* Requisites
    * Users must be created in Banshee before a game can be started
* Possible Errors
    * UsernameNotFoundException - If a provided username is invalid or not found in the currently created Users
    * ValidationException - If there is a problem validating the input, or if some required parameters are not supplied
* Description
    * Created a new Banshee game using the provided usernames as User 1 and User 2

#### Get All Games
```text
[GET] /api/game/allGames
```
* Response
    * Returns all games
* Description
    * Returns all running and finished games
    
#### Get All Active Games
```text
[GET] /api/game/allActiveGames
```
* Response
    * Returns the list of all active games
* Description
    * Returns all active games (games that are still running)
    
#### Get Game By Game Id
```text
[GET] /api/game/gameId/{gameId}
```
* Request Parameters
    * gameId - *REQUIRED* - UUID of the game to find 
* Response
    * If the game ID exists, returns the corresponding game. If the game doesn't exist, returns an empty JSON object
* Description
    * Returns a Game object for a given gameId
    
#### Get Game By UserId
```text
[GET] /api/game/userId/{userId}
```
* Request Parameters
    * userId - *REQUIRED* - UUID of user to find all games for
* Response
    * Returns the list of games in which the given user ID is a part of
* Requisites
    * User must be created in Banshee
* Possible Errors
    * UserNotFound - If a user for the specified userId cannot be found
* Description
    * Returns all games in which the given user is a part of
    
 
### Users
* All requests to the Games API must be prefixed with **/api/user**  
* Example:  
```text
curl -X POST --url http://localhost:8080/api/user/create -H 'Content-Type: application/json' -d '{"username": "pizza", "firstName": "Pizza", "lastName": "Man", "emailAddress": "pizza@gmail.com"}'
```

#### Create User
```text
[GET] /api/user/create
```
* Request Parameters
    * username - *REQUIRED* - desired username 
    * firstName - *REQUIRED* - first name of the user
    * lastName - *REQUIRED* - last name of the user
    * emailAddress - *REQUIRED* - email address of the user
* Response
    * The newly created User object
* Requisites
    * Username and email must be unique
* Possible Errors
    * EmailAddressAlreadyExistsException - If the specified email address is already in use
    * UsernameAlreadyExistsException - If the specified username is already in use
* Description
    * Creates a new Banshee user using the supplied parameters

#### Update User
```text
[PUT] /api/user/update/{userId}
```
* Request Parameters
    * userId - *REQUIRED* - userId of the user
    * firstName - *OPTIONAL* - updated first name for the user
    * lastName - *OPTIONAL* - updated last name for the user
    * emailAddress - *OPTIONAL* - updated email address for the user
* Response
    * The updated User object
* Possible Errors
    * UserNotFoundException - If the user for the given userId does not exist
* Description
    * Updates a Banshee user using the supplied parameters

#### Get All Users
```text
[GET] /api/user/allUsers
```
* Response
    * Returns list of all user objects
* Description
    * Returns all users

#### Get User By UserId
```text
[GET] /api/user/userId/{userId}
```
* Request Parameters
    * userId - *REQUIRED* - userId of the user to find
* Response
    * If the userId exists, returns the corresponding user. If the userId doesn't exist, returns an empty JSON object
* Description
    * Returns a User object for a given userId

#### Get User By Username
```text
[GET] /api/user/username/{userId}
```
* Request Parameters
    * username - *REQUIRED* - username of the user to find 
* Response
    * If the username exists, returns the corresponding user. If the username doesn't exist, returns an empty JSON object
* Description
    * Returns a User object for a given username
    
#### Get User By Email Address
```text
[GET] /api/user/email/{userId}
```
* Request Parameters
    * emailAddress - *REQUIRED* - Email address of the user to find 
* Response
    * If the email exists, returns the corresponding user. If the email doesn't exist, returns an empty JSON object
* Description
    * Returns a User object for a given email address
