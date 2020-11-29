package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.*;
import dev.lavertu.banshee.services.GamesService;
import dev.lavertu.banshee.user.User;
import dev.lavertu.banshee.utils.JpaGameConverter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "games", schema = "public")
public class Game implements Serializable {

    @Id
    @Column(name = "game_id")
    private UUID gameId;

    @Column(name = "game_object")
    @Convert(converter = JpaGameConverter.class)
    private GameBoard gameBoard;

    @Column(name = "finished")
    private Boolean finished;

    @Column(name = "user1_color")
    private String user1Color;

    @Column(name = "user2_color")
    private String user2Color;

    @Column(name = "winner")
    private int winner;

    @Column(name = "is_forfeit")
    private boolean isForfeit;

    @Column(name = "forfeit_user")
    private int forfeitUser;

    @Column(name = "turn")
    private int turn;

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @Transient
    private RuleEnforcer ruleEnforcer;

    public Game(){}

    public Game(User user1, User user2) {
        this(UUID.randomUUID(), user1, user2);
    }

    public Game(UUID gameId, User user1, User user2) {
        this.gameId = gameId;
        this.user1 = user1;
        this.user2 = user2;
        this.finished = Boolean.FALSE;
        this.user1Color = Color.BLACK.toString();
        this.user2Color = Color.WHITE.toString();
        this.isForfeit = false;
        this.turn = 1;
        this.gameBoard = new GameBoard();
        this.ruleEnforcer = new RuleEnforcer(this);
    }

    public void createGameId() {
        if (this.gameId == null) {
            this.gameId = UUID.randomUUID();
        }
    }

    public void flipAll() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 8; j++) {
                gameBoard.pieceAt(new Coordinate(i, j)).flipPiece();
            }
        }
    }

    public void makeMove(Move move) throws IllegalMoveException {
        try {
            if(move.getMoveType() == MoveType.CAPTURE) {
                performCapture(move);
            }else if(move.getMoveType() == MoveType.TRAVEL) {
                performTravel(move);
            } else if(move.getMoveType() == MoveType.FLIP) {
                performFlip(move);
            } else {
                throw new IllegalMoveException();
            }
            switchTurn(turn);
        } catch(CaptureException | TravelException | FlipException e) {
            e.printStackTrace();
            throw new IllegalMoveException();
        }
    }

    private void performCapture(Move move) throws CaptureException {
        try {
            if(ruleEnforcer.isValidCapture(move)) {
                gameBoard.removePiece(move.getTo());
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | IllegalCaptureException | CaptureEmptySpaceException | SameColorException | GameOverException | IllegalHopException e) {
            e.printStackTrace();
            throw new CaptureException();
        }
    }

    private void performTravel(Move move) throws TravelException {
        try {
            if(ruleEnforcer.isValidTravel(move)) {
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | SpaceOccupiedException | GameOverException | IllegalHopException e) {
            e.printStackTrace();
            throw new TravelException();
        }
    }

    private void performFlip(Move move) throws FlipException {
        try {
            if(ruleEnforcer.isValidFlip(move)) {
                gameBoard.pieceAt(move.getTo()).flipPiece();
            }
        } catch(CoordinateOutOfBoundsException | PieceFaceUpException | GameOverException e) {
            e.printStackTrace();
            throw new FlipException();
        }
    }

    private void gameOver(UUID winnerId, boolean forfeit) throws GameDoesNotContainUserException {
        this.finished = true;

        // Set winner, update user records
        if (winnerId == user1.getUserId()) {
            this.winner = 1;
            this.user1.updateGameStatsWhenFinished(true);
            this.user2.updateGameStatsWhenFinished(false);
        }
        else if (winnerId == user2.getUserId()) {
            this.winner = 2;
            this.user1.updateGameStatsWhenFinished(false);
            this.user2.updateGameStatsWhenFinished(true);
        }
        else {
            throw new GameDoesNotContainUserException();
        }

        // Set forfeit
        if (forfeit) {
            this.isForfeit = true;
            this.forfeitUser = (this.winner == 1) ? 2 : 1;
        }
    }

    public void forfeitGame(UUID winnerId) throws GameDoesNotContainUserException {
        gameOver(winnerId, true);
    }

    public UUID getGameId() {
        return gameId;
    }

    public User getUser1() {
        return user1;
    }

    public User getUser2() {
        return user2;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public String getUser1Color() {
        return user1Color;
    }

    public String getUser2Color() {
        return user2Color;
    }

    public boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public boolean getIsForfeit() {
        return isForfeit;
    }

    public int getForfeitUser() {
        return forfeitUser;
    }

    public int getTurn() {
        return turn;
    }

    private void switchTurn(int currentTurn) {
        turn = (currentTurn == 1) ? 2 : 1;
    }

    public Date getCreateDate() {
        return createDate;
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = new Date();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    @PreUpdate
    public void setUpdateDate() {
        this.updateDate = new Date();
    }

    @Override
    public String toString() {
        String u1Color = user1Color.toString();
        String u2Color = user2Color.toString();
        return user1.getUsername() + " - " + u1Color + "\n" + user2.getUsername() + " - " + u2Color + "\n" + gameBoard.toString();
    }
}
