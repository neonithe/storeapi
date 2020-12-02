package se.web.store.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import se.web.store.dao.AdminUserDAO;
import se.web.store.entity.AdminUser;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminUserDAO adminUserDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AdminUser adminUser = adminUserDAO.getUserByUsername(username);

        if(adminUser == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(adminUser);
    }
}
