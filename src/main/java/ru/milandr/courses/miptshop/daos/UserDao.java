package ru.milandr.courses.miptshop.daos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.milandr.courses.miptshop.entities.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Query("SELECT user " +
            "FROM User user " +
            "WHERE user.email = :email")
    User findByEmail(@Param("email") String email);

    @Query("SELECT user " +
            "FROM User user " +
            "WHERE user.name = :name")
    User findByName(@Param("name") String name);

    //Для самоконтроля!
    //User findByEmail(String email);
}
