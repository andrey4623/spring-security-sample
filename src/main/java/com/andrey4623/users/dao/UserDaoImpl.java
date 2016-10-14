package com.andrey4623.users.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;
import org.hibernate.SessionFactory;
import com.andrey4623.users.model.User;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;
    private static final Logger logger = Logger.getLogger(UserDaoImpl.class.getName());
    private static final String DELIMITER = "	";
    private static final String PASSWORD_FILE = "passwords.txt";

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        List<User> users = new ArrayList<User>();

        users = getSessionFactory().getCurrentSession().createQuery("from User where username=?")
                .setParameter(0, username).list();

        logger.info("We have users");

        if (users.size() > 0) {
            User user = users.get(0);
            // Set the password for user from file.
            user.setPassword(getUserPasswordFromFile(user.getId()));

            return user;
        } else {
            return null;
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private String getUserPasswordFromFile(final int userId) {

        String userPassword = null;

        try(BufferedReader br = new BufferedReader(new FileReader(PASSWORD_FILE))) {
            for (String line; (line = br.readLine()) != null; ) {

                StringTokenizer tokenizer = new StringTokenizer(line, DELIMITER);
                if (tokenizer.countTokens() < 2)
                    continue;
                final String candidateUserId = tokenizer.nextToken();

                int id;

                try {
                    id = Integer.parseInt(candidateUserId);
                }
                catch (NumberFormatException ex) {
                    continue;
                }

                if (id != userId)
                    continue;

                userPassword = tokenizer.nextToken();
                break;

            }
        }
        catch (IOException ex) {
            logger.severe(ex.getMessage());
        }

        return userPassword;

    }

}