package org.aston.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import org.aston.model.User;
import org.aston.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {
    @Override
    public Optional<User> get(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(User.class, id));
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from users", User.class).list();
        }
    }

    @Override
    public void save(User user) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        }
    }

    @Override
    public void update(User user, String[] params) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            if (params.length >= 3) {
                user.setName(params[0]);
                user.setEmail(params[1]);
                user.setAge(Integer.parseInt(params[2]));
            }

            session.merge(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null)
                transaction.rollback();

            e.printStackTrace();
        }
    }
}
