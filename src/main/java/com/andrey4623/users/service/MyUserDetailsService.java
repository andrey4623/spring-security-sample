package com.andrey4623.users.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jboss.logging.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.andrey4623.users.dao.UserDao;

public class MyUserDetailsService implements UserDetailsService {

    private static final String ROLE_USER = "ROLE_USER";
    private UserDao userDao;
    private static final Logger logger = Logger.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        // Programmatic transaction management
        /*
        return transactionTemplate.execute(new TransactionCallback<UserDetails>() {

            public UserDetails doInTransaction(TransactionStatus status) {
                com.andrey4623.users.model.User user = userDao.findByUserName(username);
                List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

                return buildUserForAuthentication(user, authorities);
            }

        });*/

        com.andrey4623.users.model.User user = userDao.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority();

        return buildUserForAuthentication(user, authorities);

    }

    // Converts com.andrey4623.users.model.User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(com.andrey4623.users.model.User user, List<GrantedAuthority> authorities) {
        return new ExtendedUser(user.getId(), user.getUsername(), user.getName(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority() {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority(ROLE_USER));

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}