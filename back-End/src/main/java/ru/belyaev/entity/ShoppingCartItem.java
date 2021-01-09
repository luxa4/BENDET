/*
 * Created by Vologda Developer
 * Date: 10.07.2020
 * Time: 14:52
 */


package ru.belyaev.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "shopping_cart_item")
public class ShoppingCartItem implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private Product product;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_shopping_cart")
    private ShoppingCart shoppingCart;

    @Column
    private int count;

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Product product, ShoppingCart shoppingCart, int count) {
        this.product = product;
        this.shoppingCart = shoppingCart;
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartItem that = (ShoppingCartItem) o;
        return  Objects.equals(id, that.id) &&
                Objects.equals(product, that.product) &&
                Objects.equals(shoppingCart, that.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, shoppingCart);
    }
}
