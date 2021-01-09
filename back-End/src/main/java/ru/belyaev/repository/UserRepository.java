/*
 * Created by Vologda Developer
 * Date: 20.06.2020
 * Time: 13:54
 */

package ru.belyaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByName(String name);

    User findUserByEmail(String email);

    List<User> findAll();
}
