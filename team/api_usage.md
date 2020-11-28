# Banshee API Usage

## Table of Contents
1. [GUI Endpoints](#gui-endpoints)
    1. [Hello](#hello---get-hello)
2. [API Endpoints](#api-endpoints)
    1. [Games](#games)
        1. [Create Game](#create-game---post-create)
        2. [Get All Games](#get-all-games---get-allgames)
        3. [Get All Active Games](#get-all-active-games---get-allactivegames)
        4. [Get Game By GameId](#get-game-by-gameid---get-idgameid)
        5. [Get Game By Player1Id](#get-game-by-player1id---get-player1idplayer1id)
        6. [Get Game By Player2Id](#get-game-by-player2id---get-player2idplayer2id)
    2. [Users](#users)
        1. [Create User](#create-user---post-create)
        2. [Update User](#update-user---put-updateuserid)
        3. [Get All Users](#get-all-users---get-allusers)
        4. [Get User By UserId](#get-user-by-userid---get-iduserid)
        5. [Get User By Username](#get-user-by-username---get-idusername)
        6. [Get User By Email Address](#get-user-by-email-address---get-idemailaddress)
        

## GUI Endpoints

- ##### Hello - GET /hello 
    1. Returns the Banshee home page
    
## API Endpoints

All requests to the API must start with **/api**  
Any posted data must be in JSON format  

### Games

All requests to the Games API must be prefixed with **/game**  
Example:  `curl -X POST --url http://localhost:8080/api/game/create -H "Content-Type:application/json" -d '{"player1Username": "player1", "player2Username": "player2"}'`

- ##### Create Game - POST /create
    - **Request Parameters**
        1. player1Username - *REQUIRED* - Username of player1 
        2. player2Username - *REQUIRED* - Username of player2
    - **Response**
        - The newly created Game object
    - **Requisites**
        - Users must be created in Banshee before a game can be started
    - **Possible Errors**
        - GameMappingException - If there is an internal error storing the gameBoard
        - UsernameNotFoundException - If a provided username is invalid or not found in the currently created Users
        - ValidationException - If there a problem validating the input, or if some required parameters are not supplied
    - **Description**
        - Creates a new Banshee game using the provided usernames as Player 1 and Player 2

- ##### Get All Games - GET /allgames
    - **Response**
        - Returns the list of game objects in which the given user ID is player 1
    - **Description**
        - Returns all running and finished games

- ##### Get All Active Games - GET /allactivegames
    - **Response**
        - Returns the list of game objects in which the given user ID is player 1
    - **Description**
        - Returns all active games (games that are still running)

- ##### Get Game By GameId - GET /id/{gameId}
    - **Request Parameters**
        1. gameId - *REQUIRED* - UUID of the game to find 
    - **Response**
        - If the game ID exists, returns the corresponding game. If the game doesn't exist, returns an empty JSON object
    - **Description**
        - Returns a Game object for a given gameId

- ##### Get Game By Player1Id - GET /player1Id/{player1Id}
    - **Request Parameters**
        1. player1Id - *REQUIRED* - UUID of player1 
    - **Response**
        - Returns the list of games in which the given user ID is player 1
    - **Requisites**
        - User must be created in Banshee
    - **Possible Errors**
        - UserNotFound - If a user for the specified userId cannot be found
    - **Description**
        - Returns all games in which the given player is player 1

- ##### Get Game By Player2Id - GET /player2Id/{player2Id}
    - **Request Parameters**
        1. player2Id - *REQUIRED* - UUID of player2 
    - **Response**
        - Returns the list of games in which the given user ID is player 2
    - **Requisites**
        - User must be created in Banshee
    - **Possible Errors**
        - UserNotFound - If a user for the specified userId cannot be found
    - **Description**
        - Returns all games in which the given player is player 2
  
### Users

All requests to the Games API must be prefixed with **/user**  
Example:  `curl -X POST --url http://localhost:8080/api/user/create -H 'Content-Type: application/json' -d '{"username": "pizza", "firstName": "Pizza", "lastName": "Man", "emailAddress": "pizza@gmail.com"}'`

- ##### Create User - POST /create
    - **Request Parameters**
        1. username - *REQUIRED* - desired username 
        2. firstName - *REQUIRED* - first name of the user
        3. lastName - *REQUIRED* - last name of the user
        4. emailAddress - *REQUIRED* - email address of the user
    - **Response**
        - The newly created User object
    - **Requisites**
        - Username and email must be unique
    - **Possible Errors**
        - EmailAddressAlreadyExistsException - If the specified email address is already in use
        - UsernameAlreadyExistsException - If the specified username is already in use
    - **Description**
        - Creates a new Banshee user using the supplied parameters

- ##### Update User - PUT /update/{userId}
    - **Request Parameters**
        1. userId - *REQUIRED* - userId of the user
        2. firstName - *OPTIONAL* - updated first name for the user
        3. lastName - *OPTIONAL* - updated last name for the user
        4. emailAddress - *OPTIONAL* - updated email address for the user
    - **Response**
        - The updated User object
    - **Possible Errors**
        - UserNotFoundException - If the user for the given userId does not exist
    - **Description**
        - Updates a Banshee user using the supplied parameters

- ##### Get All Users - GET /allusers
    - **Response**
        - Return list of all user objects
    - **Description**
        - Returns all users

- ##### Get User By UserId - GET /id/{userId}
    - **Request Parameters**
        1. userId - *REQUIRED* - userId of the user to find 
    - **Response**
        - If the userId exists, returns the corresponding user. If the userId doesn't exist, returns an empty JSON object
    - **Description**
        - Returns a User object for a given userId

- ##### Get User By Username - GET /id/{username}
    - **Request Parameters**
        1. username - *REQUIRED* - username of the user to find 
    - **Response**
        - If the username exists, returns the corresponding user. If the username doesn't exist, returns an empty JSON object
    - **Description**
        - Returns a User object for a given username

- ##### Get User By Email Address - GET /id/{emailAddress}
    - **Request Parameters**
        1. emailAddress - *REQUIRED* - Email address of the user to find 
    - **Response**
        - If the email exists, returns the corresponding user. If the email doesn't exist, returns an empty JSON object
    - **Description**
        - Returns a User object for a given email address
