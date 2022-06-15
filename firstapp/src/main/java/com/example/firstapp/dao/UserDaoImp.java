package com.example.firstapp.dao;

import com.example.firstapp.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsers() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void delUser(Long id) {
        User user = entityManager.find( User.class,id);
        entityManager.remove(user);


    }

    @Override
    public void regUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserWithCredentials(User user){
        String query = "FROM User WHERE email = :email";
        List<User> list = entityManager.createQuery(query)
                .setParameter("email", user.getEmail())
                .getResultList();

        //check if email or password is empty for prevent null exception

        if (list.isEmpty() ){
            return null;
        }
        String passwordHashed = list.get(0).getPassword();
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if(argon2.verify(passwordHashed, user.getPassword()) ) {
            return list.get(0);
        }

    return null;
    }
}
