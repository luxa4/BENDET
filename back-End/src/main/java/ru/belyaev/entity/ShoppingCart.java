/*
 * Created by Vologda Developer
 * Date: 10.07.2020
 * Time: 14:52
 */


package ru.belyaev.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "shoppingCart", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ShoppingCartItem> shoppingCartItems;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    @Column(name = "total_count")
    private int totalCount;

    @Column(name = "total_cost")
    private BigDecimal totalCost;


    public ShoppingCart() {
        this.shoppingCartItems = new HashSet<>();
    }

    public ShoppingCart(int totalCount, BigDecimal totalCost) {
        this.totalCount = totalCount;
        this.totalCost = totalCost;
    }

    public ShoppingCart(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(Set<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Boolean shoppingCartIsEmpty() {
        return shoppingCartItems.isEmpty() ? true : false;
    }
}
