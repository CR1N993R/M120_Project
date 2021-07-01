package ch.tbz.server.util;

import ch.tbz.server.data.User;
import ch.tbz.server.data.group.GroupChat;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class Database {
    private static EntityManager em;

    public static void init() {
        if (em == null) {
            em = Persistence.createEntityManagerFactory("ch.tbz.discord").createEntityManager();
        }
    }

    public static User getUserByUserName(String name){
        return em.createQuery("from User U where U.username = '" + name +"'", User.class).getSingleResult();
    }

    /*public static List<User> getUsersByName(String search) {
        return em.createQuery("from User where User.username like %" + search + "%", User.class).getResultList();
    }*/

    public static User getUsersById(int id) {
        return em.find(User.class, id);
    }

    /*public static GroupChat getGroupByName(String search) {
        return em.createQuery("from Group where Group.name like %" + search + "%", GroupChat.class).getSingleResult();
    }*/

    public static GroupChat getGroupById(long id) {
        return em.find(GroupChat.class, id);
    }

    public static synchronized void persistObject(Object o) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.persist(o);
        em.flush();
        em.getTransaction().commit();
    }

    public static synchronized void dropObject(Object o) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
        em.remove(o);
        em.flush();
        em.getTransaction().commit();
    }
}
