package ru.belyaev.service;

import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.User;

public interface ShoppingCartService {

    ShoppingCart addToShoppingCart(int productId, int count, User user);

    ShoppingCart removeFromShoppingCart(int productId, int count, User user);

    ShoppingCart getShoppingCartByUser(User user);

}
