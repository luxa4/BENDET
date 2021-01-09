/*
 * Created by Vologda Developer
 * Date: 25.06.2020
 * Time: 14:20
 */


package ru.belyaev.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String role;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "user_role",
    joinColumns = @JoinColumn(name = "id_role"),
    inverseJoinColumns = @JoinColumn(name = "id_user"))
    private List<User> users;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
