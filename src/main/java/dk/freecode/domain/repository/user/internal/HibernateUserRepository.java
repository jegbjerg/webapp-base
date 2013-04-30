package dk.freecode.domain.repository.user.internal;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import dk.freecode.domain.model.user.User;
import dk.freecode.domain.repository.user.UserRepository;

@Repository
public class HibernateUserRepository implements UserRepository {

    @Inject
    private SessionFactory sessionFactory;

    @Override
    public User getUser(final String username) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User u where u.username = :username")
                .setParameter("username", username).uniqueResult();
    }

    @Override
    public void saveUser(final User user) {
        sessionFactory.getCurrentSession().save(user);
    }
}
