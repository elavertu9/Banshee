package dev.lavertu.banshee.repository;

import dev.lavertu.banshee.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Transactional
@Repository
public class UsersRepository {

    private static final Logger LOGGER = LogManager.getLogger(UsersRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> getAllUsers() {
        String hql = "SELECT u FROM User as u";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        return query.getResultList();
    }

    public User getUserByUserId(UUID userId) {
        String hql = "SELECT u FROM User u WHERE u.userId = :userId";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("userId", userId);
        List<User> userList = query.getResultList();
        return (userList == null || userList.isEmpty()) ? null : userList.get(0);
    }

    public User getUserByUsername(String username) {
        String hql = "SELECT u FROM User u WHERE LOWER(u.username) = :username";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("username", username.toLowerCase());
        List<User> userList = query.getResultList();
        return (userList == null || userList.isEmpty()) ? null : userList.get(0);
    }

    public User getUserByEmailAddress(String emailAddress) {
        String hql = "SELECT u FROM User u WHERE LOWER(u.emailAddress) = :emailAddress";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("emailAddress", emailAddress.toLowerCase());
        List<User> userList = query.getResultList();
        return (userList == null || userList.isEmpty()) ? null : userList.get(0);
    }

    public User getUserByUsernameOrEmailAddress(String username, String emailAddress) {
        String hql = "SELECT u FROM User u WHERE LOWER(u.username) = :username OR LOWER(u.emailAddress) = :emailAddress";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        query.setParameter("username", username.toLowerCase());
        query.setParameter("emailAddress", emailAddress.toLowerCase());
        List<User> userList = query.getResultList();
        return (userList == null || userList.isEmpty()) ? null : userList.get(0);
    }

    public void saveUser(User user) {
        entityManager.persist(user);
    }
}


