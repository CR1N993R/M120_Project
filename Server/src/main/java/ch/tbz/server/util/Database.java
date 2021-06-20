package ch.tbz.server.util;

import ch.tbz.server.data.User;
import ch.tbz.server.data.group.GroupChat;
import org.hibernate.NonUniqueResultException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class Database {
    private static EntityManager em;

    public static void init(){
        if (em == null) {
            em = Persistence.createEntityManagerFactory("ch.tbz.discord").createEntityManager();
        }
    }

    public static User getUserByUserNameOrId(String name){
        try {
            return em.createQuery("from User where User.username is " + name, User.class).getSingleResult();
        }catch (NoResultException | NonUniqueResultException e){
            return em.find(User.class, name);
        }
    }

    public static List<User> getUsersByName(String search){
        return em.createQuery("from User where User.username like %" +search+ "%", User.class).getResultList();
    }

    public static User getUsersById(int id){
        return em.find(User.class, id);
    }

    public static GroupChat getGroupByName(String search){
        return em.createQuery("from Group where Group.name like %" + search + "%", GroupChat.class).getSingleResult();
    }

    public static GroupChat getGroupById(long id){
        return em.find(GroupChat.class, id);
    }

    public static void persistObject(Object o){
        em.getTransaction().begin();
        em.persist(o);
        em.flush();
        em.getTransaction().commit();
    }

    public static void dropObject(Object o){
        em.getTransaction().begin();
        em.remove(o);
        em.flush();
        em.getTransaction().commit();
    }
}
