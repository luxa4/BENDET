/*
 * Created by Vologda Developer
 * Date: 05.07.2020
 * Time: 11:06
 */

package ru.belyaev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.belyaev.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRole(String role);

}
