package se.web.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.web.store.entity.AdminUser;

public interface AdminUserDAO extends JpaRepository<AdminUser, Integer> {

    @Query("SELECT u FROM AdminUser u WHERE u.username = :username")
    public AdminUser getUserByUsername(@Param("username") String username);

}
