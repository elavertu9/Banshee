package dev.lavertu.banshee.user;

import dev.lavertu.banshee.game.pieces.iPiece;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "users", schema = "public")
public class User implements Serializable {

    private static final Logger LOGGER = LogManager.getLogger(User.class);

    @Id
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "username")
    @NotBlank(message = "Username is required")
    private String username;

    @Column(name = "first_name")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Column(name = "email_address")
    @NotBlank(message = "Email address is required")
    private String emailAddress;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;

    // TODO - these need to be added to DB model to work
//    private ArrayList<iPiece> myPieces;
//    private UserStats userStats;

    public User(){}

    public User(UUID userId, String username, String firstName, String  lastName, String emailAddress) {
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
//        this.userStats = new UserStats();
    }

    public void createUserId() {
        if (this.userId == null) {
            this.userId = UUID.randomUUID();
        }
    }

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

//    public ArrayList<iPiece> getMyPieces() {
//        return myPieces;
//    }

    @Override
    public String toString() {
        return "Player: \n\t" + userId.toString() + "\n\t" + username;
    }
}
