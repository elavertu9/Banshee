package dev.lavertu.banshee.game;

import dev.lavertu.banshee.exception.*;
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

    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User player1;

    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User player2;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    @Transient
    private GameStats gameStats;
    @Transient
    private RuleEnforcer ruleEnforcer;

    public Game(){}

    public Game(User player1, User player2) {
        this(UUID.randomUUID(), player1, player2);
    }

    public Game(UUID gameId, User player1, User player2) {
        this.gameId = gameId;
        this.player1 = player1;
        this.player2 = player2;
        this.finished = Boolean.FALSE;
        this.gameStats = new GameStats(player1, player2);
        this.gameBoard = new GameBoard();
        this.ruleEnforcer = new RuleEnforcer(this);
    }

    public void createUserId() {
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

    public void makeMove(Move move) {
        try {
            if(move.getMoveType() == MoveType.CAPTURE) {
                performCapture(move);
            }else if(move.getMoveType() == MoveType.TRAVEL) {
                performTravel(move);
            } else if(move.getMoveType() == MoveType.FLIP) {
                performFlip(move);
            } else {
                throw new IllegalMoveException("Move type unrecognized " + move.getMoveType());
            }
        } catch(IllegalMoveException e) {
            e.printStackTrace();
        }
    }

    private void performCapture(Move move) {
        try {
            if(ruleEnforcer.isValidCapture(move)) {
                gameBoard.removePiece(move.getTo());
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | GameOverException | IllegalMoveException e) {
            e.printStackTrace();
        }
    }

    private void performTravel(Move move) {
        try {
            if(ruleEnforcer.isValidTravel(move)) {
                gameBoard.swapPieces(move.getFrom(), move.getTo());
            }
        } catch(CoordinateOutOfBoundsException | GameOverException | IllegalMoveException e) {
            e.printStackTrace();
        }
    }

    private void performFlip(Move move) {
        try {
            if(ruleEnforcer.isValidFlip(move)) {
                gameBoard.pieceAt(move.getTo()).flipPiece();
            }
        } catch(CoordinateOutOfBoundsException | GameOverException | IllegalMoveException e) {
            e.printStackTrace();
        }
    }

    public UUID getGameId() {
        return gameId;
    }

    public User getPlayer1() {
        return player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameStats getGameStats() {
        return gameStats;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
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

    // TODO - Make game stats a database table? or make player color part of game record?
    @Override
    public String toString() {
        String p1Color = null;
        String p2Color = null;
        if (gameStats != null) {
            p1Color = gameStats.getPlayer1Color().toString();
            p2Color = gameStats.getPlayer2Color().toString();
        }
        return player1.getUsername() + " - " + p1Color + "\n" + player2.getUsername() + " - " + p2Color + "\n" + gameBoard.toString();
    }
}
