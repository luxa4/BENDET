package ru.belyaev.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.belyaev.entity.Product;
import ru.belyaev.entity.ShoppingCart;
import ru.belyaev.entity.ShoppingCartItem;
import ru.belyaev.entity.User;
import ru.belyaev.repository.ProductRepository;
import ru.belyaev.repository.ShoppingCartItemRepository;
import ru.belyaev.repository.ShoppingCartRepository;
import ru.belyaev.repository.UserRepository;
import ru.belyaev.service.ShoppingCartService;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    public static final Logger LOGGER = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductRepository productRepository;


    @Override
    public ShoppingCart getShoppingCartByUser(User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        return shoppingCart;
    }

    @Override
    @Transactional
    public ShoppingCart addToShoppingCart(int productId, int count, User user) {

        Product productToCart = productRepository.findProductById(productId);
        LOGGER.info("--->>> Found product by ID");

        LOGGER.info("--->>> Searching cart.... ");
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);

        if(shoppingCart == null) {
            LOGGER.info("--->>> The Cart is't found, creating new ..");
            shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            shoppingCart.setTotalCount(0);
            shoppingCart.setTotalCost(BigDecimal.valueOf(0));
            shoppingCartRepository.save(shoppingCart);
            LOGGER.info("--->>> Save Cart into DataBase");
        }

        ShoppingCartItem existProductInShoppingCart = shoppingCartItemRepository.findShoppingCartItemByShoppingCartAndProduct(shoppingCart, productToCart);

        if (existProductInShoppingCart == null) {
            LOGGER.info("--->>> Данного продукта в корзине еще не было, добавляем как новый");
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setShoppingCart(shoppingCart);
            shoppingCartItem.setProduct(productToCart);
            shoppingCartItem.setCount(count);
            shoppingCart.getShoppingCartItems().add(shoppingCartItem);
            LOGGER.info("--->>> Добавили товар в корзину");
        } else {
            LOGGER.info("--->>> Данный продукт в корзине уже был, обновляем кол-во");
            shoppingCart.getShoppingCartItems().remove(existProductInShoppingCart);
            existProductInShoppingCart.setCount(existProductInShoppingCart.getCount() + count);
            shoppingCart.getShoppingCartItems().add(existProductInShoppingCart);
            LOGGER.info("--->>> Обновили товар в корзине");
        }
        refreshShoppingCart(shoppingCart, user);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    @Override
    @Transactional
    public ShoppingCart removeFromShoppingCart(int productId, int count, User user) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUser(user);
        Product product = productRepository.findProductById(productId);
        ShoppingCartItem existProductInShoppingCart = shoppingCartItemRepository.findShoppingCartItemByShoppingCartAndProduct(shoppingCart, product);

        if (existProductInShoppingCart != null) {
            if (existProductInShoppingCart.getCount() > count) {
                existProductInShoppingCart.setCount(existProductInShoppingCart.getCount()-count);
                shoppingCart.getShoppingCartItems().add(existProductInShoppingCart);
                shoppingCartItemRepository.save(existProductInShoppingCart);
                LOGGER.info("--->>> Удалили часть продукта");
            } else {
                LOGGER.info("--->>> Начать удаление");
                shoppingCart.getShoppingCartItems().remove(existProductInShoppingCart);
                LOGGER.info("--->>> Удалили весь продукт");
            }
        }
        refreshShoppingCart(shoppingCart, user);
        shoppingCartRepository.saveAndFlush(shoppingCart);
        LOGGER.info("--->>> Сохранение - {} - {}", shoppingCart.getTotalCount(), shoppingCart.getTotalCost());
        return shoppingCart;
    }


    private void refreshShoppingCart(ShoppingCart shoppingCart, User user) {
        refreshTotalCount(shoppingCart, user);
        refreshTotalCost(shoppingCart, user);
    }

    private void refreshTotalCount(ShoppingCart ThisShoppingCart, User user){
        Set<ShoppingCartItem> list = shoppingCartRepository.findShoppingCartByUser(user).getShoppingCartItems();
        int totalCount = 0;
        for(ShoppingCartItem item: list) {
            totalCount = totalCount + item.getCount();
        }
        ThisShoppingCart.setTotalCount(totalCount);
        LOGGER.info("Общее кол-во - {}", ThisShoppingCart.getTotalCount());
    }

    private void refreshTotalCost(ShoppingCart ThisShoppingCart, User user) {
        Set<ShoppingCartItem> list = shoppingCartRepository.findShoppingCartByUser(user).getShoppingCartItems();
        BigDecimal totalCost = BigDecimal.valueOf(0.00);
        for(ShoppingCartItem item: list) {
            totalCost = totalCost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
        }
        ThisShoppingCart.setTotalCost(totalCost);
        LOGGER.info("Общая стоимость - {}", ThisShoppingCart.getTotalCost());
    }
}
